package com.hoangphan.tutor0801_2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView wv2048 = (WebView) findViewById(R.id.wv2048);
		WebSettings setting = wv2048.getSettings();
		
		setting.setJavaScriptEnabled(true);
		setting.setDatabaseEnabled(true);
		
		wv2048.loadUrl("file:///android_asset/2048/index.html");
	}
}
