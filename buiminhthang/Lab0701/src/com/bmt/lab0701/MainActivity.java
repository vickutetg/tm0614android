package com.bmt.lab0701;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView plappybird = (WebView) findViewById(R.id.webview);
		WebSettings setting = plappybird.getSettings();
		
		setting.setJavaScriptEnabled(true);
		setting.setDatabaseEnabled(true);
		
		plappybird.loadUrl("file:///android_asset/flappybird/index.html");
    }
}
