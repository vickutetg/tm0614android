package com.example.namnh_bcshop;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	static ArrayList<Product> productList = new ArrayList<Product>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initiate the list product at here
		initProductList();
		displayListProduct();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
	}
	
	//////////////////////////////////////
	// Initialize the product and add them to productList
	//////////////////////////////////////
	private void initProductList() {
		Product product1 = new Product("Quan Kaki size 50", 45, "LATER");
		Product product2 = new Product("Vay nu da hoi", 25, "LATER");
		Product product3 = new Product("Giay nam", 19, "LATER");
		
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
	}
	
	//////////////////////////////////////
	// Create ArrayAdapter and set it to ListView 
	//////////////////////////////////////
	private void displayListProduct() {
		ArrayAdapter<Product> productListAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productList);
		
		ListView listProduct = (ListView) findViewById(R.id.listProduct);
		listProduct.setAdapter(productListAdapter);
		
		listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,
					long id) {
				
				Product product = (Product) parent.getItemAtPosition(position);
				
				Intent intent = new Intent(getBaseContext(), CardActivity.class);
				intent.putExtra("SelectedProduct", product);
				
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("SelectedProduct", product);
				
				startActivity(intent);
			}
			
		});
	}
}
