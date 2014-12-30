package com.hoangphan.fourpic.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME ="PICS_DB";
    private static final String DICTIONARY_TABLE_NAME = "dictionary";
    
    public static final String COL_ID = "_id";
    public static final String COL_KEYWORD = "KEY_WORD";
    public static final String COL_SHOWWORD = "SHOW_WORD";
    public static final String COL_PIC = "PIC";
    public static final String COL_ANSWERED = "ANSWERED";
	
	protected final Context context;
	protected DictionaryOpenHelper dbHelper;
	protected SQLiteDatabase db;

	public DBAdapter(Context context) {
		this.context = context;
		dbHelper = new DictionaryOpenHelper(this.context);
	}
	
	private class DictionaryOpenHelper extends SQLiteOpenHelper {
	    private static final String DICTIONARY_TABLE_CREATE =
	                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
               		COL_ID + " integer primary key autoincrement not null, " +
	        		COL_KEYWORD + " TEXT, " +
	        		COL_SHOWWORD + " TEXT, " +
	                COL_PIC + " TEXT, " +
	        		COL_ANSWERED +" NUMERIC" +
	                ");";

	    DictionaryOpenHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(DICTIONARY_TABLE_CREATE);
	    }

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		}
	}

	public DBAdapter openWritable() {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public DBAdapter openReadable() {
		db = dbHelper.getReadableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Cursor getKeyWorkd() {
		return db.query(DICTIONARY_TABLE_NAME, null, null, null, null, null, null);
	}
}
