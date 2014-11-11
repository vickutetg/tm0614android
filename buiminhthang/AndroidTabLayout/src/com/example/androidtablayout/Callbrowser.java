package com.example.androidtablayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Callbrowser extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		WebView browser = (WebView) findViewById(R.id.webview);
		browser.loadUrl("https://www.google.com.vn/");
		browser.getSettings().setJavaScriptEnabled(false);
	}

}
