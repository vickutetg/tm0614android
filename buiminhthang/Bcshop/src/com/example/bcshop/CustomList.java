package com.example.bcshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] title;
	private final String[] cost;

	public CustomList(Activity context, String[] title, String[] cost) {
		super(context, R.layout.item, cost);
		this.context = context;
		this.title = title;
		this.cost = cost;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.item, null, true);

		TextView txtTitle = (TextView) rowView.findViewById(R.id.title);

		TextView txtcost = (TextView) rowView.findViewById(R.id.cost);

		txtTitle.setText(title[position]);

		txtcost.setText(cost[position]);

		return rowView;
	}

}
