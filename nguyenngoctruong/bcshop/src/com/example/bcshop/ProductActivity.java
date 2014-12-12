package com.example.bcshop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ProductActivity extends Activity {
	
	final ArrayList<Product> arrBill = new ArrayList<Product>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);

		ListView lvProduct = (ListView) findViewById(R.id.lvProduct);

		final ArrayList<Product> arr = new ArrayList<Product>();
		

		Product p1 = new Product();
		p1.setName("Quan kaki");
		p1.setPrice(45);
		p1.setQuantity(1);

		Product p2 = new Product();
		p2.setName("Giay nam");
		p2.setPrice(19);
		p2.setQuantity(1);

		Product p3 = new Product();
		p3.setName("Vay nu");
		p3.setPrice(25);
		p3.setQuantity(1);

		arr.add(p1);
		arr.add(p2);
		arr.add(p3);

		MyArrayAdapter adapter = new MyArrayAdapter(ProductActivity.this,
				R.layout.listview_layout, arr);

		lvProduct.setAdapter(adapter);

		lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Product product = new Product();
				
				product = arr.get(position);
				
				try {
					FileOutputStream fo = openFileOutput("bill.txt",MODE_APPEND);
					
					String pro = product.getName() + ":" + product.getPrice() + ":" + product.getQuantity() + "\t";											
					fo.write(pro.getBytes());	
					fo.close();

				} catch (FileNotFoundException e) {

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent i = new Intent(ProductActivity.this, BillActivity.class);
				startActivity(i);

			}
		});

	}

}
