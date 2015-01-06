package com.hoangphan.tutor1502_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
	}
	
	public void startService(View v){
		//Intent.Action_View
		Intent service = new Intent("service_download_anh");
		startService(service);
	}
	
	public void stopService(View v){
		stopService(new Intent("com.hoangphan.tutor1502_service.ImageService"));
	}
	
	public void asyncService(View v){
		Intent iService = new Intent(ServiceActivity.this, ImgIntentService.class);
		startService(iService);
	}
	
	public void testScreen(View v){
		startActivity(new Intent(ServiceActivity.this, TestActivity.class));
	}
	
}
