package com.hoangphan.tutor0301_westjourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
		
		//lấy ra intent và nội dung của nó 
		Intent i = getIntent();
		String hello = i.getStringExtra("hello");
		int year = i.getIntExtra("year", 0);
		
		//hiển thị 
		Log.d("Bundle", hello);
		Log.d("Bundle", year+"");
		String text = hello + "\n" + "This year is "+year;
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
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
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	/*******
	 * event
	 * @param v
	 */
	public void goMainFrom1(View v){
		//lay ra data va truyen sang Main
		Intent i = new Intent(Scr1Activity.this, MainActivity.class);
		
		//lay ra
		EditText txtName = (EditText) findViewById(R.id.txtName);
		EditText txtAge = (EditText) findViewById(R.id.txtAge);
		String name = txtName.getText().toString();
		int age = Integer.parseInt(txtAge.getText().toString());
		i.putExtra("name", name);
		i.putExtra("age", age);
		
		setResult(RESULT_OK, i);
		this.finish();
	}
}
