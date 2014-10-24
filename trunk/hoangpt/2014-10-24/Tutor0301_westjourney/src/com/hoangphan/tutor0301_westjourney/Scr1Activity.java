package com.hoangphan.tutor0301_westjourney;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Scr1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scr1);
		Log.d("Screen1", "Screen1 onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("Screen1", "Screen1 onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("Screen1", "Screen1 onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("Screen1", "Screen1 onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("Screen1", "Screen1 onPause");
	}

	@Override
	protected void onStop() {
		Log.d("Screen1", "Screen1 onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d("Screen1", "Screen1 onDestroy");
		super.onDestroy();
	}	
}
