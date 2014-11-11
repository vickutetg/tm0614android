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
		TextView title_detail = (TextView) findViewById(R.id.textView1);
		TextView tv_count = (TextView) findViewById(R.id.tvCount);
		Button btFlag = (Button) findViewById(R.id.btFlag);
		btFlag.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetailItem.this, MainActivity.class);
				startActivityForResult(i, Constants.REQUEST_FROM_MAIN_TO1);
			}
		});
		
		tv_count.setText(count);
		title_detail.setText(title);
		
		Toast.makeText(DetailItem.this,
				"Detail",
				Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(DetailItem.class +"", "aaaa");
		
	}

}
