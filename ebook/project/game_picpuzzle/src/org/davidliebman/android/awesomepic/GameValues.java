package org.davidliebman.android.awesomepic;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

public class GameValues {

	public Bitmap mTestMap, mLargeMap;
	private int mDisplayX, mDisplayY;
	private int mNumTilesX, mNumTilesY;
	private int mTileX, mTileY;
	private int mPicSizeX, mPicSizeY;
	private float mScaleX, mScaleY;
	private int mFileSource;
	private Integer mAnimateNum;
	private int mMode;
	private boolean mSavePicture;
	private boolean mGettingNewPicture;
	private int mStartCondition;
	private int mBlankX, mBlankY;
	private boolean mUsingTimerInTransition;
	
	private String mPicturePath = new String("/");
	private int mHolderSomeX [][];
	private int mHolderSomeY [][];
	private boolean mHolderIsBlankTile [][];
	
	private Handler mHandler;
	public Puzzle mPuzzle;
	
	public static final int ORIENTATION_PORTRAIT = 1;
	public static final int ORIENTATION_LANDSCAPE = 2;
	private int mOrientation;
	
	//saved preferences vars
	public static final String PREFS_NAME = "awesomepic_prefs";
	public static final String PREFS_SAVED_MODE = "saved_mode";
	public static final String PREFS_SAVED_FILESOURCE = "saved_filesource";
	public static final String PREFS_SAVED_SAVEPICTURE = "saved_savepicture";
	public static final String PREFS_SAVED_PICTUREPATH	= "saved_picturepath";
	public static final String PREFS_SAVED_BLANKX = "saved_blank_x";
	public static final String PREFS_SAVED_BLANKY = "saved_blank_y";
	public static final String PREFS_SAVED_STARTCONDITION = "saved_startcondition";
	public static final String PREFS_SAVED_GETTINGNEWPIC = "saved_gettingnewpic";
	public static final String PREFS_SAVED_USETIMERINTRANSITION = "saved_usetimerintransition";
	
	public static final String PREFS_ARRAY_PREFIX = "HOLDER_";
	public static final String PREFS_SOME_X = "_SOMEX";
	public static final String PREFS_SOME_Y = "_SOMEY";
	public static final String PREFS_BLANK  = "_BLANK";
	
	public static final String PREFS_ARRAY_SEPERATOR  = "_";
	
	public GameValues() {
		mAnimateNum = new Integer(0);
		this.initHolderData();
	}
	
	public GameValues(Puzzle mP, Handler mH) {
		mPuzzle = mP;
		mHandler = mH;
		mAnimateNum = new Integer(0);
		this.initHolderData();
	}
	

	public Handler getHandler() {
		return mHandler;
	}



	public void setHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}

	public void initHolderData() {
		this.mHolderIsBlankTile = new boolean [5][5];
		this.mHolderSomeX = new int [5][5];
		this.mHolderSomeY = new int [5][5];
		for (int x = 0; x < 5; x ++ ) {
			for (int y = 0; y < 5; y ++ ) {

				this.setHolderData(x, y, x, y);
			}
		}
		this.mBlankX = -1;
		this.mBlankY = -1;
	}
	
	public void setHolderData(int x, int y, int mOX, int mOY) {
		this.mHolderSomeX[x][y] = mOX;
		this.mHolderSomeY[x][y] = mOY;


	}

	public void setHolderDataInPrefs( Tile mTile ) {
		
		setHolderData( mTile.getPosX(), mTile.getPosY(), 
				mTile.getOrigPosX(), mTile.getOrigPosY());
		
		if ( mTile.isBlankTile() ) {
			this.mBlankX = mTile.getPosX();
			this.mBlankY = mTile.getPosY();
		}
		
//		setHolderData( mTile.getPosX(), mTile.getPosY(), 
//				mTile.getRememberX(), mTile.getRememberY(), 
//				mTile.isBlankTile());
	}
	
	public Tile [][] getHolderDataFromPrefs(Tile mHolder [][]) {
		
		int mTest = 0;
		for(int x = 0; x < 5; x ++ ) {
			mTest = mTest + this.mHolderSomeX[x][0];
		}
		if (mTest != 0) {
		
			for (int x = 0; x < 5; x ++ ) {
				for (int y = 0; y < 5; y ++ ) {
					mHolder[x][y].setRememberX(this.mHolderSomeX[x][y]);
					mHolder[x][y].setRememberY(this.mHolderSomeY[x][y]);
					mHolder[x][y].setBlankTile(this.mHolderIsBlankTile[x][y]);
				}
			}
		}
		return mHolder;
	}
	
	public Puzzle getPuzzle() {
		return mPuzzle;
	}

	public void setPuzzle(Puzzle mPuzzle) {
		this.mPuzzle = mPuzzle;
	}

	public int getDisplayX() {
		return mDisplayX;
	}

	public void setDisplayX(int mDisplayX) {
		this.mDisplayX = mDisplayX;
	}

	public int getDisplayY() {
		return mDisplayY;
	}

	public void setDisplayY(int mDisplayY) {
		this.mDisplayY = mDisplayY;
	}

	public int getNumTilesX() {
		return mNumTilesX;
	}

	public void setNumTilesX(int mNumTilesX) {
		this.mNumTilesX = mNumTilesX;
	}

	public int getNumTilesY() {
		return mNumTilesY;
	}

	public void setNumTilesY(int mNumTilesY) {
		this.mNumTilesY = mNumTilesY;
	}

	public int getTileX() {
		return mTileX;
	}

	public void setTileX(int mTileX) {
		this.mTileX = mTileX;
	}

	public int getTileY() {
		return mTileY;
	}

	public void setTileY(int mTileY) {
		this.mTileY = mTileY;
	}

	public int getPicSizeX() {
		return mPicSizeX;
	}

	public void setPicSizeX(int mPicSizeX) {
		this.mPicSizeX = mPicSizeX;
	}

	public int getPicSizeY() {
		return mPicSizeY;
	}

	public void setPicSizeY(int mPicSizeY) {
		this.mPicSizeY = mPicSizeY;
	}

	public float getScaleX() {
		return mScaleX;
	}

	public void setScaleX(float mScaleX) {
		this.mScaleX = mScaleX;
	}

	public float getScaleY() {
		return mScaleY;
	}

	public void setScaleY(float mScaleY) {
		this.mScaleY = mScaleY;
	}

	public int getOrientation() {
		return mOrientation;
	}

	public void setOrientation(int mOrientation) {
		this.mOrientation = mOrientation;
	}

	public void incrementAnimateNumber() {
		this.mAnimateNum ++;
	}

	public int getAnimateNum() {
		return mAnimateNum;
	}

	public void setAnimateNum(Integer mAnimateNum) {
		this.mAnimateNum = mAnimateNum;
	}

	public Bitmap getLargeMap() {
		return mLargeMap;
	}

	

	public boolean isGettingNewPicture() {
		return mGettingNewPicture;
	}

	public void setGettingNewPicture(boolean mGettingNewPicture) {
		this.mGettingNewPicture = mGettingNewPicture;
	}

	public int getStartCondition() {
		return mStartCondition;
	}

	public void setStartCondition(int mStartCondition) {
		this.mStartCondition = mStartCondition;
	}

	public int getFileSource() {
		return mFileSource;
	}

	public void setFileSource(int mFileSource) {
		this.mFileSource = mFileSource;
	}

	public int getMode() {
		return mMode;
	}

	public void setMode(int mMode) {
		this.mMode = mMode;
	}

	public void setLargeMap(Bitmap mLargeMap) {
		this.mLargeMap = mLargeMap;
	}
	
	public boolean isSavePicture() {
		return mSavePicture;
	}

	public void setSavePicture(boolean mSavePicture) {
		this.mSavePicture = mSavePicture;
	}

	
	
	public String getPicturePath() {
		return mPicturePath;
	}

	public void setPicturePath(String mPicturePath) {
		this.mPicturePath = mPicturePath;
	}

	
	
	public int getBlankX() {
		return mBlankX;
	}

	public void setBlankX(int mBlankX) {
		this.mBlankX = mBlankX;
	}

	public int getBlankY() {
		return mBlankY;
	}

	public void setBlankY(int mBlankY) {
		this.mBlankY = mBlankY;
	}

	
	
	public boolean isUsingTimerInTransition() {
		return mUsingTimerInTransition;
	}

	public void setUsingTimerInTransition(boolean mUsingTimerInTransition) {
		this.mUsingTimerInTransition = mUsingTimerInTransition;
	}

	public void getSharedPreferences(Context mC) {
		SharedPreferences mPrefs = mC.getSharedPreferences(GameValues.PREFS_NAME, 4);
		this.mMode = mPrefs.getInt(GameValues.PREFS_SAVED_MODE, Puzzle.MODE_CLASSIC);
		this.mFileSource = mPrefs.getInt(GameValues.PREFS_SAVED_FILESOURCE, Puzzle.FILESOURCE_GALLERY);
		this.mSavePicture = mPrefs.getBoolean(GameValues.PREFS_SAVED_SAVEPICTURE, true);
		this.mPicturePath = mPrefs.getString(GameValues.PREFS_SAVED_PICTUREPATH, "/");
		this.mStartCondition = mPrefs.getInt(GameValues.PREFS_SAVED_STARTCONDITION, Puzzle.START_GALLERY);
		this.mGettingNewPicture = mPrefs.getBoolean(GameValues.PREFS_SAVED_GETTINGNEWPIC, false);
		this.mUsingTimerInTransition = mPrefs.getBoolean(GameValues.PREFS_SAVED_USETIMERINTRANSITION, true);
		
		this.mBlankX = mPrefs.getInt(GameValues.PREFS_SAVED_BLANKX, -1);
		this.mBlankY = mPrefs.getInt(GameValues.PREFS_SAVED_BLANKY, -1);


		for (int x = 0; x < 5; x ++ ) {
			for (int y = 0; y < 5; y ++ ) {
				int tempx = mPrefs.getInt(PREFS_ARRAY_PREFIX
						+ new Integer(x).toString() 
						+ GameValues.PREFS_ARRAY_SEPERATOR
						+ new Integer(y).toString()
						+ GameValues.PREFS_SOME_X
						, 0);
				int tempy = mPrefs.getInt(GameValues.PREFS_ARRAY_PREFIX
						+ new Integer(x).toString()
						+GameValues.PREFS_ARRAY_SEPERATOR
						+ new Integer(y).toString()
						+ GameValues.PREFS_SOME_Y
						, 0);

				this.setHolderData(x, y, tempx, tempy);
			}
		}
		
	}
	
	public void saveSharedPreferences(Context mC) {
		SharedPreferences mPrefs = mC.getSharedPreferences(GameValues.PREFS_NAME, 4);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(GameValues.PREFS_SAVED_MODE, this.mMode);
		editor.putInt(GameValues.PREFS_SAVED_FILESOURCE, this.mFileSource);
		editor.putBoolean(GameValues.PREFS_SAVED_SAVEPICTURE, mSavePicture);
		editor.putString(GameValues.PREFS_SAVED_PICTUREPATH, this.mPicturePath);
		editor.putInt(GameValues.PREFS_SAVED_STARTCONDITION, this.mStartCondition);
		editor.putBoolean(GameValues.PREFS_SAVED_GETTINGNEWPIC, this.mGettingNewPicture);
		editor.putBoolean(GameValues.PREFS_SAVED_USETIMERINTRANSITION, this.mUsingTimerInTransition);
		
		editor.putInt(GameValues.PREFS_SAVED_BLANKX, this.mBlankX);
		editor.putInt(GameValues.PREFS_SAVED_BLANKY, this.mBlankY);


		
		for (int x = 0; x < 5; x ++ ) {
			for (int y = 0; y < 5; y ++ ) {
				String tempx = new String (PREFS_ARRAY_PREFIX
						+ new Integer(x).toString() 
						+ GameValues.PREFS_ARRAY_SEPERATOR
						+ new Integer(y).toString()
						+ GameValues.PREFS_SOME_X
						);
				String tempy = new String(GameValues.PREFS_ARRAY_PREFIX
						+ new Integer(x).toString()
						+GameValues.PREFS_ARRAY_SEPERATOR
						+ new Integer(y).toString()
						+ GameValues.PREFS_SOME_Y
						);

//					Log.e("Values", tempx + " " + this.mHolderSomeX[x][y]);
				editor.putInt(tempx, this.mHolderSomeX[x][y]);
				editor.putInt(tempy, this.mHolderSomeY[x][y]);
//					editor.putBoolean(tempbool, this.mHolderIsBlankTile[x][y]);
			}
		}
		
		
		editor.commit();
	}
	
}
