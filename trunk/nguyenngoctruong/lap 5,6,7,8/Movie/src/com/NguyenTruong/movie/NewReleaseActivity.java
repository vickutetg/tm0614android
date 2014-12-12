package com.NguyenTruong.movie;

import java.util.ArrayList;

import model.Movie;

import adapter.movie_adapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class NewReleaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_release);
		
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		Movie m1 = new Movie();
		m1.setName("Speed");
		m1.setPoster(R.drawable.poster6);
		
		movieList.add(m1);
		
		movie_adapter adapter = new movie_adapter(NewReleaseActivity.this, movieList);
		
		GridView gv = (GridView) findViewById(R.id.gridView1);
		
		gv.setAdapter(adapter);
		
		
	}
}
