package com.hoangphan.apptestlonglistview;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<String> list = new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			list.add("Hello, android "+i);
		}
		setListAdapter(new WorkAdapter(getBaseContext(), list));
		
		setContentView(R.layout.activity_main);
	}
}
