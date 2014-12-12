package com.NguyenTruong.sweet_dream;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GeneralActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general);
		
		String[] RingMode = {"Sleep", "Loud", "Normal"};
		
		Spinner spnRingMode = (Spinner) findViewById(R.id.spnRingMode);
		
		ArrayAdapter<String> adapterRingMode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, RingMode);
		
		spnRingMode.setAdapter(adapterRingMode);
		
	}
}
