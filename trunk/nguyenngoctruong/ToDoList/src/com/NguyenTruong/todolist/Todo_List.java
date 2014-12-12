package com.NguyenTruong.todolist;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.Collections;

public class Todo_List extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo__list);

		final EditText edtWork = (EditText) findViewById(R.id.edtWork);
		final EditText edtHour = (EditText) findViewById(R.id.edtHour);
		final EditText edtMinute = (EditText) findViewById(R.id.edtMinute);
		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		final ListView lvList = (ListView) findViewById(R.id.lvList);

		// tao ArrayList object
		final ArrayList<String> arrList = new ArrayList<String>();

		// gan data arrList vao ArrayAdapter
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrList);

		// gan adapter vao ListView
		lvList.setAdapter(adapter);

		// set su kien cho nut add
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String edtwork = edtWork.getText().toString();
				String Shour = edtHour.getText().toString();
				String Sminute = edtMinute.getText().toString();
				// int hour = Integer.parseInt(edtHour.getText().toString());
				// int minute =
				// Integer.parseInt(edtMinute.getText().toString());

				if (edtwork.equals("") == true || Shour.equals("") == true
						|| Sminute.equals("") == true) {
					AlertDialog.Builder dialogInput = new AlertDialog.Builder(
							Todo_List.this);
					// tao tieu de
					dialogInput.setTitle("Warning");

					// tao thong bao
					dialogInput
							.setMessage("Your work or ... either blank. You must enter right word.");

					// tao nut OK
					dialogInput.setNeutralButton("OK", null);

					dialogInput.show();
				} else {

					int hour = Integer.parseInt(Shour);
					if (hour < 10) {
						Shour = "0" + hour;
					}

					int minute = Integer.parseInt(Sminute);
					if (minute < 10) {
						Sminute = "0" + minute;
					}

					String work;
					work = Shour + " : " + Sminute + " - "
							+ edtWork.getText().toString();

					arrList.add(work);
					
					adapter.notifyDataSetChanged();
					Collections.sort(arrList);
					edtWork.setText("");
					edtHour.setText("");
					edtMinute.setText("");
				}

			}
		});

		// xoa 1 cong viec bang cach Long click

		lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				arrList.remove(position);
				adapter.notifyDataSetChanged();
				return false;
			}
		});
		

	}

}
