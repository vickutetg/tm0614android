package com.hoangphan.tutor0203_bttodo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class BtodoActivity extends Activity implements OnClickListener {

	private EditText edtWork;
	private EditText edtHour;
	private EditText edtMinute;
	private ListView lstWork;
	private Button btnAdd;
	private ArrayList<Work> works;
	private ArrayAdapter<Work> adt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_btodo);
		
		//ui
		edtWork = (EditText) findViewById(R.id.edtWork);
		edtHour = (EditText) findViewById(R.id.editHour);
		edtMinute = (EditText) findViewById(R.id.edtMinute);
		lstWork = (ListView) findViewById(R.id.lstWork);
		btnAdd = (Button) findViewById(R.id.button1);

		//adapter for list view
		works = new ArrayList<Work>();
		adt = new ArrayAdapter<Work>(
				BtodoActivity.this, 
				android.R.layout.simple_list_item_1,
				works);
		lstWork.setAdapter(adt);
		
		//event
		btnAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		addWork(v);
	}

	private void addWork(View v) {
		//lay ra tu ui
		String workName = edtWork.getText().toString();
		String hour = edtHour.getText().toString();
		String minute = edtMinute.getText().toString();
		
		//validate, ok != null, add to list
		if(!workName.equals("")){
			//build work
			Work work = new Work(workName, Integer.parseInt(hour), 
					Integer.parseInt(minute));
			works.add(work);
			
			//sort
			Collections.sort(works, new Comparator<Work>() {

				@Override
				public int compare(Work w1, Work w2) {
					return Integer.compare(w1.calulateMinutes(), w2.calulateMinutes());
				}
			});
			adt.notifyDataSetChanged();
			
			//chuyển về ban đầu form 
			edtWork.setText("");
			edtHour.setText("");
			edtMinute.setText("");
			
			edtWork.requestFocus();
		} else {
			//hien thi dialog
			AlertDialog.Builder b = new AlertDialog.Builder(BtodoActivity.this);
			b.setTitle("Nhap sai roi").setMessage("Nhap sai roi")
				.setPositiveButton("OK", null).show();
		}
	}
}
