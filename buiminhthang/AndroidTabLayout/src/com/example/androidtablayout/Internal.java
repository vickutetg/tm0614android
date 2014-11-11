package com.example.androidtablayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Internal extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_layout);
		Button btWebview, btImageview, btMediaview;
		btWebview = (Button) findViewById(R.id.btWebview);
		btImageview = (Button) findViewById(R.id.btImageview);
		btMediaview = (Button) findViewById(R.id.btMediaview);
		btWebview.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(Internal.this, Callbrowser.class);
				startActivity(i);
			}
		});
		
		btImageview.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(Internal.this, Imageview.class);
				startActivity(i);
			}
		});
		

	}
}
