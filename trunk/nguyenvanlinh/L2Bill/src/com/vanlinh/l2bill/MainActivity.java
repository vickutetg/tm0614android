package com.vanlinh.l2bill;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Button btnAdd, btnSub, btnCalculate;
	Spinner spDrink;
	TextView tvQuantity, tvVAT, tvCalculate;
	
	String[] arrDrink = {"Beer", "Coca", "Pepsi"};
	int[] arrMoney = {10000, 7000, 8000}; 
	String text = "";
	int moneyTotal = 0;
	int quantity = 1;
	int at = 0;// chi vi tri trong mang
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//khoi tao cac widget
		spDrink = (Spinner) findViewById(R.id.spDrink);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnSub = (Button) findViewById(R.id.btnSub);
		btnCalculate = (Button) findViewById(R.id.btnCalculate);
		tvQuantity = (TextView) findViewById(R.id.tvQuantity);
		tvVAT = (TextView) findViewById(R.id.tvVAT);
		tvCalculate = (TextView) findViewById(R.id.tvCalculate);
		
		//do du lieu vao adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrDrink);
		//hien thi danh sach spinner
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		//thiet lap adapter cho spinner
		spDrink.setAdapter(adapter);
		
		//set su kien chon item cho spinner
		spDrink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// arg2 la phan tu duoc chon trong data source
				text = arrDrink[arg2];
				at = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				text = "";
			}
		});
		
		//su kien button Add
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				quantity++;
				tvQuantity.setText("" + quantity);
			}
		});
		
		//su kien button Sub
		btnSub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if( (--quantity) < 1){
					quantity = 1;
				}
				tvQuantity.setText("" + quantity);
			}
		});
		
		//su kien button Calculate
		btnCalculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				moneyTotal = quantity * arrMoney[at] * 110 / 100;
				tvCalculate.setText("" + moneyTotal);
			}
		});
	}

	
}
