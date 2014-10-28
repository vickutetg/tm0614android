package com.hoangphan.tutor0301_westjourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageButton btnTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("Main", "Main onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("Main", "Main onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("Main", "Main onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("Main", "Main onResume");
		
		btnTest = (ImageButton) findViewById(R.id.btnTest);
		btnTest.setOnClickListener(new TestListener("hoang"));
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("Main", "Main onPause");
	}

	@Override
	protected void onStop() {
		Log.d("Main", "Main onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d("Main", "Main onDestroy");
		super.onDestroy();
	}
	
	/********************
	 * for test event
	 * @author hoangpt
	 *
	 */
	class TestListener implements OnClickListener {
		
		String name;

		public TestListener(String name) {
			this.name = name;
		}

		@Override
		public void onClick(View v) {
			goScreen2(v);
		}
	}

	public void goScreen2(View v) {
		//có dự định đi
		Intent i = new Intent("com.hoangphan.Screen1");
		
		//vali
		Bundle b = new Bundle();
		b.putString("hello", "Hello");
		b.putInt("year", 2014);
		i.putExtras(b);
		
		//đi
		startActivity(i);
	}
	
	long beforeTimestamp = 0;
	long afterTimestamp = 0;
	int num = 0;
	
	@Override
	public void onBackPressed() {
		num+= 1; //tang len 1
		
		afterTimestamp = System.currentTimeMillis();
		long duration = (afterTimestamp - beforeTimestamp)/1000;
		if(num > 1){
			if(duration>3){
				num = 0;
			} else {
				super.onBackPressed();
			}
		} else {
			beforeTimestamp = afterTimestamp;
			Toast.makeText(MainActivity.this, 
					"Do you really want to exit", Toast.LENGTH_LONG).show();
		}
	}
	
	public void goScr1FromMain(View v){
		//shortcut 
		Intent i = new Intent(MainActivity.this, Scr1Activity.class);
		i.putExtra("hello", "Good bye");
		i.putExtra("year", -3);
		
		startActivityForResult(
				i, 
				Constants.REQUEST_FROM_MAIN_TO1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i) {
		//if(resultCode == Constants.RESPONSE_FROM1_TO_MAIN){
			Bundle b = i.getExtras();
			String name = b.getString("name");
			int age = b.getInt("age");
			Toast.makeText(MainActivity.this, "name:"+name+" - age:"+age, Toast.LENGTH_LONG).show();
		//}
	}
}
