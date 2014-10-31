package com.hoangphan.tutor0401_raglay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void showLayout(View v){
		Button b = (Button) v;
		String name = b.getText().toString();
		
		Class<?> cls;
		try {
			cls = Class.forName("com.hoangphan.tutor0401_raglay."+name+"Activity");
			startActivity(new Intent(MainActivity.this, cls));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
