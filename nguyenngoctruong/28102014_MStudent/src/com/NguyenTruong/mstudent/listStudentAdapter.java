package com.NguyenTruong.mstudent;

import java.text.DecimalFormat;
import java.util.ArrayList;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class listStudentAdapter extends ArrayAdapter<Student>{
	Activity context = null;
	ArrayList<Student> myArray = null;
	int layoutId;

	public listStudentAdapter(Activity context, int layoutId,
			ArrayList<Student> arr) {
		super(context, layoutId, arr);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = arr;
	}
	
	public View getView(int position, View convertView, ViewGroup listProduc) {
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		DecimalFormat df = new DecimalFormat("#.00");
		
		final TextView txtNameStudent = (TextView)convertView.findViewById(R.id.txtNameStudent);
		final TextView txtTB = (TextView)convertView.findViewById(R.id.txtTB);
		
		Student stu = myArray.get(position);
		txtNameStudent.setText(stu.getName());	
		txtTB.setText(df.format(stu.getTb()) + "");
		
		return convertView;
	}
	
	
}
