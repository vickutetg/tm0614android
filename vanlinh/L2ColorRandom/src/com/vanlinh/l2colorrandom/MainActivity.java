package com.vanlinh.l2colorrandom;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tvShow;
	private EditText edtName;
	private Button btnRed, btnGreen, btnBlue;
	private TextView tvColor;
	private RadioGroup rdgColors;

	private String hienThi = "Welcome Android World,";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// khoi tao cac widget
		tvShow = (TextView) findViewById(R.id.tvShow);
		edtName = (EditText) findViewById(R.id.edtName);
		btnRed = (Button) findViewById(R.id.btnRed);
		btnGreen = (Button) findViewById(R.id.btnGreen);
		btnBlue = (Button) findViewById(R.id.btnBlue);
		tvColor = (TextView) findViewById(R.id.tvColor);
		rdgColors = (RadioGroup) findViewById(R.id.rdgColor);

		// su kien enter khi nhap du lieu trong edittext
		edtName.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if key down and enter is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// hien thi du lieu len textview
					tvShow.setText(hienThi + edtName.getText().toString());
					return true;
				}
				return false;
			}
		});

		// xu ly su kien cho tung Button
		btnRed.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tvColor.setBackgroundColor(Color.RED);
				toaster("Red");
			}
		});

		btnGreen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tvColor.setBackgroundColor(Color.GREEN);
				toaster("Green");
			}
		});

		btnBlue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tvColor.setBackgroundColor(Color.BLUE);
				toaster("Blue");
			}
		});
		
		//xu ly su kien radio
		rdgColors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int isChecked) {
				if(isChecked == R.id.rdRed){
					tvColor.setBackgroundColor(Color.RED);
					toaster("Red");
				}
				if(isChecked == R.id.rdGreen){
					tvColor.setBackgroundColor(Color.GREEN);
					toaster("Green");
				}
				if(isChecked == R.id.rdBlue){
					tvColor.setBackgroundColor(Color.BLUE);
					toaster("Blue");
				}
			}
		});
		
	}
	
	//toaster
	public void toaster(String name){
		Toast.makeText(MainActivity.this, "Bạn đã chọn màu " + name, Toast.LENGTH_SHORT).show();
	}
	
}
