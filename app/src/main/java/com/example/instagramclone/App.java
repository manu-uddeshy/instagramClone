package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nEBNb9Ldv1KE22rCYo8fo2bDKLqCzD4xHiyGOZcf")
                // if defined
                .clientKey("XnRsYQ3vmB1mUX5kk4zYKoLRASDMijPim3t2FLfE")
                .server("https://parseapi.back4app.com//")
                .build());

    }
}
