package com.example.lab0702;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tv1 = (TextView) findViewById(R.id.tv1);
		TextView tv2 = (TextView) findViewById(R.id.tv2);
		TextView tv3 = (TextView) findViewById(R.id.tv3);
		TextView tv4 = (TextView) findViewById(R.id.tv4);
		TextView tv5 = (TextView) findViewById(R.id.tv5);
		TextView tv6 = (TextView) findViewById(R.id.tv6);
		TextView tv7 = (TextView) findViewById(R.id.tv7);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/GothaProMed.otf");
		tv1.setTypeface(custom_font);
		tv2.setTypeface(custom_font);
		tv3.setTypeface(custom_font);
		tv4.setTypeface(custom_font);
		tv5.setTypeface(custom_font);
		tv6.setTypeface(custom_font);
		tv7.setTypeface(custom_font);

	}

	public void linear1(View v) {

	}

}
