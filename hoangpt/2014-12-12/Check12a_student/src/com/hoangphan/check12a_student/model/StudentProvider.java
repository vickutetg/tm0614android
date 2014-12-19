package com.hoangphan.check12a_student.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class StudentProvider extends ContentProvider {
	
	public class StudentHelper extends SQLiteOpenHelper {
		private static final String DB_NAME = "amsterdam";
		public static final String DB_TABLE = "student";
		public static final int DB_VERSION = 2;
		
		public static final String KEY_ID = "_id";
		public static final String KEY_NAME = "name";
		public static final String KEY_MATH = "math";
		public static final String KEY_PHYSIC = "physic";
		public static final String KEY_CHEMISTRY = "chemistry";
		

		public static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
				+ KEY_ID + " integer primary key autoincrement, " + KEY_NAME
				+ " text not null, math integer not null, physic integer not null, chemistry integer not null);";
		public static final String DB_DROP = "DROP TABLE "+ DB_TABLE;
		
		public StudentHelper(Context context) {
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
	
	private static final int MANY_STUDENT = 0;
	private static final int ONE_STUDENT = 1;
	
	static String providerName = "amsterdam";
	static String buoiUri = "content://"+providerName +"/students"; //get all
	static Uri CONTENT_URI = Uri.parse(buoiUri);
	
	static UriMatcher matcher;
	private StudentHelper studentHelper;
	private SQLiteDatabase studentDB;
	
	//uri = content://com.hoangphan.buoi/students --> many
	//uri = content://com.hoangphan.buoi/students/2 -->lay cho tao buoi so 2
	//init --> run before constructor
	static {
		//address of data service
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(providerName, "", MANY_STUDENT); // all content://amsterdam/students/
		matcher.addURI(providerName, "#", ONE_STUDENT); // 1  content://amsterdam/students/2
	}
	//dynamic init
	{}

	@Override
	public String getType(Uri uri) {
			switch (matcher.match(uri)) {
			//put into common provider --> content resolver load / cursor loader
			case MANY_STUDENT:
				return "vnd.android.cursor.dir/" + buoiUri;
			case ONE_STUDENT:
				return "vnd.android.cursor.item/" + buoiUri;
			default:
				break;
			}
		return null;
	}
	
	public Context context;
	
	@Override
	public boolean onCreate() {
	    //connect db
	    //Context context = getContext();
	    studentHelper = new StudentHelper(context);
	    studentDB = studentHelper.getWritableDatabase(); //connect sqlite
	    return (studentDB == null) ? false : true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		/**
		 * custome builder
		 */
	    SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
	    sqlBuilder.setTables(StudentHelper.DB_TABLE);
	    
	    //nếu lấy ra 1
	    //content://com.hoangphan/students/1
	    if (matcher.match(uri) == ONE_STUDENT) { //wrapper/helper
	      sqlBuilder.appendWhere(StudentHelper.KEY_ID + " = " + uri.getPathSegments().get(1));
	    }

	    if (sortOrder == null || sortOrder == "") {
	      sortOrder = StudentHelper.KEY_NAME;
	    }
	      
	    Cursor c = sqlBuilder.query(studentDB, projection, selection, selectionArgs,
	        null, null, sortOrder);

	    // thông báo cho uri biết 
	    //c.setNotificationUri(getContext().getContentResolver(), uri);
	    return c;		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//cho thêm vào CSDL 
		long buoiId = studentDB.insert(StudentHelper.DB_TABLE, "", values);
		return Uri.parse("content://amsterdam/students/"+buoiId);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		studentDB.delete(StudentHelper.DB_TABLE, selection, selectionArgs);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		studentDB.update(StudentHelper.DB_TABLE, values, selection, selectionArgs);
		return 0;
	}
}
