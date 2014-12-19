package com.hoangphan.tutor1201_contact.provider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/*
 * already define listview
 */
public class ContactActivity extends ListActivity {

	private Cursor contactCursor;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		//lay ra name
		String[] projection = new String[] { 
				BaseColumns._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER,
				ContactsContract.Contacts.PHONETIC_NAME
				}; 
		
		//uri = content://com.android.contacts/contacts
		//websevice http://campaign.api/all
		//display all
		contactCursor = managedQuery(
				ContactsContract.Contacts.CONTENT_URI, //content://com.android.contacts/contacts --> get all contacts 
				projection, 					 //lấy ra 4 trường 
				"display_name LIKE '%hoang phan%'",  //hiển thị tên mà có chữ ha 
				null, 						 //không sử dụng argument 
				ContactsContract.Contacts.DISPLAY_NAME+" ASC" //sắp xếp 
				);
		
		//adapter (same as adapter)
		String[] from = new String[] { ContactsContract.Contacts.DISPLAY_NAME, "_id" }; //data binding
		int[] to = new int[] { android.R.id.text1,  android.R.id.text2 }; //view using data
		SimpleCursorAdapter adapter = new SimpleCursorAdapter( //map from cursor to column
				this, 
				android.R.layout.simple_list_item_2, 
				contactCursor, 
				from, 
				to
				);
		setListAdapter(adapter);
		
		//print phone
//		LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout1);
//		ViewTreeObserver vto = layout.getViewTreeObserver(); 
//		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { 
//		    @Override 
//		    public void onGlobalLayout() { 
//		        _printPhone();
//		    } 
//		});	
	}
	
	public void loadPhone(View v){
		_printPhone();
	}
	
	//same as yesterday
	private void _printPhone() {
		//C1: read name ->phone ok
		//C2: in phone after render list
		int position = -1;
		contactCursor.moveToPosition(position);
		
		//list
		ListView list = (ListView) findViewById(android.R.id.list);
		//same as cursor of helper
		while (contactCursor.moveToNext()) { //position = 0
			position++;
			ViewGroup vg = (ViewGroup) list.getChildAt(position);
			TextView text2 = (TextView) vg.findViewById(android.R.id.text2);
			TextView text1 = (TextView) vg.findViewById(android.R.id.text1);
			
			String name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			int id = contactCursor.getInt(contactCursor.getColumnIndex(BaseColumns._ID));
			int hasPhone = contactCursor.getInt(contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

			text1.setText(id + ":"+ name); //set line1 = name
			
			if (hasPhone > 0) {
				//data service resolver
				ContentResolver resolver = getContentResolver();
				Cursor phoneCursor = resolver.query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
						null,  //lấy ra tất cả các trường 
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", //tìm các phone có contact_id giống trên 
						new String[] { ""+id }, 
						null);
				
				String phoneNrs = ""; //get phone cursor
				while (phoneCursor.moveToNext()) {
					String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					phoneNrs += phone+",";
				}
				
				text2.setText(phoneNrs);
				Log.d("phone", name+" has id = "+id+" and has phones is: "+phoneNrs);
				phoneCursor.close();
			} else {
				Log.d("phone", name+" has id = "+id+" and do not have phone number");
				text2.setText("No phone");
			}			
		}
		//contactCursor.close();
	}
}
