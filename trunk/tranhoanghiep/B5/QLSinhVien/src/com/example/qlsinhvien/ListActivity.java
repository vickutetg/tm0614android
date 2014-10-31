package com.example.qlsinhvien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {

	public ArrayList<Sinhvien> person;
	public ArrayAdapter<Sinhvien> adapter;
	ListView lv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = new Intent();
		person = (ArrayList<Sinhvien>) i.getSerializableExtra("person");
		Collections.sort(person, new Comparator<Sinhvien>() {

			@Override
			public int compare(Sinhvien sv1, Sinhvien sv2) {
				// TODO Auto-generated method stub
				if (sv1.getTotal() < sv2.getTotal()) {
					return 1;
				} else {
					if (sv1.getTotal() == sv2.getTotal()) {
						return 0;
					} else {
						return -1;
					}
				}
			}
		});

		lv = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<Sinhvien>(this,
				android.R.layout.simple_list_item_1, person);
		lv.setAdapter(adapter);
	}
}
