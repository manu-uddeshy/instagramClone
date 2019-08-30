package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPosts extends AppCompatActivity {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_posts);
        linearLayout = findViewById(R.id.linearlayout);
        Intent recievedIntent = getIntent();
        String recievedUserName= recievedIntent.getStringExtra("username");
        //FancyToast.makeText(UsersPosts.this,recievedUserName,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
        setTitle(recievedUserName+ " 's posts");
        ParseQuery<ParseObject> query= new ParseQuery<ParseObject>("Photo");
        query.whereEqualTo("username",recievedUserName);
        query.orderByDescending("createdAt");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading..");
        progressDialog.show();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                    if(objects.size()>0 && e==null){
                        for(ParseObject object:objects){
                            final TextView desc =new TextView(UsersPosts.this);
                            desc.setText(object.get("desc")+"");
                            ParseFile image = (ParseFile) object.get("pic");
                            image.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(data !=null && e ==null){
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                        ImageView imgview1 = new ImageView(UsersPosts.this);
                                        LinearLayout.LayoutParams imgviewparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                        imgviewparam.setMargins(5,5,5,5);
                                        imgview1.setLayoutParams( imgviewparam);
                                        imgview1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                        imgview1.setImageBitmap(bitmap);

                                        LinearLayout.LayoutParams txtparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                        txtparam.setMargins(5,5,5,5);
                                        desc.setLayoutParams(txtparam);
                                        desc.setGravity(Gravity.CENTER);
                                        desc.setBackgroundColor(Color.BLACK);
                                        desc.setTextColor(Color.WHITE);
                                        desc.setTextSize(30f);

                                        linearLayout.addView(imgview1);
                                        linearLayout.addView(desc);
                                    }else {
                                        FancyToast.makeText(UsersPosts.this,"This user has not posted anything yet",FancyToast.LENGTH_LONG,FancyToast.CONFUSING,true).show();
                                        finish();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }

                else {
                    FancyToast.makeText(UsersPosts.this,"no posts",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    finish();
                }

            }
        });

    }
}
