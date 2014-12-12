package com.NguyenTruong.ragnarok_layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnTablayout = (Button) findViewById(R.id.btnTblayout);

		btnTablayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						TableLayoutActivity.class);
				startActivity(i);
			}
		});

		Button btnLine = (Button) findViewById(R.id.btnLinelayout);

		btnLine.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, LinearActivity.class);
				startActivity(i);

			}
		});

		Button btnLineW = (Button) findViewById(R.id.btnLinelayoutW);

		btnLineW.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, LinearWithActivity.class);
				startActivity(i);

			}
		});
		
		Button btnRela = (Button) findViewById(R.id.btnRelaLayout);
		btnRela.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, RelativeActivity.class);
				startActivity(i);

			}
		});
		
		Button btnGird = (Button) findViewById(R.id.btnGrilayout);
		btnGird.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, GirdLayoutActivity.class);
				startActivity(i);

			}
		});
		
		
		Button btnAbso = (Button) findViewById(R.id.btnAbslayout);
		btnAbso.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, AbsoluteActivity.class);
				startActivity(i);

			}
		});


	}
}
