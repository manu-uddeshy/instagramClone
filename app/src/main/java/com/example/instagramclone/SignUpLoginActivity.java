package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {
    private Button btnlogin, btnsignup;
    private EditText edtusernameS,edtpassS,edtusernameL,editpassL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_login_activity);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnsignup=(Button) findViewById(R.id.btnsignup);
        edtusernameS=(EditText) findViewById(R.id.edtusrnmeS);
        edtpassS=(EditText) findViewById(R.id.edtpassS);
        editpassL=(EditText) findViewById(R.id.edtpasswordL);
        edtusernameL=(EditText)findViewById(R.id.edtusrnmeL);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtusernameS.getText().toString());
                appUser.setPassword(edtpassS.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,"you are successfully signed up!", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,"error in signup process",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtusernameL.getText().toString(), editpassL.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null) {
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        }else {
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " log in failed!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
    }
}
