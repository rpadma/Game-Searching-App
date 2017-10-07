package com.etuloser.padma.rohit.homework5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView wv;

    String url=null;
    String title=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wv=(WebView)findViewById(R.id.webview);
        wv.setWebViewClient(new MyBrowser());
        wv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        if(getIntent().getExtras()!=null)
        {
           url=getIntent().getExtras().getString("uurl").toString().trim();
            title=getIntent().getExtras().getString("titlename").toString().trim();

        }


        ConnectivityManager cm=(ConnectivityManager)WebViewActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();

        if(ni!=null && url!=null) {
           // String url = "https://www.youtube.com/watch?v=zvKvnpLgWMQ";
            wv.getSettings().setLoadsImagesAutomatically(true);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            wv.loadUrl(url);
            wv.setVerticalScrollBarEnabled(false);
            wv.setHorizontalScrollBarEnabled(false);

            ((TextView)findViewById(R.id.Wvtxttitle)).setText(title+" Trailer");

        }
        else
        {
            Toast.makeText(this,"Check your wifi/mobile data connection",Toast.LENGTH_SHORT).show();
        }


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }



    }




}
