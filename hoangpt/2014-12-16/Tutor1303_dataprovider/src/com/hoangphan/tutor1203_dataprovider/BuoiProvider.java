package com.hoangphan.tutor1203_dataprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class BuoiProvider extends ContentProvider {
	
	private static final int MANY_BUOI = 0;
	private static final int ONE_BUOI = 1;
	private static final int ADD_BUOI = 2;
	
	static String providerName = "com.hoangphan";
	static String buoiUri = "content://"+providerName +"/buois"; //get all
	static Uri CONTENT_URI = Uri.parse(buoiUri);
	
	static UriMatcher matcher;
	private BuoiHelper buoiHepler;
	private SQLiteDatabase buoiDB;
	
	//uri = content://com.hoangphan.buoi/buois --> many
	//uri = content://com.hoangphan.buoi/buois/2 -->lay cho tao buoi so 2
	//init --> run before constructor
	static {
		//address of data service
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(providerName, "buois/all", MANY_BUOI); // all content://com.hoangphan.buois/buois/all
		matcher.addURI(providerName, "buois/#", ONE_BUOI); // 1  content://com.hoangphan.buois/buois/1
		matcher.addURI(providerName, "buois/add", ADD_BUOI);
	}
	//dynamic init
	{}

	@Override
	public String getType(Uri uri) {
			switch (matcher.match(uri)) {
			//put into common provider --> content resolver load / cursor loader
			case MANY_BUOI:
				return "vnd.android.cursor.dir/" + buoiUri;
			case ONE_BUOI:
				return "vnd.android.cursor.item/" + buoiUri;
			default:
				break;
			}
		return null;
	}
	
	@Override
	public boolean onCreate() {
	    //connect db
	    Context context = getContext();
	    buoiHepler = new BuoiHelper(context);
	    buoiDB = buoiHepler.getWritableDatabase(); //connect sqlite
	    return (buoiDB == null) ? false : true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		/**
		 * custome builder
		 */
	    SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
	    sqlBuilder.setTables(BuoiHelper.DB_TABLE);
	    
	    //nếu lấy ra 1
	    //content://com.hoangphan/buois/1
	    if (matcher.match(uri) == ONE_BUOI) { //wrapper/helper
	      sqlBuilder.appendWhere(BuoiHelper.KEY_ID + " = " + uri.getPathSegments().get(1));
	    }

	    if (sortOrder == null || sortOrder == "") {
	      sortOrder = BuoiHelper.KEY_NAME;
	    }
	      
	    Cursor c = sqlBuilder.query(buoiDB, projection, selection, selectionArgs,
	        null, null, sortOrder);

	    // thông báo cho uri biết 
	    c.setNotificationUri(getContext().getContentResolver(), uri);
	    return c;		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//cho thêm vào CSDL 
		long buoiId = buoiDB.insert(BuoiHelper.DB_TABLE, "", values);
		return Uri.parse("content://com.hoangphan.buois/"+buoiId);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		buoiDB.delete(BuoiHelper.DB_TABLE, selection, selectionArgs);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		buoiDB.update(BuoiHelper.DB_TABLE, values, selection, selectionArgs);
		return 0;
	}

}
