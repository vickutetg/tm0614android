package com.NguyenTruong.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.NguyenTruong.fodi.R;
import com.NguyenTruong.fodi.R.id;
import com.NguyenTruong.fodi.R.layout;
import com.NguyenTruong.model.Food;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class FoodAdapterList extends BaseAdapter {
	

	private Context mContext;
	private ArrayList<Food> foodlist = new ArrayList<Food>();

	public FoodAdapterList(Context mContext, ArrayList<Food> foodlist) {
		super();
		this.mContext = mContext;
		this.foodlist = foodlist;
	}

	@Override
	public int getCount() {
		int rtValue = 0;
		if (foodlist != null) {
			rtValue = foodlist.size();
		}
		return rtValue;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return foodlist.get(position);

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;

		LayoutInflater mInflater = LayoutInflater.from(mContext);
		convertView = mInflater
				.inflate(R.layout.listview_layout, parent, false);

		holder = new ViewHolder();
		holder.foodimage = (ImageView) convertView.findViewById(R.id.imgFood);
		holder.foodName = (TextView) convertView.findViewById(R.id.txtFoodName);
		holder.dificult = (TextView) convertView.findViewById(R.id.txtDificult);
		holder.duration = (TextView) convertView.findViewById(R.id.txtTime);

		convertView.setTag(holder);

		Food fooddetail = foodlist.get(position);
		
		InputStream ims;
		try {
			ims = mContext.getAssets().open(fooddetail.getImage());
			Bitmap d = BitmapFactory.decodeStream(ims);
			holder.foodimage.setScaleType(ScaleType.FIT_XY);
			holder.foodimage.setImageBitmap(d);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		holder.foodName.setText(fooddetail.getName() + "");
		holder.dificult.setText(fooddetail.getDificult() + "");
		holder.duration.setText(fooddetail.getDuration() + " Phút");

		return convertView;
	}

	public class ViewHolder {
		ImageView foodimage;
		TextView foodName, dificult, duration;
	}

}
