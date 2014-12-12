package com.NguyenTruong.fodi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		final EditText edtSearch = (EditText) findViewById(R.id.edtSearch);
		Button btnTim = (Button) findViewById(R.id.btnSearchFood);
		
		btnTim.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=getIntent();
				 String value= edtSearch.getText().toString();
				 intent.putExtra("data", value);
				 setResult(MainActivity.RESULT_CODE_SAVE, intent);
				 finish();
			}
		});
		
	}
}
