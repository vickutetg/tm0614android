package com.example.bcshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView list;
	String[] title = { "Ao phong", "Quan kaki", "Giay da", "Dong ho", };
	String[] cost = { "100$", "500$", "900$", "800$" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CustomList adapter = new CustomList(MainActivity.this, title, cost);
		list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(MainActivity.this, DetailItem.class);
				i.putExtra("TITLE", title[position]);
				i.putExtra("COUNT", "1");

				setResult(RESULT_OK, i);
				Toast.makeText(MainActivity.this,
						"You Clicked at " + title[+position],
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(DetailItem.class + "", "aaaa");

	}
}
