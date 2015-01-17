package vn.techmaster.hoangphan.tourist_app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hoangpt on 1/16/15.
 */
public class FlickrDao {

    private static final String TAG = "localDB";
    FlickrHelper helper;
    Context app;
    private SQLiteDatabase db;

    private static final String DB_NAME = "flickr_app";
    private static final String DB_TABLE = "photo";
    public static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";

    public static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
            + KEY_ID + " integer primary key autoincrement, id text not null, owner text not null," +
            "secret text not null, server text not null, farm text not null, title text not null," +
            "ispublic text not null, isfriend text not null,  isfamily text not null);";

    private class FlickrHelper extends SQLiteOpenHelper {

        public FlickrHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS photo");
            onCreate(db);
        }
    }

    public FlickrDao(Context app) {
        this.app = app;
        helper = new FlickrHelper(app, DB_NAME, null, DB_VERSION);
        open();
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }


    public ArrayList<Flickphoto> findAllFlickphoto(){
        Cursor c = db.query(DB_TABLE, null, null, null, null, null, null);
        ArrayList<Flickphoto> list = new ArrayList<>();
        while (c.isLast()){
            String id = c.getString(c.getColumnIndex("id"));
            String owner = c.getString(c.getColumnIndex("owner"));
            String secret = c.getString(c.getColumnIndex("secret"));
            String server = c.getString(c.getColumnIndex("server"));
            String title = c.getString(c.getColumnIndex("title"));
            String farm = c.getString(c.getColumnIndex("farm"));
            String ispublic = c.getString(c.getColumnIndex("ispublic"));
            String isfriend = c.getString(c.getColumnIndex("isfriend"));
            String isfamily = c.getString(c.getColumnIndex("isfamily"));
            Flickphoto flickphoto =  new Flickphoto(id, secret, owner, server, title, farm, ispublic, isfriend, isfamily);


            list.add(flickphoto);
            c.moveToNext();
        }

        return  list;
    }

    /**
     * Entity framework
     * @param id
     * @return
     */
    public Flickphoto findByFLickrId(String id){
        //SELECT * FROM photo WHERE id=232; //place holder, sql injection
        Cursor c = db.query(DB_TABLE, null, "id=?", new String[]{id}, null, null, null);
        if(c.moveToFirst()){
            //ORM object relational mapper, mapper: Hibernate, EclipseLink JPA
            String owner = c.getString(c.getColumnIndex("owner"));
            String secret = c.getString(c.getColumnIndex("secret"));
            String server = c.getString(c.getColumnIndex("server"));
            String title = c.getString(c.getColumnIndex("title"));
            String farm = c.getString(c.getColumnIndex("farm"));
            String ispublic = c.getString(c.getColumnIndex("ispublic"));
            String isfriend = c.getString(c.getColumnIndex("isfriend"));
            String isfamily = c.getString(c.getColumnIndex("isfamily"));

            return new Flickphoto(id, secret, owner, server, title, farm,
                    ispublic, isfriend, isfamily);
        }

        return null;
    }

    /**
     * delete 1 photo
     * @param id
     * @return
     */
    public boolean deletePhoto(String id) {
        return db.delete(DB_TABLE, "flickr_id="+id, null) > 0;
    }

    public long insert(Flickphoto photo){
        //chuẩn bị bundle
        ContentValues v = photo.toContent();

        //thực hiện insert
        return db.insert(DB_TABLE, null, v);
    }

    public long update(Flickphoto photo){
        //bundle
        ContentValues v = photo.toContent();
        return db.update(DB_TABLE, v, "id="+photo.id, null);
    }
}
