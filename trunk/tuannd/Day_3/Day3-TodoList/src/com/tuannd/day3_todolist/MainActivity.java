package com.tuannd.day3_todolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private EditText textWork, textHour, textMinute;
	private ListView listView;
	private List<String> data = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private AlertDialog.Builder builder;

	private String strWork = "Your Work is blank! ";
	private String strHour = "Your Hour is blank! ";
	private String strHour0_23 = "Your Hour must is number 0 to 23! ";
	private String strMinute = "Your Minute is blank! ";
	private String strMinute0_59 = "Your Minute must is number 0 to 59! ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {
		textWork = (EditText) findViewById(R.id.id_edtxtWork);
		textHour = (EditText) findViewById(R.id.id_edtxtHour);
		textMinute = (EditText) findViewById(R.id.id_edtxtMinute);		
		listView = (ListView) findViewById(R.id.id_listviewTodo);

		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder builder;
				builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setTitle("Attention");
				builder.setMessage("Is Work " + data.get(position) + " completed?\nDo You want remove it from list?");
				
				builder.setNegativeButton("Cancel",	new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {								
							}
						});
				builder.setPositiveButton("OK",	new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								data.remove(position);
								adapter.notifyDataSetChanged();
							}
						});
				builder.show();
				return false;
			}
		});

		builder = new AlertDialog.Builder(MainActivity.this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("Error");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void ClickAdd(View v) {
		String work = textWork.getText().toString();
		String hour = textHour.getText().toString();
		String minute = textMinute.getText().toString();
		if (work.equals("")) {
			builder.setMessage(strWork);
			builder.show();
			return;
		} else if (hour.equals("")) {
			builder.setMessage(strHour);
			builder.show();
			return;
		} else if (Integer.parseInt(hour) > 23) {
			builder.setMessage(strHour0_23);
			builder.show();
			return;
		} else if (minute.equals("")) {
			builder.setMessage(strMinute);
			builder.show();
			return;
		} else if (Integer.parseInt(minute) > 59) {
			builder.setMessage(strMinute0_59);
			builder.show();
			return;
		}

		hour = validateNumber(hour);
		minute = validateNumber(minute);
		String item = hour + ":" + minute + " - " + work;
		data.add(item);
		Collections.sort(data);
		adapter.notifyDataSetChanged();
		clearForm();
	}

	private String validateNumber(String inStr) {
		int result = Integer.parseInt(inStr);
		if (result < 10) {
			return "0" + result;
		}
		return "" + result;
	}

	private void clearForm() {
		textWork.setText("");
		textHour.setText("");
		textMinute.setText("");
	}

}
