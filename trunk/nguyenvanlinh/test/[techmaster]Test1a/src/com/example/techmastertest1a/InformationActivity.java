package com.example.techmastertest1a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class InformationActivity extends Activity {

	EditText edtName, edtCMT, edtBoSung;
	Button btnGuiTT;
	RadioGroup rdgSchool;
	CheckBox cbSach, cbBao, cbCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		edtName = (EditText) findViewById(R.id.edtName);
		edtCMT = (EditText) findViewById(R.id.edtCMT);
		edtBoSung = (EditText) findViewById(R.id.edtBoSung);
		
		btnGuiTT = (Button) findViewById(R.id.btnGuiTT);
		
		rdgSchool = (RadioGroup) findViewById(R.id.rdgSchool);
		
		cbSach = (CheckBox) findViewById(R.id.cbSach);
		cbBao = (CheckBox) findViewById(R.id.cbBao);
		cbCode = (CheckBox) findViewById(R.id.cbCoding);
		
		
		//validate
		int soCMT = edtCMT.getText().length();
		if( soCMT > 9){
			Toast.makeText(this, "CMT chỉ có 9 số", Toast.LENGTH_SHORT).show();
		}
		
		
		
		// button
		btnGuiTT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Thông tin để hiển thị lên Dialog
				String message = "";
				
				message += edtName.getText().toString() + "\n";
				message += edtCMT.getText().toString() + "\n";
				
				// radio
				int isChecked = rdgSchool.getCheckedRadioButtonId();
				String radioCheck = "";
				switch( isChecked ){
				case R.id.rdTrungcap:
					radioCheck = "Trung Cấp";
					break;
				case R.id.rdCaodang:
					radioCheck = "Cao Đẳng";
					break;
				case R.id.rdDaihoc:
					radioCheck = "Đại Học";
					break;
				}
				
				message += radioCheck + "\n";
				
				String checkbox = "";
				if( cbSach.isChecked() ){
					checkbox += "Đọc Sách";
				}
				if( cbBao.isChecked() ){
					checkbox += "Đọc Báo";
				}
				if( cbCode.isChecked() ){
					checkbox += "Đọc Coding";
				}
				
				message += checkbox +"\n" + "---------------------" + "\n";
				// Thông tin bổ sung
				String bosung = edtBoSung.getText().toString();
				message += bosung;
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
				builder.setTitle("Thông tin cá nhân?")
				.setMessage(message)
				.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
						
					}
				})
				
				
				.create().show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_exit) {
			finish();
			return true;
		}
		
		if( id == R.id.profile) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
			builder.setTitle("Thông tin cá nhân?")
			.setMessage("Are you sure you want to exit?")
			.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					dialog.cancel();
					
				}
			})
			
			
			.create().show();
			
		}
		
		if ( id ==  R.id.book_info ){
			startActivity( new Intent( InformationActivity.this, ReceiptActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
}
