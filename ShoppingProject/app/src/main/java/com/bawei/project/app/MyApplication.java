package com.bawei.project.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author Administrator.
 * @explain
 * @time 2019/03/20 14:51.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Fresco初始化
        Fresco.initialize(this);
    }
}
