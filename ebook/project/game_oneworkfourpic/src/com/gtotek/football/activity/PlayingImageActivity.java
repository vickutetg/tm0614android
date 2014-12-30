package com.gtotek.football.activity;

import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Window;
import android.widget.ImageView;

import com.gtotek.football.base.BaseActivity;
import com.gtotek.football.base.Constans;
import com.gtotek.football.dialog.WinDialog;
import com.gtotek.football.dialog.WinDialog.OnCloseWinDialog;
import com.gtotek.football.util.PreferenceUtil;
import com.gtotek.footballquiz.R;

public class PlayingImageActivity extends BaseActivity implements
		OnCloseWinDialog {
	private Context mContext = this;
	private ImageView mImgQuestion;
	private WinDialog mWinDialog;

	@Override
	protected void initialize() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_playing_image);
		this.mImgQuestion = (ImageView) this.findViewById(R.id.img_question);
		index = PreferenceUtil.getValue(mContext, Constans.KEY_INDEX_IMAGE, 0);
		super.initialize();
	}

	@Override
	protected void incrementIndex() {
		// TODO Auto-generated method stub
		PreferenceUtil.setValue(mContext, Constans.KEY_INDEX_IMAGE, index);
		super.incrementIndex();

	}

	@Override
	protected void next() {

		super.next();

		Bitmap bitmap = getBitmapFromAsset(this.mQuestionEntity.getImage());
		this.mImgQuestion.setImageBitmap(bitmap);
		// bitmap.recycle();
		// bitmap = null;

	}

	private Bitmap getBitmapFromAsset(String name) {
		AssetManager assetManager = getAssets();
		InputStream istr = null;

		try {
			istr = assetManager.open("logofootball/" + name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}

	@Override
	protected void passQuestion() {
		// TODO Auto-generated method stub
		/*
		 * Intent intent = new Intent(mContext, WinActivity.class);
		 * intent.putExtra(Constans.KEY_QUESTIONENTITY, mQuestionEntity);
		 * startActivity(intent);
		 */
		mWinDialog = new WinDialog(mContext, mQuestionEntity);
		mWinDialog.setOnCloseWinDialog(this);
		mWinDialog.show();
	}

	@Override
	public void closeDialog() {
		next();
	}

}
