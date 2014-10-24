package com.hoangphan.tutor0301_westjourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Scr2Activity extends Activity {

	/***********
	 * UI
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d("Screen2", "Screen2 onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		setContentView(R.layout.activity_scr2);
		Log.d("Screen2", "Screen2 onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("Screen2", "Screen2 onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("Screen2", "Screen2 onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("Screen2", "Screen2 onPause");
	}

	@Override
	protected void onStop() {
		Log.d("Screen2", "Screen2 onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d("Screen2", "Screen2 onDestroy");
		super.onDestroy();
	}
	
	/******************
	 * event
	 *******/
	public void goMain(View v){
		//đu từ SCr2 tới Main 
		Intent i = new Intent(Scr2Activity.this, Scr1Activity.class);
		startActivity(i);
	}

}
