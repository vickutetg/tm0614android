package com.example.nhandangbienso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText editNhap;
	Button btnCheck;
	TextView txtHien;
	String format = "5[0-9]-[A-Z][0-9]-[0-9]{3}\\.[0-9]{2}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editNhap = (EditText) findViewById(R.id.editNhap);
		btnCheck = (Button) findViewById(R.id.btnCheck);
		txtHien = (TextView) findViewById(R.id.txtHien);
		btnCheck.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String nhap = editNhap.getText().toString();
				Pattern pattern = Pattern.compile(format);
				Matcher mavung = pattern.matcher(nhap);
				if (mavung.matches() == true) {
					txtHien.setText("Bảng số đúng dạng của TP-HCM");
				} else {
					txtHien.setText("Bảng số không đúng dạng của TP-HCM");
				}
			}
		});

	}
}
