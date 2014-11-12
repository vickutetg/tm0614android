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
		final EditText edEmail = (EditText) findViewById(R.id.edEmail);
		final EditText edPass = (EditText) findViewById(R.id.edPass);

		TextView ocLogin = (TextView) findViewById(R.id.ocLogin);
		ocLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String email = edEmail.getText().toString();
				String pass = edPass.getText().toString();

				if (edEmail.getText().toString().equals("abc@gmail.com")
						&& edPass.getText().toString().equals("abc")) {
					Toast.makeText(MainActivity.this, "Successful login",
							Toast.LENGTH_SHORT).show();
				} else if (edEmail.getText().toString().equals("")
						| edPass.getText().toString().equals("")) {
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
