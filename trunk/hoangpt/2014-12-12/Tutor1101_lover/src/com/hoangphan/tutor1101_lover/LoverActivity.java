package com.hoangphan.tutor1101_lover;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class LoverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lover);

		//parent
		SQLiteDatabase db1 = SQLiteDatabase.openDatabase("/sdcard/test.db", null, SQLiteDatabase.CREATE_IF_NECESSARY);
		
		//db có cấu trúc
		db1.execSQL("INSERT INTO test VALUES (null, 'hotay');");
		db1.execSQL("INSERT INTO test VALUES (null, 'ho vanquan');");
		
		//lover
		SQLiteDatabase db = openOrCreateDatabase("lover.db", MODE_PRIVATE, null);
		
		//transactions
		db.beginTransaction();
		//db.execSQL("DROP TABLE lover;"); 
		//db.execSQL("CREATE TABLE lover (_id INTEGER PRIMARY KEY autoincrement,"+
		//			"name TEXT, phone TEXT);");
		db.execSQL("INSERT INTO lover(`name`,`phone`) VALUES ('hoa', '3434');");
		db.execSQL("INSERT INTO lover VALUES (null, 'quyen', '345646');");
		db.execSQL("UPDATE lover SET name='honghoa' WHERE _id=6;");
		//db.execSQL("INSERT INTO lover VALUES (7, 'mai', '65767');");
		//db.execSQL("INSERT INTO lover VALUES (8, 'huong', '65767');");
		
		db.setTransactionSuccessful();
		db.endTransaction();
		
		//lấy ra 
		//place holder (sql injection) SELECT * FROM lover WHERE _id>5
		Cursor c = db.rawQuery("SELECT * FROM lover WHERE _id > ?", new String[]{"5"});
		c.moveToFirst(); //dong dau tien
		do {
			String name = c.getString(1);
			String phone = c.getString(2);
		
			//log.d va in vao list view
			Log.d("record", name+":"+phone);
		} while (c.moveToNext());
	}
}
