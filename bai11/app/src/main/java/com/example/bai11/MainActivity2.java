package com.example.bai11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient; // THÊM DÒNG NÀY

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Đảm bảo layout này có WebView

        WebView webView = findViewById(R.id.webview1);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        Intent intent = getIntent();
        Uri data = intent.getData();


        if (data != null) {
            try {
                webView.loadUrl(data.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}