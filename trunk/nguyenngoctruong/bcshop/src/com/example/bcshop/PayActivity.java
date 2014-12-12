package com.example.bcshop;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		
		Intent i = getIntent();
		String total = i.getStringExtra("Total");
		
		EditText edtTotalPay = (EditText) findViewById(R.id.edtTotalPay);
		edtTotalPay.setText(total);
		
		Button btnNew = (Button) findViewById(R.id.btnNew);
		
		btnNew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					FileOutputStream fo = openFileOutput("bill.txt",MODE_PRIVATE);
					fo.write("".getBytes());
					fo.close();

				} catch (FileNotFoundException e) {

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent i = new Intent(PayActivity.this, ProductActivity.class);
				startActivity(i);
				
				
				
			}
		});
		
	}

	
	
	
}
