package org.davidliebman.android.awesomepic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Button;

public class ShiftButton extends Button {

	private GameValues mValues;
	private int mPart;
	
	private Bitmap mTemp, mMap;
	
	private int mOrigPosX, mOrigPosY;
	private Integer mShift;
	
	public ShiftButton(Context context) {
		super(context);
		setBitmap();
		this.setBackgroundResource(R.drawable.background);
	}
	
	public ShiftButton(Context context, GameValues mV, Bitmap m, Integer shift, int x, int y, int part) {
		super(context);
		mValues = mV;
		mMap = m;
		mShift = shift;
		mPart = part;
		this.mOrigPosX = x;
		this.mOrigPosY = y;
		this.setBackgroundResource(R.drawable.background);
		setBitmap();

	}
	
	public void setBitmap() {
		int mMeasurement = 0;
		int mVisibleChange = 0;
		
		switch (mShift) {
		case Tile.SHIFT_LEFT:
			if (mPart == Tile.PART_A || mPart == Tile.PART_C ) {
				mVisibleChange = (int) mValues.getTileX() - ( mValues.getAnimateNum() * (mValues.getTileX() / Puzzle.ANIMATION_STEPS ));
				
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						(mOrigPosX + 1) * mValues.getTileX() - mVisibleChange , 
						mOrigPosY * mValues.getTileY()  , 
						mVisibleChange , 
						mValues.getTileY() 
						);
			}
			else {
				mVisibleChange = (int) ( mValues.getAnimateNum() * (mValues.getTileX() / Puzzle.ANIMATION_STEPS ));
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						mOrigPosY * mValues.getTileY()  , 
						mVisibleChange , 
						mValues.getTileY() 
						);
			}
			
			
			
			break;
		case Tile.SHIFT_RIGHT:
			if (mPart == Tile.PART_A || mPart == Tile.PART_C ) {
				mVisibleChange =  (int) ( mValues.getAnimateNum() * (mValues.getTileX() / Puzzle.ANIMATION_STEPS ));
				
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						(mOrigPosX + 1) * mValues.getTileX() -   mVisibleChange , 
						mOrigPosY * mValues.getTileY() , 
						mVisibleChange  , 
						mValues.getTileY() 
						);
			}
			else {
				mVisibleChange = (int)  mValues.getTileX() - ( mValues.getAnimateNum()  * (mValues.getTileX() / Puzzle.ANIMATION_STEPS ));
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						mOrigPosY * mValues.getTileY() , 
						mVisibleChange  , 
						mValues.getTileY() 
						);
			}
			
			break;
		case Tile.SHIFT_UP:
			if (mPart == Tile.PART_A || mPart == Tile.PART_C ) {
				
				
				mVisibleChange = (int) mValues.getTileY() - ( mValues.getAnimateNum() * (int)(mValues.getTileY() / Puzzle.ANIMATION_STEPS ));
				mMeasurement = mVisibleChange ;

				
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						(mOrigPosY + 1)* mValues.getTileY() -  mVisibleChange , 
						mValues.getTileX() , 
						mMeasurement
						);
			}
			else {
				
				mVisibleChange = (int)  ( mValues.getAnimateNum() * (int)(mValues.getTileY() / Puzzle.ANIMATION_STEPS ));
				mMeasurement = mVisibleChange ;

				
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						mOrigPosY * mValues.getTileY() ,
						mValues.getTileX() , 
						mMeasurement
						);
			}
			
			
			
			break;
		case Tile.SHIFT_DOWN:
			if (mPart == Tile.PART_A || mPart == Tile.PART_C ) {
				
				mVisibleChange = (int)  ( mValues.getAnimateNum() * (int)(mValues.getTileY() / Puzzle.ANIMATION_STEPS ));
				mMeasurement = mVisibleChange ;

				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						(mOrigPosY + 1 ) * mValues.getTileY() -(  mVisibleChange ), 
						mValues.getTileX() , 
						mMeasurement
						);
			}
			else {
				mVisibleChange = (int) mValues.getTileY() - ( mValues.getAnimateNum() * ( int) (mValues.getTileY() / Puzzle.ANIMATION_STEPS ));
				mMeasurement =  mVisibleChange;
				
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						(mOrigPosY  ) * mValues.getTileY() , 
						mValues.getTileX() , 
						mMeasurement
						);
			}
			
			
			
			break;
			default:
				mTemp = Bitmap.createBitmap(mValues.getLargeMap(), 
						mOrigPosX * mValues.getTileX() , 
						mOrigPosY * mValues.getTileY() , 
						mValues.getTileX() , 
						mValues.getTileY() 
						);
				break;
		}
		
		
		
		this.setWidth((int) (mValues.getScaleX() * mTemp.getWidth()));
		this.setHeight((int) (mValues.getScaleY() * mTemp.getHeight()));
		
	}
	
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Matrix mMatrix = new Matrix();

		mMatrix.setScale(mValues.getScaleX(), mValues.getScaleY());
		canvas.drawBitmap(mTemp, mMatrix, null);
		
		Paint mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		canvas.drawLine(0, 0, 0, mMap.getHeight() * mValues.getScaleY(), mPaint);
		canvas.drawLine(0, 0, mMap.getWidth() * mValues.getScaleX(), 0, mPaint);
		
//		if (mBlank) {
//			//canvas.drawColor(Color.BLACK);
//		}
		switch (mPart) {
		case Tile.PART_A:
			canvas.drawColor(Color.BLACK);
			break;
		case Tile.PART_B:
			break;
		case Tile.PART_C:
			break;
		case Tile.PART_D:
			canvas.drawColor(Color.BLACK);
			break;
		}
	}


	public int getPart() {
		return mPart;
	}

	public void setPart(int mPart) {
		this.mPart = mPart;
	}
	
}