package com.hoangphan.tutor1402_fancysms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//register receiver into directory
		IntentFilter filter = new IntentFilter("tour_di_bien");
		receiver = new MyReceiver();
		registerReceiver(receiver, filter);
	}
	
	public void gosea(View v){
		Intent i = new Intent("tour_di_bien");
		i.putExtra("price", 1000);
		sendBroadcast(i);
	}

	@Override
	public void onBackPressed() {
		unregisterReceiver(receiver);
		super.onBackPressed();
	}
}
