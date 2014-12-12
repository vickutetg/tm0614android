package com.example.bcshop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Product> {

	Activity context = null;
	ArrayList<Product> myArray = null;
	int layoutId;

	public MyArrayAdapter(Activity context, int layoutId,
			ArrayList<Product> myArray) {
		super(context, layoutId, myArray);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = myArray;
	}
	
	
	public View getView(int position, View convertView, ViewGroup listProduc) {
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		
		final TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
		final TextView txtPrice = (TextView)convertView.findViewById(R.id.txtprice);
		final Product product = myArray.get(position);
		txtName.setText(product.getName());
		String price = product.getPrice() + "$";
		txtPrice.setText(price);
		
		return convertView;
	}

}
