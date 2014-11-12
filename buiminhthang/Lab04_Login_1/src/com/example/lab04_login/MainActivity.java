package com.example.lab04_login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText edEmail = (EditText) findViewById(R.id.edEmail);
		EditText edPass = (EditText) findViewById(R.id.edPass);

		final String email = edEmail.getText().toString();
		final String pass = edPass.getText().toString();
		final String v_email = "abc@gmail.com";
		final String v_pass = "abc";
		TextView ocLogin = (TextView) findViewById(R.id.ocLogin);
		ocLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (email == v_email && pass == v_pass) {
					Toast.makeText(MainActivity.this, "Successful login",
							Toast.LENGTH_SHORT).show();
				} else if (email == null | pass == null) {
					Toast.makeText(MainActivity.this,
							"Please enter email and password!",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Wrong password!",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}
}
