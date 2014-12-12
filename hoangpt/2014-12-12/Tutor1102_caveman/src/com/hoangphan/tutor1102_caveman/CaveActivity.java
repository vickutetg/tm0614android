package com.hoangphan.tutor1102_caveman;

import java.util.Collection;
import java.util.HashMap;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class CaveActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//thực hiện giống trước 
		CaveDao dao = new CaveDao(this);
		dao.insert("hang doi");
		dao.insert("hang bacpo");
		
		//hiển thị ra list nhé 
		Cursor c = dao.findall();
		HashMap<Integer, String> map = new HashMap<>(); //mapper, ORM

		do {
			c.move(1);
			String caveName = c.getString(c.getColumnIndex("name")); //get theo ten cot
			int id = c.getInt(c.getColumnIndex("_id")); //get Integer, Boolean, Long,...
			map.put(Integer.valueOf(id), caveName);
		} while (!c.isLast());

		Collection<String> names = map.values();
		ArrayAdapter<String> adapter = new ArrayAdapter<>(
				this, 
				android.R.layout.simple_list_item_1);
		adapter.addAll(names);
		
		setListAdapter(adapter);
		dao.close();
	}
}
