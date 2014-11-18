package com.example.techmastertest1a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	EditText edtEmail, edtPass;
	Button btnLogin, btnExit;
	CheckBox cbSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
	}

	private void init() {
		
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtPass = (EditText) findViewById(R.id.edtPass);
		
		cbSave = (CheckBox) findViewById(R.id.cbSave);
		
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnExit = (Button) findViewById(R.id.btnExit);
		
		//xu ly su kien
		btnExit.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		if( v.getId() == R.id.btnLogin){
			
			if( edtEmail.getText().toString().trim().equals("") ||
					edtPass.getText().toString().trim().equals("") ){
				Toast.makeText(this, "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
			}else{
				startActivity(new Intent(MainActivity.this, InformationActivity.class));
			}
			
			
			
		}
		
		
		
		if( v.getId() == R.id.btnExit){
			
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("Question?")
			.setMessage("Are you sure you want to exit?")
			.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					dialog.cancel();
					
				}
			})
			
			.setPositiveButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					dialog.dismiss();
					
				}
			})
			.create().show();
		}
		
	}
	
	

	
}
