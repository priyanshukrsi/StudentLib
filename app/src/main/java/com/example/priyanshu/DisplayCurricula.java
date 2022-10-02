package com.example.priyanshu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URLEncoder;

public class DisplayCurricula extends AppCompatActivity {
    WebView pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_curricula);
        pdf = findViewById(R.id.PdfView);
        pdf.getSettings().setJavaScriptEnabled(true);

        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Opening....");
        String file_url = getIntent().getStringExtra("fileurl");

        pdf.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });
        String url = "";
        try {
            url = URLEncoder.encode(file_url,"UTF-8");
        }catch (Exception e){}

        pdf.loadUrl("https://drive.google.com/gview?embedded=true&url=" + url);

    }
}