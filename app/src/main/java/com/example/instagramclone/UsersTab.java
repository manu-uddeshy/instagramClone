package com.example.instagramclone;


import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment {
    private TextView txtloading;
    private ListView listview1;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;


    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_users_tab, container, false);
        txtloading= view.findViewById(R.id.txtlaoding);
        listview1 = view.findViewById(R.id.listVIew1);
        arrayList= new ArrayList();
        arrayAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e==null){
                    if(users.size()> 0){
                        for(ParseUser user: users){
                            arrayList.add(user.getUsername());
                        }
                        listview1.setAdapter(arrayAdapter);
                        txtloading.animate().alpha(0).setDuration(2000);
                        listview1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //FancyToast.makeText(getContext(),"it works",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                Intent intent = new Intent(getContext(), UsersPosts.class);
                intent.putExtra("username",arrayList.get(position));
                startActivity(intent);
            }
        });


        return view;
    }


}
