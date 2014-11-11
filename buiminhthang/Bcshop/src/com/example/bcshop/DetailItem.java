package com.example.bcshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailItem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_item);
		Intent i = getIntent();
		String title = i.getStringExtra("TITLE");
		String count = i.getStringExtra("COUNT");
		String arr_title[] = { title };
		String arr_count[] = { count };
		CustomListDetail adapter = new CustomListDetail(DetailItem.this,
				arr_title, arr_count);
		Button btFlag = (Button) findViewById(R.id.btFlag);
		btFlag.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DetailItem.this, MainActivity.class);
				startActivityForResult(intent, Constants.REQUEST_FROM_MAIN_TO1);
			}
		});

		Toast.makeText(DetailItem.this, "Detail", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(DetailItem.class + "", "onDestroy");

	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// Bundle b = data.getExtras();
	// }

}
