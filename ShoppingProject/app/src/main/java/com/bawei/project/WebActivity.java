package com.bawei.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webview = findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());

    }
}
