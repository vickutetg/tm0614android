package com.hoangphan.check05a_rxeye;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<String> menuList = new ArrayList<>();
		Resources resource = getResources();
		String newStr = resource.getString(R.string.new_game);
		String settingStr = resource.getString(R.string.setting_game);
		String exitStr = resource.getString(R.string.exit_game);
		
		menuList.add(newStr);
		menuList.add(settingStr);
		menuList.add(exitStr);
		
		//MenuAdapter adMenu = new MenuAdapter(this);
		ArrayAdapter<String> adMenu = new ArrayAdapter<>(
				this, R.layout.menu_custome,
				R.id.title, //string need to put here
				menuList
				);
		setListAdapter(adMenu);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0: //new game
			startActivity(new Intent(MainActivity.this, NewGameAct.class));
			break;

		default:
			break;
		}
		
	}
}
