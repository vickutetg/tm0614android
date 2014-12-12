package com.NguyenTruong.movie;

import java.util.ArrayList;

import model.Movie;
import adapter.movie_adapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity {

	TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
		// Tạo tabhost
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabHost thBrowse = (TabHost) findViewById(android.R.id.tabhost);

		thBrowse.setup();

		TabHost.TabSpec spec;
		spec = thBrowse.newTabSpec("Coming Soon");
		spec.setContent(R.id.tab1);
		spec.setIndicator("COMING SOON");
		thBrowse.addTab(spec);

		spec = thBrowse.newTabSpec("New Release");
		spec.setContent(R.id.tab2);
		spec.setIndicator("NEW RELEASE");
		thBrowse.addTab(spec);

		spec = thBrowse.newTabSpec("Promotions");
		spec.setContent(R.id.tab3);
		spec.setIndicator("PROMOTIONS");
		thBrowse.addTab(spec);

		thBrowse.setCurrentTab(1);

		for (int i = 0; i < thBrowse.getTabWidget().getChildCount(); i++) {
			thBrowse.getTabWidget().getChildAt(i)
					.setBackgroundColor(Color.TRANSPARENT);
			thBrowse.getTabWidget().getChildAt(i).getLayoutParams().height = 100;
			TextView tv = (TextView) thBrowse.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
			tv.setPadding(10, 10, 10, 20);
		}
		
		
		
		
		
		// Tạo giá trị cho tab New Release
		ArrayList<Movie> movieList = new ArrayList<Movie>();

		Movie m1 = new Movie();
		m1.setName("SPEED");
		m1.setPoster(R.drawable.poster6);
		
		Movie m2 = new Movie();
		m2.setName("127 HOURS");
		m2.setPoster(R.drawable.poster1);
		
		Movie m3 = new Movie();
		m3.setName("LES MISECRABLES");
		m3.setPoster(R.drawable.poster2);
		
		Movie m4 = new Movie();
		m4.setName("DRIVE");
		m4.setPoster(R.drawable.poster3);
		
		Movie m5 = new Movie();
		m5.setName("DIVERGENT");
		m5.setPoster(R.drawable.poster4);
		
		Movie m6 = new Movie();
		m6.setName("SPLICE");
		m6.setPoster(R.drawable.poster5);

		movieList.add(m1);
		movieList.add(m2);
		movieList.add(m3);
		movieList.add(m4);
		movieList.add(m5);
		movieList.add(m6);

		movie_adapter adapter = new movie_adapter(this, movieList);

		GridView gv = (GridView) findViewById(R.id.gridView1);

		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(MainActivity.this, DetailActivity.class);
				startActivity(i);
				
			}
		});
		

	}

}
