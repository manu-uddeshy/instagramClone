package com.example.instagramclone;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
private EditText edtprofilename , edtbio,edtprofession,edtHobbies,edtFavSport;
private Button btnUpdate;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtprofilename = view.findViewById(R.id.edtprofilename);
        edtbio= view.findViewById(R.id.edtbio);
        edtprofession=view.findViewById(R.id.edtProfession);
        edtHobbies=view.findViewById(R.id.edtHobbies);
        edtFavSport=view.findViewById(R.id.edtSport);
        btnUpdate= view.findViewById(R.id.btnupdateinfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        edtprofilename.setText(parseUser.get("profile_name")+"");
        edtbio.setText(parseUser.get("bio")+"");
        edtprofession.setText(parseUser.get("profession")+"");
        edtHobbies.setText(parseUser.get("hobbies")+"");
        edtFavSport.setText(parseUser.get("fav_Sport")+"");
        if(parseUser.get("profile_name") == null || parseUser.get("bio")== null || parseUser.get("profession")==null || parseUser.get("hobiies")==null || parseUser.get("fav_sport") ==null ){
           edtprofilename.setText("");
       }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtprofilename.getText().toString().equals("") || edtbio.getText().toString().equals("") || edtprofession.getText().toString().equals("") || edtHobbies.getText().toString().equals("")|| edtFavSport.getText().toString().equals("")){
                    FancyToast.makeText(getContext(),"all fields are required!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();


                }else{
                    parseUser.put("profile_name",edtprofilename.getText().toString());
                    parseUser.put("bio", edtbio.getText().toString());
                    parseUser.put("profession",edtprofession.getText().toString());
                    parseUser.put("hobbies",edtHobbies.getText().toString());
                    parseUser.put("fav_Sport",edtFavSport.getText().toString());


                    parseUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                FancyToast.makeText(getContext(),"successfully updated",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }else{
                                FancyToast.makeText(getContext(),"Error occured: "+e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }

                    });}


            }
        });
        return view;
    }


}
