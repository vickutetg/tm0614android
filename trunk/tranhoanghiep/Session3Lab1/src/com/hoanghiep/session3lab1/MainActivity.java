package com.hoanghiep.session3lab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

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
		setContentView(R.layout.activity_main);
		btnAt1Start = (Button) findViewById(R.id.btnAt1Start);
		btnAt1Destroy = (Button) findViewById(R.id.btnAt1Destroy);
		btnAt2Start = (Button) findViewById(R.id.btnAt2Start);
		btnAt2Destroy = (Button) findViewById(R.id.btnAt2Destroy);
		btnAt3Start = (Button) findViewById(R.id.btnAt3Start);
		btnAt3Destroy = (Button) findViewById(R.id.btnAt3Destroy);

		txtShow = (TextView) findViewById(R.id.txtShow);

		// activity 1 start
		btnAt1Start.setText("Activity 1 (stop)");
		btnAt1Start.setEnabled(false);

		// activity 2
		btnAt2Destroy.setEnabled(false);
		btnAt2Start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onStop();
			}
		});

		// activity 3
		btnAt3Destroy.setEnabled(false);
		btnAt3Start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onStop();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		txtShow.append("Activity 1 start\n");
		txtShow.setBackgroundColor(Color.parseColor("#D51D00"));
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
		txtShow.append("Activity 1 resume\n");
		txtShow.setBackgroundColor(Color.parseColor("#D51D00"));
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
		Intent i = new Intent("com.hoanghiep.session3lab1.Screen2");
		Bundle b = new Bundle();
		b.putString("activity1", "activity 1 stop");
		i.putExtras(b);
		startActivity(i);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}
}
