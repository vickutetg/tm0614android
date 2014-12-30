package org.davidliebman.android.awesomepic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.os.Message;
//import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Tile extends TableLayout {//LinearLayout  {

	private Context mContext;
	private Bitmap mMap;
	private boolean mUseMap;
	private GameValues mValues;
	private Matrix mMatrix = new Matrix();
	private boolean mBlankTile;
	private int mNumber, mPosX, mPosY;
	private int mOrigPosX, mOrigPosY;
	private int mRememberX, mRememberY;
	private int mTrailingX, mTrailingY;
	private int mShift, mAnimateNum;
	private TileButton mButton;
	private int mBlankButtonWidth, mBlankButtonHeight;
	
	public static final int SHIFT_NONE = 0;
	public static final int SHIFT_LEFT = 1;
	public static final int SHIFT_RIGHT = 2;
	public static final int SHIFT_UP = 3;
	public static final int SHIFT_DOWN = 4;
	
	public static final int PART_A = 0;
	public static final int PART_B = 1;
	public static final int PART_C = 2;
	public static final int PART_D = 3;
	
	public Tile(Context context, GameValues v) {
		super(context);
		mContext = context;
		mValues = v;
		this.setBackgroundResource(R.drawable.background);
		          
		mAnimateNum = mValues.getAnimateNum();
        mButton = new TileButton(context);
        manageViews();
	}

	public Tile(Context context, GameValues v, boolean mUse, int x, int y) {
		super(context);
		mContext = context;
		mValues = v;
		this.setBackgroundResource(R.drawable.background);
		          
		mAnimateNum = mValues.getAnimateNum();
        this.mUseMap = mUse;
        
        this.mBlankButtonWidth = x;
        this.mBlankButtonHeight = y;
        
        this.setMap();
        mButton = new TileButton(context);

        this.manageViews();
	}
	
	public Tile (Tile mOld) {
		super(mOld.getContext());
		mValues = mOld.mValues;
		
		mMatrix = mOld.mMatrix;
		mBlankTile = mOld.mBlankTile;
		mNumber = mOld.mNumber;
		mPosX = mOld.mPosX;
		mPosY = mOld.mPosY;
		mOrigPosX = mOld.mOrigPosX;
		mOrigPosY = mOld.mOrigPosY;
		this.mRememberX = mOld.mRememberX;
		this.mRememberY = mOld.mRememberY;
		mAnimateNum = mValues.getAnimateNum();
		mShift = mOld.mShift;
		this.mTrailingX = mOld.mTrailingX;
		this.mTrailingY = mOld.mTrailingY;
		mButton = new TileButton(mOld.getContext());
		this.setMap(mOld.getMap()); // always after button is instantiated

		this.manageViews();
		
	}
	
	public void manageViews() {
		this.removeAllViews();
		
		ShiftButton mShift1;
		ShiftButton mShift2;
		TableRow mRow1 = new TableRow(this.getContext());
		TableRow mRow2 = new TableRow(this.getContext());

		
		switch (mShift) {
		case Tile.SHIFT_DOWN:


			if (!this.mBlankTile ) {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY , Tile.PART_C);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY , Tile.PART_D);
				
				mRow1.addView(mShift1);
				mRow2.addView(mShift2);
				this.addView(mRow1);
				//this.addView(mRow2);

			}
			else {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY  , Tile.PART_A);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY , Tile.PART_B);
				
				mRow1.addView(mShift1);
				mRow2.addView(mShift2);
				this.addView(mRow1);
				this.addView(mRow2);

			}
			break;
		case Tile.SHIFT_UP:

			if (this.mBlankTile ) {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_C);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_D);
				
				mRow1.addView(mShift1);
				mRow2.addView(mShift2);
				this.addView(mRow1);
				//this.addView(mRow2);

			}
			else {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_A);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_B);
				
				mRow1.addView(mShift1);
				mRow2.addView(mShift2);
				this.addView(mRow1);
				this.addView(mRow2);

			}
			break;
		case Tile.SHIFT_LEFT:

			if (this.mBlankTile ) {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_C);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_D);
			
				mRow1.addView(mShift1);
				//mRow1.addView(mShift2);
				this.addView(mRow1);
			}
			else {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_A);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_B);
			
				mRow1.addView(mShift1);
				mRow1.addView(mShift2);
				this.addView(mRow1);

			}
			break;
		case Tile.SHIFT_RIGHT:

			if (!this.mBlankTile ) {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_C);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_D);
				
				mRow1.addView(mShift1);
				//mRow1.addView(mShift2);
				this.addView(mRow1);
			}
			else {
				mShift1 = new ShiftButton(this.getContext(),mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_A);
				mShift2 = new ShiftButton(this.getContext(), mValues, mMap, mShift, this.mTrailingX, this.mTrailingY, Tile.PART_B);
			
				mRow1.addView(mShift1);
				mRow1.addView(mShift2);
				this.addView(mRow1);

			}
			break;

		}
		
		
		
		if (mShift == Tile.SHIFT_NONE ) {
			this.removeAllViews();
			this.addView(mButton);
		}
		
	}

	public Bitmap getMap() {
		return mMap;
	}

	public void setMap(Bitmap mMap) {
		this.mUseMap = true;
		this.mMap = mMap;
		mButton.setWidth((int)(mMap.getWidth() * mValues.getScaleX()));
		mButton.setHeight((int)(mMap.getHeight() * mValues.getScaleY()));
	}

	public void setMap() {
		this.mUseMap = false;
		mMap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
	}
	
	public int getNumber() {
		return mNumber;
	}

	public void setNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public int getPosX() {
		return mPosX;
	}

	public void setPosX(int mPosX) {
		this.mPosX = mPosX;
	}

	public int getPosY() {
		return mPosY;
	}

	public void setPosY(int mPosY) {
		this.mPosY = mPosY;
	}

	public boolean isBlankTile() {
		return mBlankTile;
	}

	public void setBlankTile(boolean mBlankTile) {
		this.mBlankTile = mBlankTile;
	}
	
	public int getShift() {
		return mShift;
	}

	public void setShift(int mShift) {
		this.mShift = mShift;
	}

	public int getAnimateNum() {
		return mAnimateNum;
	}

	public void setAnimateNum(int mAnimateNum) {
		this.mAnimateNum = mAnimateNum;
	}

	public int getOrigPosX() {
		return mOrigPosX;
	}

	public void setOrigPosX(int mOrigPosX) {
		this.mOrigPosX = mOrigPosX;
	}

	public int getOrigPosY() {
		return mOrigPosY;
	}

	public void setOrigPosY(int mOrigPosY) {
		this.mOrigPosY = mOrigPosY;
	}

	

	public int getRememberX() {
		return mRememberX;
	}

	public void setRememberX(int mRememberX) {
		this.mRememberX = mRememberX;
	}

	public int getRememberY() {
		return mRememberY;
	}

	public void setRememberY(int mRememberY) {
		this.mRememberY = mRememberY;
	}

	public int getTrailingX() {
		return mTrailingX;
	}

	public void setTrailingX(int mTrailingX) {
		this.mTrailingX = mTrailingX;
	}

	public int getTrailingY() {
		return mTrailingY;
	}

	public void setTrailingY(int mTrailingY) {
		this.mTrailingY = mTrailingY;
	}

	public void setButtonWidth(int width) {
		mButton.setWidth(width);
		mBlankButtonWidth = width;
	}

	public void setButtonHeight(int height) {
		mButton.setHeight(height);
		mBlankButtonHeight = height;
	}
	
	class TileButton extends Button implements OnClickListener {

		public TileButton(Context context) {
			super(context);
			this.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			
			if (mValues.getAnimateNum() == 0 && mValues.getMode() == Puzzle.MODE_EASY) {
				Message mM = new Message();
		      	mM.obj = (Object) new Tile (Tile.this);
		      	mM.what = Puzzle.MSG_SWITCH;
		      	mValues.getHandler().dispatchMessage(mM);
			}
			else if (mValues.getAnimateNum() == 0 && mValues.getMode() == Puzzle.MODE_CLASSIC) {
				Message mM = new Message();
		      	mM.obj = (Object) new Tile (Tile.this);
		      	mM.what = Puzzle.MSG_SWITCH_CLASSIC;
		      	mValues.getHandler().dispatchMessage(mM);
			}
		}
		
		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			if (mUseMap) {
				mMatrix.setScale(mValues.getScaleX(), mValues.getScaleY());
				canvas.drawBitmap(mMap, mMatrix, null);
				
				Paint mPaint = new Paint();
				mPaint.setColor(Color.RED);
				canvas.drawLine(0, 0, 0, mMap.getHeight() * mValues.getScaleY(), mPaint);
				canvas.drawLine(0, 0, mMap.getWidth() * mValues.getScaleX(), 0, mPaint);
			}
			else {
				mMatrix.setScale(mBlankButtonWidth, mBlankButtonHeight);
				canvas.drawBitmap(mMap, mMatrix, null);
				this.setWidth(mBlankButtonWidth);
				this.setHeight(mBlankButtonHeight);
			}
			if (mBlankTile) {
				canvas.drawColor(Color.BLACK);
			}
		}
		
	}

	

}
