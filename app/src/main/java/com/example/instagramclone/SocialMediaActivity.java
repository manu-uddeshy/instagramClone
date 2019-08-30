package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.tabs.TabLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocialMediaActivity extends AppCompatActivity {
private ViewPager viewPager;
private TabAdapter tabAdapter;
private String camerafilepath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
//        SocialMediaActivity.this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewPager= findViewById(R.id.view_pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setupViewPager(ViewPager viewPager){
TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
adapter.addFragment(new ProfileTab(), "Profile");
adapter.addFragment(new UsersTab(), "users");
adapter.addFragment(new ShareTab(),"share");
viewPager.setAdapter(adapter);
    }

   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.postimgitm){
            if(Build.VERSION.SDK_INT>=23 && ActivityCompat.checkSelfPermission(SocialMediaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3000);

            }else{
                captureImage();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==3000){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                captureImage();
            }else{
                FancyToast.makeText(SocialMediaActivity.this,"error: Permission Denied!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
            }
        }
    }
    private File CreatecamImage() throws IOException {
        String TimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String ImageFileName = "JPEG" + TimeStamp +"_";
        // this is the directory in which the file will be created!
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM),"Camera");
        File image= File.createTempFile(
                ImageFileName,".jpg",storageDir);
        //save a file: path for using again
        camerafilepath="file://"+image.getAbsolutePath();
        return image;


    }


    private void captureImage() {
        try{
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(SocialMediaActivity.this, BuildConfig.APPLICATION_ID+".provider",CreatecamImage()));
            startActivityForResult(intent,4000);

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4000){
            if(resultCode== Activity.RESULT_OK){


            }
        }
    } */
}
