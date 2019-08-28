package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;

import com.google.android.material.tabs.TabLayout;

public class SocialMediaActivity extends AppCompatActivity {
private ViewPager viewPager;
private TabAdapter tabAdapter;
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
    private void setupViewPager(ViewPager viewPager){
TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
adapter.addFragment(new ProfileTab(), "Profile");
adapter.addFragment(new UsersTab(), "users");
adapter.addFragment(new ShareTab(),"share");
viewPager.setAdapter(adapter);
    }

}
