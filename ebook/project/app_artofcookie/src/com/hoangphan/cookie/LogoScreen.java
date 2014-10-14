package com.hoangphan.cookie;       
       
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoScreen extends Activity {

    // Splash screen timer
	private static int SPLASH_TIME_OUT = 5000; // 5 seconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);

		// make a thread that will be run after the value specified in
		// SPLASH_TIME_OUT
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(LogoScreen.this, ImagesView.class);
				startActivity(i);
				// close this activity
				finish(); // close logo screen
			}
		}, SPLASH_TIME_OUT);
	}

}
