package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

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
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private Button submitbtn , btngetall, btnswitch;
    private EditText Ename, Pspeed,Ppower,Kspeed,Kpower;
    private TextView textView1;
    private String allkickboxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitbtn = (Button) findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(SignUp.this);
        Ename =(EditText) findViewById(R.id.enterName);
        Pspeed =(EditText) findViewById(R.id.PunchSpeedinput);
        Ppower =(EditText) findViewById(R.id.PunchPowerInput);
        Kspeed = (EditText) findViewById(R.id.KIckspeedinput);
        Kpower= (EditText) findViewById(R.id.kickPowerInput);
        textView1 = (TextView) findViewById(R.id.Textview1);
        allkickboxers ="";
        btngetall = (Button) findViewById(R.id.btngetall);
        btnswitch= (Button) findViewById(R.id.button2);
        btnswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
               startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("3DF9JfNcnU", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e ==null){
                            textView1.setText(object.get("name")+"-"+ object.get("punch_power"));
                        }
                    }
                });
            }
        });
        btngetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e == null){
                            if(objects.size()>0){
                                for(ParseObject KickBoxer : objects){
                                  allkickboxers = allkickboxers + KickBoxer.get("name")+ "\n";
                                }
                                FancyToast.makeText(SignUp.this,allkickboxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                        }else {
                            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });

    }
    public void onClick(View v){
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", Ename.getText().toString());
            kickBoxer.put("punch_speed", Integer.parseInt(Pspeed.getText().toString()));
            kickBoxer.put("punch_power", Integer.parseInt(Ppower.getText().toString()));
            kickBoxer.put("kick_speed", Integer.parseInt(Kspeed.getText().toString()));
            kickBoxer.put("kick_power", Integer.parseInt(Kpower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        //Toast.makeText(SignUp.this,kickBoxer.get("name")+" succesfully saved in the server", Toast.LENGTH_SHORT).show();
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " successfully saved in the server", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    }
                }
            });
        } catch (Exception e){
            FancyToast.makeText(SignUp.this, "Error Occured: "+e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }



}
