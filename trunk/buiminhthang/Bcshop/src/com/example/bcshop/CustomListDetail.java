package com.example.bcshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListDetail extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] title_detail;
	private final String[] cost_detail;

	public CustomListDetail(Activity context, String[] title, String[] cost) {
		super(context, R.layout.item_detail, cost);
		this.context = context;
		this.title_detail = title;
		this.cost_detail = cost;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.item, null, true);

		TextView txtTitle = (TextView) rowView.findViewById(R.id.title_detail);

		TextView txtcost = (TextView) rowView.findViewById(R.id.cost_detail);

		txtTitle.setText(title_detail[position]);

		txtcost.setText(cost_detail[position]);

		return rowView;
	}

}
