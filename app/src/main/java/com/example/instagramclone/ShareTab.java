package com.example.instagramclone;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareTab extends Fragment {
    private ImageView imgView;
    private EditText edtdes;
    private Button btnshare;


    public ShareTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_share_tab, container, false);
        imgView=view.findViewById(R.id.imgview);
        edtdes=view.findViewById(R.id.edtDes);
        btnshare=view.findViewById(R.id.btnShare);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgView.getDrawable()!=null){
                    BitmapDrawable drawable=(BitmapDrawable) imgView.getDrawable();
                    Bitmap imgbitmap=drawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
                    imgbitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                    byte[] bytes= byteArrayOutputStream.toByteArray();
                    ParseFile parseFile= new ParseFile("pic.png",bytes);
                    ParseObject parseObject = new ParseObject("Photo");
                    parseObject.put("pic",parseFile);
                    parseObject.put("desc",edtdes.getText().toString());
                    parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("uploading..");
                    progressDialog.show();
                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                FancyToast.makeText(getContext(),"successfullly uploaded!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }else{
                                FancyToast.makeText(getContext(),"error:"+e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

                }else {
                    FancyToast.makeText(getContext(),"Please select image first",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }

            }
        });
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>23 && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE },1000);
                }else{
                    getChosenImage();
                }

            }
        });

        return  view;
    }

    private void getChosenImage() {
        //FancyToast.makeText(getContext(),"Access Granted!",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        startActivityForResult(intent,2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== 1000){
            getChosenImage();
        }else {
            FancyToast.makeText(getContext(),"permission denired", FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        }
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2000){
            if(resultCode== Activity.RESULT_OK){
                //do somethingwith your captured image
                try{
                    Uri selectedImg=data.getData();
//                    String[] filepathColumn={MediaStore.Images.Media.DATA};
//                    Cursor cursor =getActivity().getContentResolver().query(selectedImg,filepathColumn,null,null,null);
//                    cursor.moveToFirst();
//                    int ColumnIndex=cursor.getColumnIndex(filepathColumn[0]);
//                    String picturepath = cursor.getString(ColumnIndex);
//                    cursor.close();
//                    Bitmap receivedImageBitMap = BitmapFactory.decodeFile(picturepath);
//                    imgView.setImageBitmap(receivedImageBitMap);
                    imgView.setImageURI(selectedImg);
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        }
    }


}

