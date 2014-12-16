package com.hoangphan.tutor1102_caveman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CaveDao  {

	CaveHelper helper;
	Context app;
	private SQLiteDatabase db;

	private static final String DB_NAME = "caveman";
	private static final String DB_TABLE = "caves";
	public static final int DB_VERSION = 1;
	
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";

	public static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ID + " integer primary key autoincrement, " + KEY_NAME
			+ " text not null);";

	private class CaveHelper extends SQLiteOpenHelper {

		public CaveHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//upgrade: CREATE TABLE, ALTER,... (sql)
		}
	}

	public CaveDao(Context app) {
		this.app = app;
		helper = new CaveHelper(app, DB_NAME, null, DB_VERSION);
		
		//connection
		open();
	}
	
	public void open() {
		db = helper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public Cursor findall(){
		//SELECT * FROM caves
		Cursor c = db.query(
				DB_TABLE, 
				new String[] {KEY_ID, KEY_NAME}, null, null, null, null, null);
		return c;
	}
	
	public void insert(String name){
		//chuẩn bị bundle 
		ContentValues v = new ContentValues();
		v.put("name", name);
		
		//thực hiện insert 
		db.insert(DB_TABLE, null, v);
	}
	
}
