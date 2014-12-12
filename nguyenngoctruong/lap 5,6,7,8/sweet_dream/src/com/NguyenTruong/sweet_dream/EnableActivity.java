package com.NguyenTruong.sweet_dream;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


@SuppressWarnings("deprecation")
public class EnableActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enable);

		Resources res = getResources();

		TabHost tbEnable = getTabHost();

		Intent intentGeneral = new Intent().setClass(this,
				GeneralActivity.class);
		TabSpec tabGeneral = tbEnable
				.newTabSpec("General")
				.setIndicator("General",res.getDrawable(R.drawable.ic_general_tab))
				.setContent(intentGeneral);

		tbEnable.addTab(tabGeneral);

		Intent intentLocation = new Intent().setClass(this,
				LocationActivity.class);
		TabSpec tabLocation = tbEnable
				.newTabSpec("Location")
				.setIndicator("Location", res.getDrawable(R.drawable.ic_location_tab))
				.setContent(intentLocation);
		tbEnable.addTab(tabLocation);
		

//		for (int i = 0; i < tbEnable.getTabWidget().getChildCount(); i++) {
//			TextView tv = (TextView) tbEnable.getTabWidget().getChildAt(i)
//					.findViewById(android.R.id.title);
//			tv.setTextColor(Color.parseColor("#FFFFFF"));
//		}

	}
}
