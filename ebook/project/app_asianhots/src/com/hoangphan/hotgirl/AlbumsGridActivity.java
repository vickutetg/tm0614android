package com.hoangphan.hotgirl;

import com.hoangphan.hotgirl.adapter.AlbumsGridAdapter;
import com.hoangphan.hotgirl.util.GlobalVariable;
import com.hoangphan.hotgirl.util.XMLUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumsGridActivity extends Activity
{
	private Context mContext;
	private Bundle extra;
	private TextView backButton;
	private TextView txtTitle;
	private GridView gridview;
	private ImageView imageview;
	private Bitmap mBitmap = null;
	private AlbumsGridAdapter adapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albums_grid_activity); 
        backButton 	= (TextView)findViewById(R.id.albums_activity_btn_back);
        txtTitle	= (TextView)findViewById(R.id.albums_activity_txt_title);
        gridview	= (GridView)findViewById(R.id.albums_activity_grid_view);
        imageview	= (ImageView)findViewById(R.id.albums_activity_image_view_grid_view);
        mContext 	= this;
        setGridViewNumColums();
        extra = getIntent().getExtras();
        txtTitle.setText(extra.getString("TITLE"));
        backButton.setText(getResources().getString(R.string.txt_albums));
        backButton.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
        
        if(extra.getString("ID").equals("null"))
        {
        	mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nophotos);
        	gridview.setVisibility(View.GONE);
        	imageview.setVisibility(View.VISIBLE);
        	imageview.setImageBitmap(mBitmap);
        }
        else
        {
        	GlobalVariable.mAlbumsGrid = XMLUtil.parserAlbumsGridData(this, extra.getString("ID"));
        	adapter = new AlbumsGridAdapter(this, extra.getString("ID"));
            gridview.setAdapter(adapter);
		}
    }
    
    private void setGridViewNumColums()
    {
    	GlobalVariable.config = getResources().getConfiguration(); 
    	try
    	{
	        if(GlobalVariable.config.orientation == Configuration.ORIENTATION_PORTRAIT)
	        {
	        	//Màn hình dọc
	        	gridview.setNumColumns(4);
	        } 
	        else if(GlobalVariable.config.orientation == Configuration.ORIENTATION_LANDSCAPE)
	        {
	        	//Màn hình ngang
	        	gridview.setNumColumns(6);
	        }
    	}
    	catch (Exception e) {}
    }
    
    public void onConfigurationChanged(Configuration newConfig)
    {
    	super.onConfigurationChanged(newConfig);
    	setGridViewNumColums();
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
        if(mBitmap != null)
        {
        	mBitmap.recycle();
        	mBitmap = null;
        }
        if(adapter != null)
        {
        	adapter  = null;
        	gridview = null;
        }
        System.gc();
    }
}