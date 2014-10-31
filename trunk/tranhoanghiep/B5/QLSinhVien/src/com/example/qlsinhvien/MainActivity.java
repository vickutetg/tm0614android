package com.example.qlsinhvien;

import java.util.ArrayList;

import com.example.qlsinhvien.Sinhvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText editTen;
	EditText editLop;
	EditText editLy;
	EditText editToan;
	Button btnAdd;
	Button btnList;
	public ArrayList<Sinhvien> person =new ArrayList<Sinhvien>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTen = (EditText) findViewById(R.id.editTen);
		editLop = (EditText) findViewById(R.id.editLop);
		editLy = (EditText) findViewById(R.id.editLy);
		editToan = (EditText) findViewById(R.id.editToan);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnList = (Button) findViewById(R.id.btnList);
		btnAdd.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String ten = editTen.getText().toString();
				String lop = editLop.getText().toString();
				float ly = Integer.parseInt(editLy.getText().toString());
				float toan = Integer.parseInt(editToan.getText().toString());
				float total = (ly + toan)/2;
				Sinhvien sinhvien = new Sinhvien();
				sinhvien.setTen(ten);
				sinhvien.setLop(lop);
				sinhvien.setLy(ly);
				sinhvien.setToan(toan);
				sinhvien.setTotal(total);
				person.add(sinhvien);
				editTen.setText("");
				editLop.setText("");
				editLy.setText("");
				editToan.setText("");
			}
		});
		
		btnList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.example.qlsinhvien.LIST");
				startActivity(i);
				Bundle b = new Bundle();
				
				b.putSerializable("person", person);
				i.putExtras(b);
			}
		});
	}
}
