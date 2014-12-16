package com.hoangphan.tutor1203_dataprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BuoiHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "buoidb";
	public static final String DB_TABLE = "buoi";
	public static final int DB_VERSION = 2;
	
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";

	public static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ID + " integer primary key autoincrement, " + KEY_NAME
			+ " text not null);";
	public static final String DB_DROP = "DROP TABLE "+ DB_TABLE;
	

	public BuoiHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DB_DROP);
	}

}
