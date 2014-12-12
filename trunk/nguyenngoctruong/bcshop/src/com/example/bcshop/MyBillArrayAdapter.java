package com.example.bcshop;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MyBillArrayAdapter extends ArrayAdapter<Product> {

	Activity context = null;
	ArrayList<Product> myArray = null;
	int layoutId;

	public MyBillArrayAdapter(Activity context, int layoutId,
			ArrayList<Product> myArray) {
		super(context, layoutId, myArray);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = myArray;
	}
	
	public View getView(int position, View convertView, ViewGroup listProduc) {
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		
		final TextView txtPnameBill = (TextView)convertView.findViewById(R.id.txtPnameBill);
		final EditText edtPquantity = (EditText)convertView.findViewById(R.id.edtPquantity);
		final Product product = myArray.get(position);
		txtPnameBill.setText(product.getName());
		edtPquantity.setText(product.getQuantity()+"");
		
		return convertView;
	}

}
