package com.vanlinh;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	protected static final int REQUEST_CODE_INPUT = 1;
	Button btnAdd;
	EditText edtTen, edtToan, edtLy, edtHoa;
	
	ArrayList<Student> arrStudent = new ArrayList<Student>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtTen = (EditText) findViewById(R.id.edtTen);
		edtToan = (EditText) findViewById(R.id.edtToan);
		edtLy = (EditText) findViewById(R.id.edtLy);
		edtHoa = (EditText) findViewById(R.id.edtHoa);

		btnAdd = (Button) findViewById(R.id.btnAdd);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ListActivity.class);
				
				String ten = edtTen.getText().toString();
				int toan = Integer.parseInt(edtToan.getText().toString());
				int ly = Integer.parseInt(edtLy.getText().toString());
				int hoa = Integer.parseInt(edtHoa.getText().toString());
				Student s = new Student(ten, toan, ly, hoa);
				
				arrStudent.add(s);
				
				Bundle bundle = new Bundle();
				bundle.putSerializable("student", arrStudent);
				
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	

}
