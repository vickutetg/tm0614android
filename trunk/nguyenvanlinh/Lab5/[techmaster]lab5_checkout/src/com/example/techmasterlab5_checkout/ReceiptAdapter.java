package com.example.techmasterlab5_checkout;

import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ReceiptAdapter extends ArrayAdapter<String>{
	
	Activity app;
	
	String[] name = {"Subtotal", "Discount", "Gift", "Total"};
	String[] cost = {"$240", "-$20", "-$80", "-$140"};
	
	public ReceiptAdapter(Context context) {
		super(context, R.layout.listview_receipt);
		addAll(Arrays.asList(name));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		app = (Activity) getContext();
		View row = convertView;
		
		ViewHolder holder;
		
		if( row == null){
			LayoutInflater inflater = app.getLayoutInflater();
			row = inflater.inflate(R.layout.listview_receipt, parent, false);
			
			holder = new ViewHolder();
			
			holder.tvName = (TextView) row.findViewById(R.id.tvName);
			holder.tvCost = (TextView) row.findViewById(R.id.tvCost);
			//set holder cho view
			row.setTag(holder);
		}else{
			holder = (ViewHolder) row.getTag();
		}
		
		holder.tvName.setText(name[position]);
		holder.tvCost.setText(cost[position]);
		
		return row;
	}
	
	public class ViewHolder {
		TextView tvName;
		TextView tvCost;
	}
}
