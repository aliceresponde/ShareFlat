package com.example.alice.shareflat;

import android.app.Application;

/**
 * Created by alice on 7/6/16.
 */
public class MyApplication extends Application {

    public static final String FIREBASE_URL = "https://cwtodoapp.firebaseio.com";

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
    }

    private void initFirebase() {

    }
}
