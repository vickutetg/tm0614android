package com.hoangphan.tutor1202_cursorloader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class MainActivity extends Activity
			implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int CONTACT_LOADER = 1; //constant
	private static final int IMAGE_LOADER = 2;
	private Cursor contactCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//init loader
		LoaderManager loaderManager = getLoaderManager(); //loader manager (cursor_db, image, video)
		loaderManager.initLoader(CONTACT_LOADER, null, this); //thay the lenh new HeavyImageAsyncTask
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		if(id == CONTACT_LOADER){
			return _contactLoader();
		} else if(id == IMAGE_LOADER){
			//return _imageLoader();
			return null;
		}
		
		return null;
	}

	private CursorLoader _contactLoader() {
	      Uri contactsUri = ContactsContract.Contacts.CONTENT_URI; // uri của contact provider 
	      String[] projection = {    ContactsContract.Contacts.DISPLAY_NAME } ; //chỉ lấy display name 
	      String selection = "display_name LIKE '%ha%'";           //chi lay ha
	      String[] selectionArgs = {};                             
	      String sortOrder = "display_name ASC";                   //sắp xếp 

	      return new CursorLoader(
	              getApplicationContext(), //MainActivity
	              contactsUri, //content://com.android.contacts/contact
	              projection,
	              selection,
	              selectionArgs,
	              sortOrder);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		//hiển thị lên view 
	      List<String> contacts = _contactsFromCursor(cursor);
	      for (String contact : contacts) {
	        Log.d("contact", contact);
	      }
	}

	private List<String> _contactsFromCursor(Cursor cursor) {
		ArrayList<String> contacts = new ArrayList<>();
		while (cursor.moveToNext()) {
			contacts.add(cursor.getString(
					cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))); //get only display_name
			
			// get phone cursor
			//check if has phone
			//print
		}
		
		return contacts;
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		
	}
}
