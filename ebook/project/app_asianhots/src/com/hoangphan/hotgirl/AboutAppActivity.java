package com.hoangphan.hotgirl;

import com.hoangphan.hotgirl.util.FileUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AboutAppActivity extends Activity
{
	private TextView btnBack;
	private TextView textview;
	private Context mContext;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_about_activity);
        mContext 	= this;
        btnBack		= (TextView)findViewById(R.id.app_about_btn_back);
        textview	= (TextView)findViewById(R.id.app_about_text_view);
        
        textview.setText(FileUtil.readFileText(mContext));
        btnBack.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
    }
    
    @Override
    protected void onStart()
    {
        super.onStart();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }
}