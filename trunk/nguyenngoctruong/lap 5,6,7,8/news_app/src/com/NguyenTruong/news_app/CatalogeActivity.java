package com.NguyenTruong.news_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class CatalogeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cataloge);
        
        RelativeLayout trending = (RelativeLayout) findViewById(R.id.trending);
        
        trending.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CatalogeActivity.this, TrendingActivity.class);
				startActivity(i);
				
			}
		});
        
        
        
    }
}
