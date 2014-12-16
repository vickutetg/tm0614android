package com.hoangphan.tutor1203_dataprovider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

@SuppressWarnings("deprecation")
public class BuoiActivity extends ListActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_buoi);
		
		//giống như contact 
		//insert hardcode
	    ContentValues buoiValue = new ContentValues(); //bag, intent data
	    buoiValue.put(BuoiHelper.KEY_NAME, "Buoi Dien");
	    ContentResolver resolver = getContentResolver();
	    Uri uri = Uri.parse(BuoiProvider.buoiUri); //constant content://com.hoangphan/buois
	    resolver.insert(uri, buoiValue);	    
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	    Uri uri = Uri.parse("content://com.hoangphan/buois/all"); //constants
	    ContentResolver resolver = getContentResolver();

	    Cursor c = resolver.query(uri, null, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, android.R.layout.simple_list_item_1, c, 
				new String[] {BuoiHelper.KEY_NAME}, 
				new int[] {android.R.id.text1});
		setListAdapter(adapter);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main_menu, menu);
	    return true;		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
	    if (id == R.id.action_add) {
	    		startActivity(new Intent(BuoiActivity.this, AddActivity.class));
	    }
	    
		return true;
	}	
}
