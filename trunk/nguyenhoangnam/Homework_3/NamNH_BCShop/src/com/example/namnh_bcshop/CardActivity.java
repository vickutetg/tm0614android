package com.example.namnh_bcshop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CardActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_activity);
		getData();
	}
	
	private void getData() {

		Product product = (Product) getIntent().getSerializableExtra("SelectedProduct");
		TextView txtProduct = (TextView) findViewById(R.id.txtProduct);
		txtProduct.setText(product.getProductName());
		
	}
}
