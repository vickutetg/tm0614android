package com.NguyenTruong.flappybird;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        WebView wv = (WebView) findViewById(R.id.webView1);
        


        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl("file:///android_asset/floppybird-gh-pages/index.html");
        
    }
}
