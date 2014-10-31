package com.NguyenTruong.mstudent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import android.R.array;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		ArrayList<Student> arr = new ArrayList<Student>();
		

		// doc du lieu
		try {
			FileInputStream fi = openFileInput("dulieu.txt");

			

			byte[] buffer = new byte[fi.available()];
			fi.read(buffer);
			
			String stu = new String(buffer);
			String[] listStu = stu.split("\t");
			
			for(int i = 0; i < listStu.length; i++){
				Student st = new Student();
				String info = new String(listStu[i]);		
				String[] student_info = info.split(":");
				st.setName(student_info[0]);
				st.setTb(Float.parseFloat(student_info[1]),
						Float.parseFloat(student_info[2]),
						Float.parseFloat(student_info[3]));
				arr.add(st);
			}
			
//			String info = new String(buffer);		
//			String[] student_info = info.split(":");
//			st.setName(student_info[0]);
//			st.setTb(Float.parseFloat(student_info[1]),
//					Float.parseFloat(student_info[2]),
//					Float.parseFloat(student_info[3]));
//			arr.add(st);



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// tao custom list view
		ListView lvStudent = (ListView) findViewById(R.id.lvStudent);

		listStudentAdapter adapter = new listStudentAdapter(ListActivity.this,
				R.layout.liststudent_layout, arr);

		lvStudent.setAdapter(adapter);

		// tao even quay ve activity add student
		Button btnContinue = (Button) findViewById(R.id.btnContinue);

		btnContinue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(ListActivity.this, AddActivity.class);

				startActivity(i);

			}
		});

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
