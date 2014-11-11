package com.hoanghiep.session3tut2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	ArrayList<Job> arr = null;
	MyArrayAdapter adapter = null;
	EditText edtNhap;
	EditText edtHour;
	EditText edtMinute;
	EditText edtSecond;
	Button btnAdd;
	ListView lvShow;
	Button btnDelete;
	Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		edtNhap = (EditText) findViewById(R.id.edtTask);
		edtHour = (EditText) findViewById(R.id.edtHour);
		edtMinute = (EditText) findViewById(R.id.edtMinute);
		edtSecond = (EditText) findViewById(R.id.edtSecond);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		lvShow = (ListView) findViewById(R.id.lvShow);
		arr = new ArrayList<Job>();
		adapter = new MyArrayAdapter(this, R.layout.my_item_layout, arr);
		lvShow.setAdapter(adapter);
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String job = edtNhap.getText().toString();
				int hour = Integer.parseInt(edtHour.getText().toString());
				int minute = Integer.parseInt(edtMinute.getText().toString());
				int second = Integer.parseInt(edtSecond.getText().toString());
				Job jobObject = new Job();
				jobObject.setJob(job);
				jobObject.setHour(hour);
				jobObject.setMinute(minute);
				jobObject.setSecond(second);
				arr.add(jobObject);
				adapter.notifyDataSetChanged();
				edtNhap.setText("");
				edtHour.setText("");
				edtMinute.setText("");
				edtSecond.setText("");

			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = lvShow.getChildCount() - 1; i >= 0; i--) {
					View view = lvShow.getChildAt(i);
					CheckBox cb = (CheckBox) view.findViewById(R.id.chkItem);
					if (cb.isChecked()) {
						arr.remove(i);
					}
				}
				adapter.notifyDataSetChanged();
			}
		});
		btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Iterator<Job> iterator = arr.iterator();
				File file = new File("test.txt");
				try {
					file.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileWriter write;
				BufferedWriter bw = null;
				try {
					write = new FileWriter(file);
					 bw= new BufferedWriter(write);
					while (iterator.hasNext()) {
						Job job1 = (Job) iterator.next();
						String output = job1.getJob() + " " + job1.getHour()
								+ ":" + job1.getMinute() + ":"
								+ job1.getSecond() + "\n";
						bw.write(output);
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
