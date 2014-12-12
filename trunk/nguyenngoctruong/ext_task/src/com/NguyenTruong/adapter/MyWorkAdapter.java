package com.NguyenTruong.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.NguyenTruong.Model.Work;
import com.NguyenTruong.ext_task.R;

public class MyWorkAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Work> arr = new ArrayList<Work>();

	public MyWorkAdapter(Context mContext, ArrayList<Work> arr) {
		super();
		this.mContext = mContext;
		this.arr = arr;
	}

	@Override
	public int getCount() {
		int rtValue = 0;
		if (arr != null) {
			rtValue = arr.size();
		}
		return rtValue;
	}

	@Override
	public Object getItem(int position) {
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		// init View
		// neu convertView null ban khoi tao convertView
		// nguoc lai ban co the tai su dung convertView chi can update noi dung
		// ma k can phai cap phat them 1 hang moi

		// set layout cho convertView
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		convertView = mInflater
				.inflate(R.layout.listview_layout, parent, false);

		// init va setup ViewHolder
		holder = new ViewHolder();
		holder.work = (TextView) convertView.findViewById(R.id.txtWork);
		holder.time = (TextView) convertView.findViewById(R.id.txtTime);
		holder.wCheck = (CheckBox) convertView.findViewById(R.id.checkWork);
		convertView.setTag(holder);

		// lay data insert vao view
		final Work w = arr.get(position);
		holder.work.setText(w.getWork());
		holder.time.setText(w.getHour() + ":" + w.getMinute() + ":"
				+ w.getSecond());
		if (w.isCheck() == false) {
			holder.wCheck.setChecked(false);
		} else {
			holder.wCheck.setChecked(true);
		}

		holder.wCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (holder.wCheck.isChecked()) {
					w.setCheck(true);
				} else {
					w.setCheck(false);
				}

			}
		});

		return convertView;
	}

	public class ViewHolder {
		// cac thuoc tinh cua ViewHolder thuong tuong duong voi cac View trong
		// layoutitem
		TextView work, time;
		CheckBox wCheck;

	}

}
