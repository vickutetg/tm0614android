package com.hoanghiep.session3lab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Screen3 extends Activity {
	Button btnAt1Start;
	Button btnAt1Destroy;
	Button btnAt2Start;
	Button btnAt2Destroy;
	Button btnAt3Start;
	Button btnAt3Destroy;
	TextView txtShow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen3);
		txtShow = (TextView) findViewById(R.id.txtShow);
		btnAt1Start = (Button) findViewById(R.id.btnAt1Start);
		btnAt1Destroy = (Button) findViewById(R.id.btnAt1Destroy);
		btnAt2Start = (Button) findViewById(R.id.btnAt2Start);
		btnAt2Destroy = (Button) findViewById(R.id.btnAt2Destroy);
		btnAt3Start = (Button) findViewById(R.id.btnAt3Start);
		btnAt3Destroy = (Button) findViewById(R.id.btnAt3Destroy);
		
		Intent i = getIntent();
		String activity1 = i.getStringExtra("activity1");
		String activity2 = i.getStringExtra("activity2");
		txtShow.append(activity1 + "\n");
		txtShow.append(activity2 + "\n");
		txtShow.setBackgroundColor(Color.parseColor("#FFFF00"));
		btnAt3Start.setText("Activity 3 (stop)");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		txtShow.append("activity 3 start");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		txtShow.append("activity 3 resume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
