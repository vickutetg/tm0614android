package com.vanlinh;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {

	ListView lvSV;
	ArrayList<Student> arrStudent = null;
	ArrayAdapter<Student> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		lvSV = (ListView) findViewById(R.id.lvSV);
		
		Intent i = getIntent();
		
		Bundle b = i.getExtras();
		arrStudent = (ArrayList<Student>) b.getSerializable("student");
		
		adapter = new ArrayAdapter<Student>(this,
				android.R.layout.simple_list_item_1, arrStudent);
		
		Collections.sort(arrStudent, new SortByStudent());
		
		lvSV.setAdapter(adapter);
		
		registerForContextMenu(lvSV);

	}


	
}
