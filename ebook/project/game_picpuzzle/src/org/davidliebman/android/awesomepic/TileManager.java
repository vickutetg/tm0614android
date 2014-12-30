package org.davidliebman.android.awesomepic;

import java.util.ArrayList;
import java.util.Random;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TileManager extends TableLayout{

	public GameValues mValues;
	public Tile mTest1,mTest2;
	private Context mC;
	private Tile mHolder [][];
	private ArrayList<Tile> mList;
	private SlideTransition mSlide;
	private ProgressDialog mProgress;
	public LoadImage mLoad;
	public ScrambleImage mScramble;
	public static final int NON_RANDOM_BLANK_TILE = 15;
	
	// x,y, x,y, etc...
	private static final int mPresetScramble[] = {
		//scramble vertical columns
		4,0, 4,1, 4,2, 4,3, 4,4,
		3,4, 3,3, 3,2, 3,1, 3,0,
		2,0, 2,1, 2,2, 2,3, 2,4,
		1,4, 1,3, 1,2, 1,1, 1,0,
		0,0, 0,1, 0,2, 0,3, 0,4,
		//scramble horizontal rows
		     1,4, 2,4, 3,4, 4,3,
		3,3, 2,3, 1,3, 0,3, 0,2,
		1,2, 2,2, 3,2, 4,2, 4,1,
		3,1, 2,1, 1,1, 0,1, 0,0,
		1,0, 2,0, 3,0, 4,0};
	
	public TileManager(Context context, GameValues val) {
		super(context);
		mC = context;
		mValues = val;
		mList = new ArrayList<Tile>();
	}
	
	public void launchFillArray() {
		mLoad = new LoadImage();
		mLoad.execute(new Void[] {});
	}
	
	public void launchScrambleTiles() {
		mScramble = new ScrambleImage();
		mScramble.execute(new Void[] {});
	}
	
	public void fillArray() {
		int mNumTilesX, mNumTilesY;
		int mWidth, mHeight;
		
		mList.clear();
		
		mWidth = mValues.getTileX();
		mHeight = mValues.getTileY();
		
		mNumTilesX = mValues.getNumTilesX();
		mNumTilesY = mValues.getNumTilesY();
		mHolder = new Tile[mNumTilesX][mNumTilesY];
		for (int i = 0; i < mNumTilesX; i ++ ) {
			for (int j = 0; j < mNumTilesY; j ++ ) {
				mHolder[i][j] = new Tile(mC, mValues);
				mHolder[i][j].setMap(Bitmap.createBitmap(mValues.getLargeMap(),
						i * mWidth, 
						j * mHeight, 
						mWidth, mHeight));
				mHolder[i][j].setPosX(i);
				mHolder[i][j].setPosY(j);
				
				mHolder[i][j].setOrigPosX(i);
				mHolder[i][j].setOrigPosY(j);
				mList.add(mHolder[i][j]);
				
				mHolder[i][j].setBlankTile(false);
			}
		}
		if (mValues.getStartCondition() == Puzzle.START_GAME_SAVED) {
			mList.get(NON_RANDOM_BLANK_TILE).setBlankTile(false);
			restorePuzzleState();
		}
		else  {
			//non random blank tile
			mList.get(NON_RANDOM_BLANK_TILE).setBlankTile(true);
			Tile mTemp = mList.get(TileManager.NON_RANDOM_BLANK_TILE);

			mValues.setHolderDataInPrefs(mTemp);	
			
		}
		
	}
	
	public void restorePuzzleState() {
		mHolder = mValues.getHolderDataFromPrefs(mHolder);
		
		int mNumTilesX = mValues.getNumTilesX();
		int mNumTilesY = mValues.getNumTilesY();
		Tile [][] mNewHolder = new Tile[mNumTilesX][mNumTilesY];
		for (int i = 0; i < mNumTilesX; i ++ ) {
			for (int j = 0; j < mNumTilesY; j ++ ) {
				Tile mTemp  = mHolder[ mHolder[i][j].getRememberX() ][ mHolder[i][j].getRememberY() ];
				mNewHolder[i][j] = mTemp;
				mNewHolder[i][j].setPosX(i);
				mNewHolder[i][j].setPosY(j);
			}
		}
		
		mHolder = mNewHolder;
		if (mValues.getBlankX() != -1 && mValues.getBlankY() != -1) {
			mHolder[mValues.getBlankX()][mValues.getBlankY()].setBlankTile(true);
		}
		mValues.setGettingNewPicture(false);
	}
	
	public void showAllTiles() {
		int mNumTilesX, mNumTilesY;
		
		mNumTilesX = mValues.getNumTilesX();
		mNumTilesY = mValues.getNumTilesY();
		this.removeAllViews();
		
		for (int p = 0; p < mNumTilesY; p ++ ) {
			TableRow mTempRow = new TableRow(mC);
			
			for (int q = 0; q < mNumTilesX; q ++ ) {
				mTempRow.addView(new Tile(mHolder[q][p]));
			}
			this.addView(mTempRow);
		}

		
	}
	

	
	public void scrambleTiles() {
		Random mRandom = new Random();

		mHolder[mValues.getBlankX()][mValues.getBlankY()].setBlankTile(false);
		
		
		mValues.setBlankX(-1);
		mValues.setBlankY(-1);
		
		if (mList.size() < TileManager.NON_RANDOM_BLANK_TILE) {
			for (int i = 0; i < mValues.getNumTilesX(); i ++ ) {
				for (int j = 0; j < mValues.getNumTilesY(); j ++ ) {
					
					mHolder[i][j].setBlankTile(false);
					mValues.setHolderDataInPrefs(mHolder[i][j]);
					
					mList.add(mHolder[i][j]);
				}
			}
		}
		// TODO: scramble should override everything...
		// maybe remove from user interface thread.
		mValues.setBlankX(TileManager.NON_RANDOM_BLANK_TILE / 5	);
    	mValues.setBlankY(TileManager.NON_RANDOM_BLANK_TILE % 5 );
    	mHolder[TileManager.NON_RANDOM_BLANK_TILE /5][TileManager.NON_RANDOM_BLANK_TILE % 5].setBlankTile(true);
		for (int i = 0; i < TileManager.mPresetScramble.length / 2; i ++ ) {
			Log.e("TileManager", "scramble: " + mPresetScramble[i * 2] + " " + mPresetScramble[(i*2) + 1]);
			int mTileX = mPresetScramble[(i * 2)];
			int mTileY = mPresetScramble[(i * 2) + 1];
			Tile mMoveTile = mHolder[mTileX][mTileY];
			switchWithBlankScramble(mMoveTile);
		}
	}
	
	public void switchWithBlank(Tile mTile, boolean mCheckGameOver) {
		Tile mBlank = new Tile(mC, mValues);
		boolean mFound = false;
		for (int i = 0; i < mValues.getNumTilesX(); i ++ ) {
			for (int j = 0; j < mValues.getNumTilesY(); j ++ ) {
				if (mHolder[i][j].isBlankTile()	) {
					mBlank = new Tile (mHolder[i][j]);
					mFound = true;
					i = mValues.getNumTilesX();
					j = mValues.getNumTilesY();
				}
			}

		}
		if (mFound) switchTiles (mTile, mBlank, mCheckGameOver);

	}
	
	public void switchWithBlankScramble(Tile mTile) {
		Tile mBlank = new Tile(mC, mValues);
		boolean mFound = false;
		for (int i = 0; i < mValues.getNumTilesX(); i ++ ) {
			for (int j = 0; j < mValues.getNumTilesY(); j ++ ) {
				if (mHolder[i][j].isBlankTile()	) {
					mBlank = new Tile (mHolder[i][j]);
					//check if blank is adjacent
					if (
						(mTile.getPosX() == mBlank.getPosX() && 
						mTile.getPosY() == mBlank.getPosY()	 - 1) ||
							
						(mTile.getPosX() == mBlank.getPosX() && 
						mTile.getPosY() == mBlank.getPosY()	 + 1) ||
							
						(mTile.getPosX() == mBlank.getPosX() - 1 && 
						mTile.getPosY() == mBlank.getPosY()	 ) ||
							
						(mTile.getPosX() == mBlank.getPosX() + 1 && 
						mTile.getPosY() == mBlank.getPosY()	 )
							
							) {
						mFound = true;
					}
					
					i = mValues.getNumTilesX();
					j = mValues.getNumTilesY();
				}
			}

		}
		if (mFound) switchTiles (mTile, mBlank, false);

	}
	
	public void switchWithBlankClassic(Tile mTile) {
		Tile mBlank = new Tile(mC, mValues);
		boolean mFound = false;
		for (int i = 0; i < mValues.getNumTilesX(); i ++ ) {
			for (int j = 0; j < mValues.getNumTilesY(); j ++ ) {
				if (mHolder[i][j].isBlankTile()	) {
					mBlank = new Tile (mHolder[i][j]);
					mFound = true;
					i = mValues.getNumTilesX();
					j = mValues.getNumTilesY();
				}
			}
			
		}
		if (mTile.getPosX() == mBlank.getPosX() && 
				mTile.getPosY() == mBlank.getPosY()	 - 1) {
			//move down
			mBlank.setShift(Tile.SHIFT_DOWN);
			mTile.setShift(Tile.SHIFT_DOWN);
			this.setTrailingTileSettings(mBlank, mTile);
			//Log.e("Manger", "down");
		}
		else if (mTile.getPosX() == mBlank.getPosX()  && 
				mTile.getPosY() == mBlank.getPosY()	+ 1) {
			//move up
			mBlank.setShift(Tile.SHIFT_UP);
			mTile.setShift(Tile.SHIFT_UP);
			this.setTrailingTileSettings(mBlank, mTile);
			//Log.e("Manager", "up");
		}
		else if (mTile.getPosX() == mBlank.getPosX()  - 1  && 
				mTile.getPosY() == mBlank.getPosY()	) {
			//move right
			mBlank.setShift(Tile.SHIFT_RIGHT);
			mTile.setShift(Tile.SHIFT_RIGHT);
			this.setTrailingTileSettings(mBlank, mTile);
			//Log.e("Manager","right");
		}
		else if (mTile.getPosX() == mBlank.getPosX() + 1 && 
				mTile.getPosY() == mBlank.getPosY()	 ) {
			//move left
			mBlank.setShift(Tile.SHIFT_LEFT);
			mTile.setShift(Tile.SHIFT_LEFT);
			this.setTrailingTileSettings(mBlank, mTile);
		}
		else {
			mFound = false; // not in right position.
		}
		
		if (mFound) switchTiles (mTile, mBlank, true);
	}
	
	public void setTrailingTileSettings(Tile mTile, Tile mBlank) {
		mTile.setTrailingX(mBlank.getOrigPosX());
		mTile.setTrailingY(mBlank.getOrigPosY());
		mBlank.setTrailingX(mBlank.getOrigPosX());
		mBlank.setTrailingY(mBlank.getTrailingY());
		
	}
	
	public void switchTiles(Tile mTile1, Tile mTile2, boolean mIsUserInput) {
		int mTile1X = mTile1.getPosX();
		int mTile1Y = mTile1.getPosY();
		int mTile2X = mTile2.getPosX();
		int mTile2Y = mTile2.getPosY();
		int mShift1 = mTile1.getShift();
		int mShift2 = mTile2.getShift();
		
		int mTrailingX = mTile2.getTrailingX();
		int mTrailingY = mTile2.getTrailingY();
		
		Tile mTileTemp = new Tile(mHolder[mTile1X][mTile1Y]);

		
		mHolder[mTile1X][mTile1Y] = new Tile(mHolder[mTile2X][mTile2Y]);
		mHolder[mTile1X][mTile1Y].setPosX(mTile1X);//preserve x/y
		mHolder[mTile1X][mTile1Y].setPosY(mTile1Y);
		mHolder[mTile1X][mTile1Y].setShift(mShift1);
		
		mHolder[mTile2X][mTile2Y] = new Tile(mTileTemp);
		mHolder[mTile2X][mTile2Y].setPosX(mTile2X);//preserve x/y
		mHolder[mTile2X][mTile2Y].setPosY(mTile2Y);
		mHolder[mTile2X][mTile2Y].setShift(mShift2);
		
		mHolder[mTile1X][mTile1Y].setTrailingX(mTrailingX);
		mHolder[mTile1X][mTile1Y].setTrailingY(mTrailingY);

		mHolder[mTile2X][mTile2Y].setTrailingX(mTrailingX);
		mHolder[mTile2X][mTile2Y].setTrailingY(mTrailingY);

		if (mValues.getMode() == Puzzle.MODE_CLASSIC &&  mIsUserInput) {
			mSlide = new SlideTransition();
			mSlide.execute(new Tile[] {mHolder[mTile1X][mTile1Y], mHolder[mTile2X][mTile2Y]});
		}
		else {
			mHolder[mTile1X][mTile1Y].setShift(Tile.SHIFT_NONE);
			mHolder[mTile2X][mTile2Y].setShift(Tile.SHIFT_NONE);
			mValues.setAnimateNum(0);
			mValues.getHandler().sendEmptyMessage(Puzzle.MSG_REDRAW);
		}
		mValues.setHolderDataInPrefs(mHolder[mTile1X][mTile1Y]);
		mValues.setHolderDataInPrefs(mHolder[mTile2X][mTile2Y]);

		if (mIsUserInput) checkGameOver();
	}
	
	public void checkGameOver() {
		boolean mAllInPlace = true;
		for (int i = 0; i < mValues.getNumTilesX(); i ++ ) {
			for (int j = 0 ; j < mValues.getNumTilesY(); j ++ ) {
				Tile mTemp = mHolder[i][j];
				if (mTemp.getOrigPosX() != i || mTemp.getOrigPosY() != j ) {
					mAllInPlace = false;
					
				}
			}
		}
		if (mAllInPlace ) {
			mValues.getHandler().sendEmptyMessage(Puzzle.MSG_DIALOG_FINISHED);
		}
	}
	
	public class LoadImage extends AsyncTask<Void, Void, Void> {
	 	   @Override
	 	   protected void onPreExecute() {
	 		   if (mProgress == null) {
	 			   mProgress = ProgressDialog.show(mC, "", "Loading. Please wait...", true);
	 		   }
	 	   }
	 	   
	 	   @Override
	 	   protected Void doInBackground(Void... m) {

	 		   fillArray();
	 		   
	 		   return null;
	 	   }
	 	   
	 	   @Override
	 	   protected void onPostExecute(Void m) {
		        

	 		   
		        showAllTiles();
		        
		        mValues.getPuzzle().getTilesLayout().removeAllViews();
		        
		        mValues.getPuzzle().getTilesLayout().addView(TileManager.this);
		        mValues.getHandler().sendEmptyMessage(Puzzle.MSG_REDRAW);
	 		   
		        
	 		   if (mProgress != null) mProgress.dismiss();
	 	   }
	 	   
		
	    }
	
	public class ScrambleImage extends AsyncTask<Void, Void, Void> {
	 	   @Override
	 	   protected void onPreExecute() {
	 		   if (mProgress != null ) {
	 			   mProgress.dismiss();

	 		   }
 			   mProgress = ProgressDialog.show(mC, "", "Loading. Please wait...", true);
	 		   
	 	   }
	 	   
	 	   @Override
	 	   protected Void doInBackground(Void... m) {
	 		   mValues.initHolderData();
	 		   mValues.getPuzzle().setNonRandomBlankTile();
	 		   //mValues.getPuzzle().setGameStartVars(mValues.getPicturePath());
	 		   fillArray();
	 		   scrambleTiles();
	 		   
	 		   return null;
	 	   }
	 	   
	 	   @Override
	 	   protected void onPostExecute(Void m) {
		        

	 		   
		        showAllTiles();
		        
		        mValues.getPuzzle().getTilesLayout().removeAllViews();
		        
		        mValues.getPuzzle().getTilesLayout().addView(TileManager.this);
		        mValues.getHandler().sendEmptyMessage(Puzzle.MSG_REDRAW);
	 		   
		        
	 		   if (mProgress != null) mProgress.dismiss();
	 	   }
	 	   
		
	    }
	
	private class SlideTransition extends AsyncTask<Tile, Void, Tile> {
	 	   @Override
	 	   protected void onPreExecute() {
	 		   mValues.setAnimateNum(0);
	 	   }
	 	   
	 	   @Override
	 	   protected Tile doInBackground(Tile ... mTile) {


	 		   for (int i = 1; i < Puzzle.ANIMATION_STEPS - 1; i ++ ) {
		 		   try {
		 			   if(mValues.isUsingTimerInTransition()) {
		 				   Thread.sleep(100);
		 				   //
		 				   
		 			   }
		 			   else {
		 				   Thread.sleep(25);
		 			   }
		 			   mValues.setAnimateNum(i);
		 			   
		 			   mValues.getHandler().sendEmptyMessage(Puzzle.MSG_REDRAW);
		 		   }
		 		   catch (Exception e) {
		 			   e.printStackTrace();
		 		   }
	 		   }
	 		   mTile[0].setShift(Tile.SHIFT_NONE);
	 		   mTile[1].setShift(Tile.SHIFT_NONE);

	 		   return mTile[0];
	 	   }
	 	   
	 	   @Override
	 	   protected void onPostExecute(Tile mResult) {
	 		   mValues.setAnimateNum(0);
	 		   mValues.getHandler().sendEmptyMessage(Puzzle.MSG_REDRAW);
	 	   }
	    }
}
