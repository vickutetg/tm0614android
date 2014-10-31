package com.tuannd.quanlysv;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText edtName, edtHoa, edtLy, edtToan;
	private List<Student> list = new ArrayList<Student>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	void init() {
		edtName = (EditText) findViewById(R.id.id_edtName);
		edtHoa = (EditText) findViewById(R.id.id_edtHoa);
		edtLy = (EditText) findViewById(R.id.id_edtLy);
		edtToan = (EditText) findViewById(R.id.id_edtToan);		
	}
	
	public void clickAdd(View v){
		
	}
	
	public void clickList(View v){
		
	}
}
