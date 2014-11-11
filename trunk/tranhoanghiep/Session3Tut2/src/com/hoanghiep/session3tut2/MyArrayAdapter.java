package com.hoanghiep.session3tut2;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Job> {
	ArrayList<Job> arr = null;
	Activity context;
	int layoutId;

	public MyArrayAdapter(Activity context, int layoutId, ArrayList<Job> arr){
		super(context, layoutId, arr);
		this.context = context;
		this.layoutId = layoutId;
		this.arr = arr;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		final TextView txtdisplay = (TextView) convertView.findViewById(R.id.txtItem);
		final Job job = arr.get(position);
		txtdisplay.setText(job.toString());
		return convertView;
	}

}
