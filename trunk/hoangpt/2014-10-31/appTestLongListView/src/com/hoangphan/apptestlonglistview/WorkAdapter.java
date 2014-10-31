package com.hoangphan.apptestlonglistview;

import java.util.ArrayList;
import java.util.Random;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class WorkAdapter extends ArrayAdapter<String> {
	Context app;
	ArrayList<String> list;
	
	public WorkAdapter(Context context, ArrayList<String> list) {
		super(context, R.layout.work_custom, list);
	    this.app = context;
	    this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View row = inflater.inflate(R.layout.work_custom, parent, false);
	    
	    TextView name = (TextView) row.findViewById(R.id.workName);
	    TextView time = (TextView) row.findViewById(R.id.workTime);
	    CheckBox check = (CheckBox) row.findViewById(R.id.chk);
	    
	    check.setOnClickListener(new CheckListener(position));
	    int checked = WorkApplication.getCheck(position);
	    Log.d("checked", ""+checked);
	    if(checked == 1){
	    		check.setChecked(true);
	    } else {
	    		check.setChecked(false);
	    }
	    
	    
	    name.setText(list.get(position));
	    
	    Random r = new Random();
	    int hour = r.nextInt(24);
	    int minute = r.nextInt(60);
	    time.setText(hour+":"+minute);
	    
	    return row;
	}
}
