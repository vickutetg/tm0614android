package com.example.bcshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class BillActivity extends Activity {

	ArrayList<Product> arrBill = new ArrayList<Product>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);

		try {
			FileInputStream fi = openFileInput("bill.txt");

			byte[] buffer = new byte[fi.available()];
			fi.read(buffer);

			String pro = new String(buffer);
			String[] listPro = pro.split("\t");

			for (int i = 0; i < listPro.length; i++) {
				Product product = new Product();
				String pro_detail = new String(listPro[i]);
				String[] detail = pro_detail.split(":");
				product.setName(detail[0]);
				product.setPrice(Float.parseFloat(detail[1]));
				product.setQuantity(Integer.parseInt(detail[2]));
				
				arrBill.add(product);

//					for(int j = 0; j < arrBill.size(); j++){
//						if (product.getName().equals(product.getName()) == true){
//							int quantity = arrBill.get(j).getQuantity();
//							arrBill.get(j).setQuantity(quantity++);
//						} else {
//							arrBill.add(product);
//
//						}
//					}

				fi.close();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// Tao list san pham da chon mua
		ListView lvBill = (ListView) findViewById(R.id.lvBill);
		MyBillArrayAdapter adapter = new MyBillArrayAdapter(BillActivity.this,
				R.layout.bill_layout, arrBill);

		lvBill.setAdapter(adapter);
		
		
		final EditText edtTotal = (EditText) findViewById(R.id.edtTotal);
		
		Float Total = (float) 0;
		
		for (int j = 0; j < arrBill.size(); j++){
			Total = Total + (arrBill.get(j).getQuantity() * arrBill.get(j).getPrice());
		}
		
		final DecimalFormat df = new DecimalFormat("#.00");
		
		edtTotal.setText("" + df.format(Total));
		
		
		
		
		

		Button btnContinue = (Button) findViewById(R.id.btnContinue);

		btnContinue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(BillActivity.this, ProductActivity.class);
				startActivity(i);

			}
		});
		
		
		Button btnPay = (Button) findViewById(R.id.btnPay);
		
		btnPay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(BillActivity.this, PayActivity.class);
				String total = edtTotal.getText().toString();
				i.putExtra("Total", total);
				startActivity(i);
				
			}
		});

	}

}
