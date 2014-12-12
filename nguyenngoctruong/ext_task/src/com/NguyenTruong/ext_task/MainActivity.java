package com.NguyenTruong.ext_task;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.NguyenTruong.Model.Work;
import com.NguyenTruong.adapter.MyWorkAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ArrayList<Work> workList = new ArrayList<Work>();
		
		
		final MyWorkAdapter adapter = new MyWorkAdapter(MainActivity.this,
				workList);

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("Error info provided");
		builder.setMessage("Please enter right info !!!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		final EditText edtWork = (EditText) findViewById(R.id.edtWork);
		final EditText edtHour = (EditText) findViewById(R.id.edtHour);
		final EditText edtMinute = (EditText) findViewById(R.id.edtMinute);
		final EditText edtSecond = (EditText) findViewById(R.id.edtSecond);
		final ListView lvWork = (ListView) findViewById(R.id.lvWork);
		lvWork.setAdapter(adapter);
		registerForContextMenu(lvWork);

		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		Button btnDelete = (Button) findViewById(R.id.btnDelete);
		Button btnSave = (Button) findViewById(R.id.btnSave);

		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (edtWork.getText().toString().equals("") == true
						|| edtHour.getText().toString().equals("") == true
						|| edtMinute.getText().toString().equals("") == true
						|| edtSecond.getText().toString().equals("") == true) {

					builder.show();

				} else if (Integer.parseInt(edtHour.getText().toString()) > 24
						|| Integer.parseInt(edtMinute.getText().toString()) > 60
						|| Integer.parseInt(edtSecond.getText().toString()) > 60) {
					builder.show();
				} else {
					String nameWork = edtWork.getText().toString();
					int hour = Integer.parseInt(edtHour.getText().toString());
					int minute = Integer.parseInt(edtMinute.getText()
							.toString());
					int second = Integer.parseInt(edtSecond.getText()
							.toString());

					Work work = new Work();
					work.setWork(nameWork);
					work.setHour(hour);
					work.setMinute(minute);
					work.setSecond(second);

					workList.add(work);

					Collections.sort(workList, new Comparator<Work>() {

						@SuppressLint("NewApi")
						@Override
						public int compare(Work thisWork, Work thatWork) {
							int thisTime = thisWork.getHour() * 60
									+ thisWork.getMinute();
							int thatTime = thatWork.getHour() * 60
									+ thatWork.getMinute();
							return Integer.compare(thisTime, thatTime);
						}
					});

					adapter.notifyDataSetChanged();

					edtHour.setText("");
					edtMinute.setText("");
					edtSecond.setText("");
					edtWork.setText("");
					edtWork.requestFocus();
				}
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = workList.size() - 1; i >= 0; i--) {
					if(workList.get(i).isCheck() == true){
						workList.remove(i);
					}
				}
				
				adapter.notifyDataSetChanged();
				Log.d("check", workList.size() + "");
			}

		});

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < workList.size(); i++) {
					if (workList.get(i).isCheck() == true) {
						FileOutputStream fo = null;
						try {
							fo = openFileOutput("todoList.txt", MODE_APPEND);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Work wS = workList.get(i);
						String workSave = wS.getHour() + ":" + wS.getMinute()
								+ ":" + wS.getSecond() + "  -  " + wS.getWork()
								+ "\t";
						try {
							fo.write(workSave.getBytes());
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
					}
				}
				Intent intent = new Intent(MainActivity.this,
						TodoListActivity.class);
				startActivity(intent);
				overridePendingTransition(R.drawable.right_in,
						R.drawable.left_out);
			}
		});

	}

}
