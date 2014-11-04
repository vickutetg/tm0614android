package com.hoangphan.check05a_rxeye;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<String> {

	ArrayList<String> menuList = new ArrayList<>();
	Activity app = (Activity) getContext();
	
	//init dynamic, run before constructor
	{
	}
	
	public MenuAdapter(Context context) {
		super(context, R.layout.menu_custome);
		
		//lấy ra thư mục resource
		Resources resource = app.getResources();
		String newStr = resource.getString(R.string.new_game);
		String settingStr = resource.getString(R.string.setting_game);
		String exitStr = resource.getString(R.string.exit_game);
		
		menuList.add(newStr);
		menuList.add(settingStr);
		menuList.add(exitStr);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//goi service inflater để dựng view
	    LayoutInflater inflater = (LayoutInflater) app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View row = inflater.inflate(R.layout.menu_custome, parent, true);

		//tiến hành gán các ảnh, string tuỳ vào vị trí 
	    TextView menuStr = (TextView) row.findViewById(R.id.title);
	    menuStr.setText(menuList.get(position));
		
		return row;
	}
}
