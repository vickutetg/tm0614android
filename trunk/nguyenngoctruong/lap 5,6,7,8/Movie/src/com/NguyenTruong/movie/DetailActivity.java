package com.NguyenTruong.movie;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// Tạo tabhost

		TabHost mTabHost;
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabHost thBrowse = (TabHost) findViewById(android.R.id.tabhost);

		thBrowse.setup();

		TabHost.TabSpec spec;
		spec = thBrowse.newTabSpec("Information");
		spec.setContent(R.id.tab1);
		spec.setIndicator("INFORMATION");
		thBrowse.addTab(spec);

		spec = thBrowse.newTabSpec("Details");
		spec.setContent(R.id.tab2);
		spec.setIndicator("DETAILS");
		thBrowse.addTab(spec);

		spec = thBrowse.newTabSpec("Related");
		spec.setContent(R.id.tab3);
		spec.setIndicator("RELATED");
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
	}
}
