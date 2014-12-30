package com.gtotek.football.activity;

import com.gtotek.football.base.Constans;
import com.gtotek.football.util.PreferenceUtil;
import com.gtotek.footballquiz.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class StartPlayActivity extends Activity implements View.OnClickListener {
	// private Context mContext = this;
	private TextView tvPlay;

	private ImageView mImgWheel;

	private ImageView imgInfo;

	private ImageView mImgSound;

	private Context mContext = this;

	private int soundState = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);

		tvPlay = (TextView) findViewById(R.id.tvPlay);
		tvPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(StartPlayActivity.this,
						PlayingImageActivity.class);
				startActivity(intent);
			}
		});

		imgInfo = (ImageView) this.findViewById(R.id.ivInfo);
		imgInfo.setOnClickListener(this);

		mImgWheel = (ImageView) this.findViewById(R.id.ivWheel);
		mImgWheel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String appPackageName = "com.gtotek.wheeloffortune"; // Can
																			// also
																			// use
				// getPackageName(),
				// as below
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}
		});

		mImgSound = (ImageView) this.findViewById(R.id.ivSound);
		soundState = PreferenceUtil.getValue(mContext, Constans.KEY_SOUND, 1);

		if (soundState == 1) {
			// sound enable
			mImgSound.setBackgroundResource(R.drawable.btn_sound_on);
		} else {
			mImgSound.setBackgroundResource(R.drawable.btn_sound_off);
		}

		mImgSound.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (soundState == 1) {
					soundState = 0;
					mImgSound.setBackgroundResource(R.drawable.btn_sound_off);

				} else {
					soundState = 1;
					mImgSound.setBackgroundResource(R.drawable.btn_sound_on);
				}
				
				 PreferenceUtil.setValue(mContext, Constans.KEY_SOUND, soundState);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(getString(R.string.infomartion));
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setMessage(getResources().getText(R.string.version_info)
				.toString());
		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.show();
	}
}
