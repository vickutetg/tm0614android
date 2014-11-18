package com.hoanghiep.test1techmaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText edtUser;
	EditText edtPass;
	Button btnLogin;
	Button btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		edtUser = (EditText) findViewById(R.id.edtUser);
		edtPass = (EditText) findViewById(R.id.edtPass);
		btnExit = (Button) findViewById(R.id.btnExit);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnExit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						LoginActivity.this);
				dialog.setTitle("Question?");
				dialog.setMessage("Are you sure want to exit?");
				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						});
				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				dialog.create().show();
			}
		});
		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String user = edtUser.getText().toString();
				String pass = edtPass.getText().toString();
				if (user.compareTo("hoanghiep") != -32
						&& pass.compareTo("123456") != -32) {
					Intent i = new Intent("android.intent.action.Profile");
					startActivity(i);
				}
			}
		});
	}
}
