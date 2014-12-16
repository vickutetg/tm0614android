package com.hoangphan.tutor1203_dataprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	}
	
	public void saveBuoi(View v){
		//load động provider 
	    Uri uri = Uri.parse("content://com.hoangphan/buois");
	    ContentResolver resolver = getContentResolver();
	    
	    String buoiName = ((EditText) findViewById(R.id.edtName))
	    						.getText().toString();

	    ContentValues buoiValue = new ContentValues(); //bag, intent data
	    buoiValue.put(BuoiHelper.KEY_NAME, buoiName);
	    resolver.insert(uri, buoiValue);
	    Toast.makeText(this, "Save oK", Toast.LENGTH_LONG).show();
	}
}
