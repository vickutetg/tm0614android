package com.hoangphan.hotgirl.adapter;

import com.hoangphan.hotgirl.AlbumsGridActivity;
import com.hoangphan.hotgirl.R;
import com.hoangphan.hotgirl.data.Albums;
import com.hoangphan.hotgirl.util.FileUtil;
import com.hoangphan.hotgirl.util.GlobalVariable;
import com.hoangphan.hotgirl.util.XMLUtil;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumsListAdapter extends BaseAdapter
{
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Albums> mAlbums = null;
	private Bitmap mBitmap = null;
	private String filename;
	
	public AlbumsListAdapter(Context context)
	{
		mContext  = context;
		mAlbums   = new ArrayList<Albums>();
		mAlbums	  = XMLUtil.parserAlbumsListData(context);
		mInflater = LayoutInflater.from(context);
	}
	
	public class ViewHolder
	{
		TextView textView;
		ImageView imageView;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent)
	{
		final ViewHolder holder;
		if (view == null)
		{
			holder 				= new ViewHolder();
			view 				= mInflater.inflate(R.layout.albums_list_item, null);
			holder.textView 	= (TextView)view.findViewById(R.id.albums_list_item_text_view);
			holder.imageView 	= (ImageView)view.findViewById(R.id.albums_list_item_image_view);
			view.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) view.getTag();
		}
		
		holder.imageView.setTag(position);
		holder.textView.setTag(position);
		
		filename 	= "images/albumslist/" + mAlbums.get(position).getImage();
		GlobalVariable.byteBuffer = FileUtil.readFileFromAssets(mContext, filename);
		mBitmap		= BitmapFactory.decodeByteArray(GlobalVariable.byteBuffer, 0, GlobalVariable.byteBuffer.length);
		holder.imageView.setImageBitmap(mBitmap);
		
		holder.textView.setText(mAlbums.get(position).getTitle());
		holder.textView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				GlobalVariable.ALBUMSID 	= mAlbums.get(position).getID();
				GlobalVariable.ALBUMSTITLE 	= mAlbums.get(position).getTitle();
				
				Intent intent = new Intent(mContext, AlbumsGridActivity.class);
				intent.putExtra("ID", GlobalVariable.ALBUMSID);
				intent.putExtra("TITLE", GlobalVariable.ALBUMSTITLE);
				mContext.startActivity(intent);
			}
		});
		
		GlobalVariable.byteBuffer = null;
		return view;
	}
	
	@Override
	public int getCount()
	{
		return mAlbums.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}
}