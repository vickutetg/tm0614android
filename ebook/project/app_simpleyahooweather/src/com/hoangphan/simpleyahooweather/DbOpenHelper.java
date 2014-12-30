package com.hoangphan.simpleyahooweather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "appDb";
	

	public static final String TABLE_CITIES = "cities";
	public static final String NAME = "name"; //city name
	public static final String ID = "id"; //city yahoo id
	//public static final String STATUS = "status";
	
	private static final String CREATE_TABLE_CITY = "CREATE TABLE " + TABLE_CITIES + " ( " 
																	+ ID + " integer primary key, " + NAME + " text)";
	
	
	public DbOpenHelper (Context context) {
		super (context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate (SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL (CREATE_TABLE_CITY);
	}

	@Override
		public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP DATABASE "+TABLE_CITIES);
	}
}
