package com.NguyenTruong.ext_task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TodoListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);

		final ArrayList<String> todoList = new ArrayList<String>();
		
		FileInputStream fi = null;
		try {
			fi = openFileInput("todoList.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = null;
		try {
			buffer = new byte[fi.available()];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fi.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String work = new String(buffer);
		String[] listWork = work.split("\t");
		
		for (int i = 0; i < listWork.length; i++) {
			todoList.add(listWork[i]);
		}
		

		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				TodoListActivity.this, android.R.layout.simple_list_item_1,
				todoList);
		
		ListView lv = (ListView) findViewById(R.id.lvTodo);
		
		lv.setAdapter(adapter);
		
		
		Button btnAddmore = (Button) findViewById(R.id.btnAddmore);
		btnAddmore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(TodoListActivity.this, MainActivity.class);
				startActivity(i);
				overridePendingTransition(R.drawable.left_in, R.drawable.right_out);
				
			}
		});
		
		Button btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// xóa dữ liệu trong file txt
				FileOutputStream fo = null;
				try {
					fo = openFileOutput("todoList.txt", MODE_PRIVATE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fo.write("".getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					fo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//xóa dữ liệu trong listview
				
				for (int i = todoList.size() - 1; i >= 0; i--) {
					todoList.remove(i);
				}
				
				adapter.notifyDataSetChanged();
				
			}
		});
		
		
		
		
		
		
		

	}
}
