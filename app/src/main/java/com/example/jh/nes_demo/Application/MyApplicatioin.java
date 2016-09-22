package com.example.jh.nes_demo.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Created by jh on 2016/8/22.
 */
public class MyApplicatioin extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();

    }

    public static Context getContext(){
        return context;
    }
}
