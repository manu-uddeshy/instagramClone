package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity{
private EditText edtemail,edtpass,editusername;
private Button btnlogin,btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign Up");
        edtemail=findViewById(R.id.edtemail);
        editusername=findViewById(R.id.edtusername);
        edtpass=findViewById(R.id.edtpass);
        btnsignup=findViewById(R.id.btnsignup);
        btnlogin = findViewById(R.id.btnlogin);
        if(ParseUser.getCurrentUser() != null){
//            ParseUser.getCurrentUser().logOut();
              transitionToSocialMediaActivity();
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtemail.getText().toString().equals("") || editusername.getText().toString().equals("") || edtpass.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,"All Fields Are Required!",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }else{
                    final ParseUser appuser = new ParseUser();
                    appuser.setEmail(edtemail.getText().toString());
                    appuser.setUsername(editusername.getText().toString());
                    appuser.setPassword(edtpass.getText().toString());
                    final ProgressDialog progressDialog= new ProgressDialog(SignUp.this);
                    progressDialog.setMessage("signing you up!");
                    progressDialog.show();

                    appuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e==null){
                                FancyToast.makeText(SignUp.this,appuser.get("username")+" successfully signed up!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                transitionToSocialMediaActivity();
                            }else {
                                FancyToast.makeText(SignUp.this,"Error in sign up: "+e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }

            }
        });


    }
    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
