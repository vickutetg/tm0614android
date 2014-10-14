package com.hoangphan.hotgirl;

import com.hoangphan.hotgirl.adapter.AlbumsListAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AlbumsListActivity extends Activity
{
	private Context mContext;
	private Bitmap mBitmap = null;
	private BitmapDrawable mBitmapRepeat = null;
	
	private LinearLayout layoutRepeatLine;
	private ListView listview;

	private AlbumsListAdapter adapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albums_list_activity); 
        layoutRepeatLine = (LinearLayout)findViewById(R.id.albums_activity_layout_line_repeat);
        listview = (ListView)findViewById(R.id.albums_activity_list_view);
        mContext = this;
        
        mBitmap 		= BitmapFactory.decodeResource(getResources(), R.drawable.line_none);
        mBitmapRepeat 	= new BitmapDrawable(mBitmap);
        mBitmapRepeat.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
       	layoutRepeatLine.setBackgroundDrawable(mBitmapRepeat);
       	
       	adapter = new AlbumsListAdapter(this);
       	listview.setAdapter(adapter);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_about, menu);
	    return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_id_about:
            {
            	Intent intent = new Intent(mContext, AboutAppActivity.class);
            	mContext.startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        recycleData();
        System.gc();
    }
    
    private void recycleData()
    {
    	if(mBitmapRepeat != null)
    	{
    		mBitmap.recycle();
    		mBitmap = null;
    		mBitmapRepeat = null;
    	}
    	if(adapter != null)
    	{
    		adapter  = null;
    		listview = null;
    	}
    }
}