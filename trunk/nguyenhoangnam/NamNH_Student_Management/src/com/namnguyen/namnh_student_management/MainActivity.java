package com.namnguyen.namnh_student_management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends Activity {

	static ArrayList<Student> studentList = new ArrayList<Student>();
	ArrayAdapter<Student> arrayAdapter;
	
	class MarkComparator implements Comparator<Student>{
		 
	    @Override
	    public int compare(Student e1, Student e2) {
	        if(e1.getTotal() < e2.getTotal()){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void addStudent(View view) {
    	
    	EditText edtName = (EditText) findViewById(R.id.edtName);
    	EditText edtMath = (EditText) findViewById(R.id.edtMath);
    	EditText edtPhysics = (EditText) findViewById(R.id.edtPhysics);
    	EditText edtChemistry = (EditText) findViewById(R.id.edtChemistry);
    	
    	String 	name = edtName.getText().toString();
    	int 	math = Integer.parseInt(edtMath.getText().toString());
    	int 	physics = Integer.parseInt(edtPhysics.getText().toString());
    	int 	chemistry = Integer.parseInt(edtChemistry.getText().toString());
    	
    	Student student = new Student(name, math, physics, chemistry);
    	
    	studentList.add(student);
    	
    	arrayAdapter =      
                new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1, studentList);
    	
    	edtName.setText("");
    	edtMath.setText("");
    	edtPhysics.setText("");
    	edtChemistry.setText("");
    }
    

    
    public void getList(View view) {
    	
    	Collections.sort(studentList, new MarkComparator());
    	
    	arrayAdapter.notifyDataSetChanged();
    	
    	ListView studentListView=(ListView)findViewById(R.id.listViewMain);
    	
    	studentListView.setAdapter(arrayAdapter);
    	
    	// under construction
//    	Bundle bundle = new Bundle();
//    	Intent intent = new Intent(this, StudentList.class);
//    	
//    	bundle.putSerializable("studentList", studentList);
//    	
//    	startActivity(intent);
    }
    
}


