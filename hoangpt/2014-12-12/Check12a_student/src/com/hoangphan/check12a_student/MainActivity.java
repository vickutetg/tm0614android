package com.hoangphan.check12a_student;

import java.util.ArrayList;

import com.hoangphan.check12a_student.model.Student;
import com.hoangphan.check12a_student.model.StudentDao;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//logic
		StudentDao dao = new StudentDao(getBaseContext());
		//Student s1 = new Student(0, "Hoang", 5, 3, 6);
		//dao.insert(s1);
		
		ArrayList<Student> list = dao.findAll();
		ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
	}
}
