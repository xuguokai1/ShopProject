package com.bawei.project.utils;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static RetrofitUtils retrofitUtils;

    public RetrofitUtils() {
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            //同步锁
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public static OkHttpClient okHttpClient;

    public static synchronized OkHttpClient getOkHttpClient() {
        //创建日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx", message);
            }
        });
        //指定日志拦截器模式
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                //添加日志拦截器
                .addInterceptor(loggingInterceptor)
                .build();
        return okHttpClient;
    }

    //返回Retrofit
    public static Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .baseUrl(url)
                .build();
        return retrofit;
    }

    public <T> T getApiService(String url, Class<T> service) {
        Retrofit retrofit = getRetrofit(url);
        //通过java动态代理模式获取代理对象
        T t = retrofit.create(service);
        return t;
    }
}
