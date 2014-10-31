package com.hoangphan.tutor0401_raglay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//sleep 1s
				try {
					Thread.sleep(2000);
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
