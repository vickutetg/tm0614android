package com.hoanghiep.session3lab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Screen2 extends Activity {
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
		setContentView(R.layout.activity_screen2);
		txtShow = (TextView) findViewById(R.id.txtShow);
		btnAt1Start = (Button) findViewById(R.id.btnAt1Start);
		btnAt1Destroy = (Button) findViewById(R.id.btnAt1Destroy);
		btnAt2Start = (Button) findViewById(R.id.btnAt2Start);
		btnAt2Destroy = (Button) findViewById(R.id.btnAt2Destroy);
		btnAt3Start = (Button) findViewById(R.id.btnAt3Start);
		btnAt3Destroy = (Button) findViewById(R.id.btnAt3Destroy);
		
		Intent i = getIntent();
		String activity = i.getStringExtra("activity1");
		
		//activity 1
		txtShow.append(activity + "\n");
		
		//activity 2
		btnAt2Start.setEnabled(false);
		btnAt2Start.setText("activity 2 (stop)");
		txtShow.append("activity 2 create\n");
		txtShow.setBackgroundColor(Color.parseColor("#2B78E4"));
		
		//activity 3
		btnAt3Start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.hoanghiep.session3lab1.Screen3");
				Bundle b = new Bundle();
				b.putString("activity1", "activity 1 stop");
				b.putString("activity2", "activity 2 stop");
				intent.putExtras(b);
				startActivity(intent);
			}
		});
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		txtShow.append("activity 2 start\n");
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
		txtShow.append("activity 2 resume\n");
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
