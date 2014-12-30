package com.hoangphan.simpleyahooweather;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DbConnection {
	private DbOpenHelper mDbOpenHelper;
	private SQLiteDatabase mDb;
	private ContentValues mCv;
	private Cursor mCursor;
	
	public DbConnection (Context context, Cursor cursor) {
		this.mDbOpenHelper = new DbOpenHelper(context);
		this.mDb = mDbOpenHelper.getWritableDatabase();
		this.mCv = new ContentValues();
		this.mCursor = cursor;
		
		addDefaultCities(); //id - primary key, so will be added once
	}
	
	private void addDefaultCities () {
		addCity("Ha Noi", 91888417);
		addCity("London", 44418);
		addCity("New York", 2459115);
	}
	
	public void addCity (String name, int id) {
		mCv.put(DbOpenHelper.ID, id);
		mCv.put(DbOpenHelper.NAME, name);
		mDb.insert(DbOpenHelper.TABLE_CITIES, null, mCv);
		mCv.clear();
	}
	
	public List<City> getCities () {
		List<City> cities = new ArrayList<City> ();
		mCursor =  mDb.rawQuery ("SELECT * FROM " + DbOpenHelper.TABLE_CITIES, null);
				
		int total = mCursor.getCount();
		
		if (mCursor.moveToFirst()) {
			for (int i = 0; i < total; i++) {
				String name = mCursor.getString(mCursor.getColumnIndexOrThrow(DbOpenHelper.NAME));
				int id = mCursor.getInt(mCursor.getColumnIndexOrThrow(DbOpenHelper.ID));
				cities.add(new City(id, name));
				mCursor.moveToNext();
			}
		}
		
		return cities;
	}
	
}
