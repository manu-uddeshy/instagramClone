package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private Button submitbtn;
    private EditText Ename, Pspeed,Ppower,Kspeed,Kpower;

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
