package org.davidliebman.android.awesomepic;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Puzzle extends ListActivity {

    public RecordAdapter mAdapter;
    public ArrayList<FileListNode> mList;
    public TileManager mManager;
    public GameValues mValues;
    private SplashScreen mSplash;
    
    public TextView mText;
    public String mPath = new String();
    public Bitmap mBitmap;
    private int mLastView = -1;
    private int mStartCondition = 0;
    
    private LinearLayout mTilesLayout;
    private Animation myFadeInAnimation;
    
    // several enumerations
    public static final int START_NONE = 0;
    public static final int START_FILE = 1;
    public static final int START_GALLERY = 2;
    public static final int START_GAME_NEW = 3;
    public static final int START_GAME_SAVED = 4;
    public static final int START_RETURN_FROM_GALLERY = 5;

	public static final int VIEW_GAME = 0;
    public static final int VIEW_FILES = 1;
    public static final int VIEW_TEXT = 2;
    public static final int VIEW_SCORES = 3;
    public static final int VIEW_SPLASH	 = 4;
    
    public static final int TEXT_NOTEXT = -1;
    public static final int TEXT_HELP = 0;
    public static final int TEXT_CREDITS = 1;
	
    public static final int MSG_REDRAW = 0;
    public static final int MSG_SWITCH = 1;
    public static final int MSG_SWITCH_CLASSIC = 2;
    public static final int MSG_DIALOG_FINISHED = 3;
    public static final int MSG_DIALOG_FILENOTFOUND = 4;
    
    public static final int MODE_EASY = 1;
    public static final int MODE_CLASSIC = 2;
    
    public static final int FILESOURCE_SDCARD = 1;
    public static final int FILESOURCE_GALLERY = 2;

    // not enumerations
    public static final int SELECT_PICTURE = 0;
    
    public static final int ANIMATION_STEPS = 20;
    
	/* Called when the activity is first created */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);        
        
        setContentView(R.layout.main_layout);
        this.showView(Puzzle.VIEW_FILES);

        
        
        mValues = new GameValues(this, mHandler);
        
        mValues.getSharedPreferences(this);
       
        if (mValues.getFileSource() == Puzzle.FILESOURCE_SDCARD ) {
    		this.findViewById(R.id.button_gallery).setVisibility(View.GONE);
    	}
    	else if (mValues.getFileSource() == Puzzle.FILESOURCE_GALLERY) {
    		this.findViewById(R.id.button_gallery).setVisibility(View.VISIBLE);
    	}
        
        mManager = new TileManager(this, mValues);
        
        mPath = new String("/");
        mList = this.getFileList();
        mAdapter = new RecordAdapter(this, R.layout.main_layout, mList);
        mAdapter.setNotifyOnChange(true);
        mAdapter.notifyDataSetChanged();
        
        setListAdapter(mAdapter);
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener () {
        	
        	@Override
        	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		int entry = position ;
    			String mName = mList.get(entry).getName();

        		if (mList.get(entry).getType() == FileListNode.TYPE_FOLDER) {
        			mPath = mPath + "/" + mList.get(entry).getName();
        			
        			
        			updateFileListStatus();
        		}
        		else if (Puzzle.checkPathEnding(mName)) {

        			mPath = mPath + "/" + mList.get(entry).getName();
					
        			mStartCondition = Puzzle.START_GAME_NEW;
					showView(Puzzle.VIEW_GAME);
					mValues.initHolderData();
					setNonRandomBlankTile();
					setGameStartVars(mPath);
					
        		}
        		mText.setText(mPath);

        	 }
        	
        });
        
        mText = (TextView) findViewById(R.id.edittext_name);
        mText.setText(mPath);
        
        final Button button = (Button) findViewById(R.id.button_file);
        button.setText("Go Back To Root Directory");
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	mPath = new String("/");
            	mText.setText(mPath);
            	updateFileListStatus();
            }
        });
        
        final Button textbutton = (Button) findViewById(R.id.button_endtext);
        textbutton.setText("Go Back");
        textbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if (mLastView == Puzzle.VIEW_FILES) {

            		showView(Puzzle.VIEW_FILES);
            	}
            	else if (mLastView == Puzzle.VIEW_GAME) {
            		showView(Puzzle.VIEW_GAME);
            	}
            	
            }
        });
        
        final Button gallerybutton = (Button) findViewById(R.id.button_gallery);
        //gallerybutton.setText("Go Back");
        gallerybutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
   	
            	launchGallery();
            }
        });
        
        final View buttonSplash = this.findViewById(R.id.view_splash);
        buttonSplash.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		if (mSplash != null && !mSplash.isCancelled()) {
            		mSplash.cancel(true);
            		myFadeInAnimation.scaleCurrentDuration(0);
            		onPostSplash();
            	
            	}
        	}
        });
        
		mTilesLayout = (LinearLayout)findViewById(R.id.view_game);
        
    }
    
    public void launchGallery() {
    	mPath = new String("/");
    	mValues.setPicturePath(mPath);
    	Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Picture For Puzzle"),  SELECT_PICTURE);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                mPath = getGalleryPath(selectedImageUri);


                if ( Puzzle.checkPathEnding(mPath)) {

    				this.mStartCondition = Puzzle.START_RETURN_FROM_GALLERY;
                }
                else {
                	File mFile = new File(mPath);
                	mPath = mFile.getParent().toString();

                	if (mPath == null) mPath = new String("/");
                	mText.setText(mPath);
                	this.mStartCondition = Puzzle.START_FILE;
                	this.updateFileListStatus();
                }
                
            }
        }
    }
    
    public String getGalleryPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String mResult = cursor.getString(column_index);
        cursor.close();

        return mResult;
    }
    
    public static boolean checkPathEnding(String mName) {
    	boolean mReturn = false;
    	if (mName.endsWith(".png") || mName.endsWith(".PNG") ||
				mName.endsWith(".jpg") || mName.endsWith(".JPG") ||
				mName.endsWith(".jpeg") || mName.endsWith(".JPEG") ) {
					
			mReturn = true;
		}
    	return mReturn;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, 
    			Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
    	
    	mValues.getSharedPreferences(Puzzle.this);
    	String mSaved = mValues.getPicturePath();
    	
    	if (mValues.isSavePicture() && Puzzle.checkPathEnding(mSaved) && ! mValues.isGettingNewPicture()) {
    		mPath = mSaved;
    	}
    	
    	if (this.mStartCondition == 0) this.mStartCondition = mValues.getStartCondition();

    	switch(this.mStartCondition) {
    	case Puzzle.START_GAME_SAVED:
    	case Puzzle.START_GALLERY :
    	case Puzzle.START_FILE:
    	case Puzzle.START_GAME_NEW:
    		mSplash = new SplashScreen();
    		mSplash.execute(new Integer[] {0});
    		break;
    	case Puzzle.START_RETURN_FROM_GALLERY:
    		/* no splash screen here! */
    		
    		onPostSplash();
    		break;
    	default:
    		onPostSplash();
    		break;
    	}
    	
    	
    }
    
    public void onPostSplash() {
    	
    	if (this.mStartCondition == 0) this.mStartCondition = mValues.getStartCondition();
    	
    	if (this.mStartCondition == Puzzle.START_GAME_SAVED) {
    		this.showView(Puzzle.VIEW_GAME);

    		mPath = mValues.getPicturePath();
    		this.setGameStartVars(mPath);
    	}
    	else if (this.mStartCondition == Puzzle.START_GALLERY) {
    		//mValues.setSavePicture(false);
    		launchGallery();
        	
    	}
    	else  if (this.mStartCondition == Puzzle.START_GAME_NEW ){
    		
    		showView(Puzzle.VIEW_GAME);
        	mValues.initHolderData();
			this.setNonRandomBlankTile();
        	setGameStartVars(mPath);
    	}
    	else if (this.mStartCondition == Puzzle.START_RETURN_FROM_GALLERY ) {
    		showView(Puzzle.VIEW_GAME);
    		mValues.initHolderData();
    		this.setNonRandomBlankTile();
    		this.setGameStartVars(mPath);
    	}
    	
    	else if (this.mStartCondition == Puzzle.START_FILE) {
    		this.mText.setText(mPath);
    		this.showView(Puzzle.VIEW_FILES);
    	}
    	else {
    		this.mText.setText(mPath);
    		this.showView(Puzzle.VIEW_FILES);
    	}
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	
    	if (mValues.isSavePicture()) {
    		mStartCondition = Puzzle.START_GAME_SAVED;
    		
    	}
    	
    	mValues.setStartCondition(mStartCondition);
    	mValues.setPicturePath(mPath);
    	mValues.saveSharedPreferences(Puzzle.this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflate = getMenuInflater();
    	inflate.inflate(R.menu.main_menu, menu);
    	

    	return true;
    	
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	if (mValues.getMode() == Puzzle.MODE_CLASSIC) {
    		menu.findItem(R.id.menu_set_classic).setChecked(true);
    	}
    	else if (mValues.getMode() == Puzzle.MODE_EASY) {
    		menu.findItem(R.id.menu_clear_classic).setChecked(true);
    	}
    	if (mValues.getFileSource() == Puzzle.FILESOURCE_GALLERY ) {
    		menu.findItem(R.id.menu_choose_gallery).setChecked(true);
    	}
    	else if (mValues.getFileSource() == Puzzle.FILESOURCE_SDCARD) {
    		menu.findItem(R.id.menu_choose_sd).setChecked(true);
    	}
    	if(mValues.isSavePicture()) {
    		menu.findItem(R.id.menu_save_picture).setChecked(true);
    	}
    	else {
    		menu.findItem(R.id.menu_save_picture).setChecked(false);
    	}
    	if ( mValues.isUsingTimerInTransition()) {
    		menu.findItem(R.id.menu_use_timer).setChecked(true);
    	}
    	else {
    		menu.findItem(R.id.menu_use_timer).setChecked(false);
    	}
    	
    	
    	return true;

    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch(item.getItemId()) {
    	case R.id.menu_set_classic:
    		if(!item.isChecked()) {
    			item.setChecked(true);
    			mValues.setMode(Puzzle.MODE_CLASSIC);
    		}
    		break;
    	case R.id.menu_clear_classic:
    		if (!item.isChecked()) {
    			item.setChecked(true);
    			mValues.setMode(Puzzle.MODE_EASY);
    		}
    		break;
    	case R.id.menu_choose_sd:
    		if(!item.isChecked()) {
    			item.setChecked(true);
    			mValues.setFileSource(Puzzle.FILESOURCE_SDCARD);
    			this.mStartCondition = Puzzle.START_FILE;
    			updateFileListStatus();
    		}
    		break;
    	case R.id.menu_choose_gallery:
    		if(!item.isChecked()) {
    			item.setChecked(true);
    			mValues.setFileSource(Puzzle.FILESOURCE_GALLERY);
    			this.mStartCondition = Puzzle.START_GALLERY;
    			if (this.mLastView == Puzzle.VIEW_FILES)  {
    	    		this.findViewById(R.id.button_gallery).setVisibility(View.VISIBLE);

    			}

    		}
    		break;
    	case R.id.menu_save_picture:
    		if(!item.isChecked()) {
    			item.setChecked(true);
    			mValues.setSavePicture(true);
    			this.mStartCondition = Puzzle.START_GAME_SAVED;
    			
    		}
    		else if(item.isChecked()) {
    			item.setChecked(false);
    			mValues.setSavePicture(false);
    			
    			if (mValues.getFileSource() == Puzzle.FILESOURCE_GALLERY) this.mStartCondition = Puzzle.START_GALLERY;
    			else this.mStartCondition = Puzzle.START_FILE;
    		}
    		break;
    	case R.id.menu_use_timer:
    		if(!item.isChecked()) {
    			item.setChecked(true);
    			mValues.setUsingTimerInTransition(true);
    			
    		}
    		else if(item.isChecked()) {
    			item.setChecked(false);
    			mValues.setUsingTimerInTransition(false);
    			
    		}
    		break;
    		
    	case R.id.menu_options_newgame:
    		mValues.setGettingNewPicture(true);
    		if (this.mValues.getFileSource() == Puzzle.FILESOURCE_GALLERY) {
    			this.mStartCondition = Puzzle.START_GALLERY;
    			this.launchGallery();
    		}
    		else {
    			mPath = new String("/");
            	mText.setText(mPath);
            	updateFileListStatus();
        		
        		showView(Puzzle.VIEW_FILES);

    		}
    		break;
    	case R.id.menu_options_scramble:
    		//mManager.scrambleTiles();
    		//mHandler.sendEmptyMessage(Puzzle.MSG_REDRAW);
    		mManager.launchScrambleTiles();
    		break;
    	case R.id.menu_showtext_help:
    		this.displayText(Puzzle.TEXT_HELP);
    		break;
    	case R.id.menu_showtext_credits:
    		this.displayText(Puzzle.TEXT_CREDITS);
    		break;
    	}
    	
    	mValues.saveSharedPreferences(Puzzle.this);
    	
    	return true;
    }
    
    public void updateFileListStatus() {
    	//gallery button
    	if (mValues.getFileSource() == Puzzle.FILESOURCE_SDCARD ) {
    		this.findViewById(R.id.button_gallery).setVisibility(View.GONE);
    	}
    	else if (mValues.getFileSource() == Puzzle.FILESOURCE_GALLERY) {
    		this.findViewById(R.id.button_gallery).setVisibility(View.VISIBLE);
    	}
    	
    	//record adapter
    	mList = getFileList();
        mAdapter = new RecordAdapter(Puzzle.this, R.layout.main_layout, mList);
        mAdapter.setNotifyOnChange(true);
		mAdapter.notifyDataSetChanged();
		setListAdapter(mAdapter);
    }
    
    public void showView(int mViewToShow) {
    	// blank out all views
    	this.findViewById(R.id.view_game).setEnabled(false);
    	this.findViewById(R.id.view_game).setVisibility(View.GONE);
    	this.findViewById(R.id.view_files).setEnabled(false);
    	this.findViewById(R.id.view_files).setVisibility(View.GONE);
    	this.findViewById(R.id.view_text).setEnabled(false);
    	this.findViewById(R.id.view_text).setVisibility(View.GONE);
    	this.findViewById(R.id.view_splash).setEnabled(false);
    	this.findViewById(R.id.view_splash).setVisibility(View.GONE);
    	
    	// show selected view
    	switch (mViewToShow) {
    	case Puzzle.VIEW_GAME:
    		this.findViewById(R.id.view_game).setEnabled(true);
    		this.findViewById(R.id.view_game).setVisibility(View.VISIBLE);
        	this.mLastView = mViewToShow;
    		if (mValues.isSavePicture()) {
    			this.mStartCondition = Puzzle.START_GAME_SAVED;
    		}
    		else if (mValues.getFileSource() == Puzzle.FILESOURCE_GALLERY)  {
    			this.mStartCondition = Puzzle.START_GALLERY;
    		}
    		else if (mValues.getFileSource() == Puzzle.FILESOURCE_SDCARD) {
    			this.mStartCondition = Puzzle.START_GAME_NEW;
    		}
			mValues.setStartCondition(mStartCondition);

    		
    		break;
    	case Puzzle.VIEW_FILES:
    		this.findViewById(R.id.view_files).setEnabled(true);
    		this.findViewById(R.id.view_files).setVisibility(View.VISIBLE);
        	this.mLastView = mViewToShow;

    		break;
    	case Puzzle.VIEW_TEXT:
    		this.findViewById(R.id.view_text).scrollTo(0, 0);
    		this.findViewById(R.id.view_text).setEnabled(true);
    		this.findViewById(R.id.view_text).setVisibility(View.VISIBLE);
    		break;
    	case Puzzle.VIEW_SCORES:
    		break;
    	case Puzzle.VIEW_SPLASH :
    		//this.findViewById(R.id.view_splash).scrollTo(0, 0);
    		this.findViewById(R.id.view_splash).setEnabled(true);
    		this.findViewById(R.id.view_splash).setVisibility(View.VISIBLE);
    		break;
    	}
    }
    
    public void displayText(int mText) {
    	this.showView(Puzzle.VIEW_TEXT);
    	switch (mText) {
    	case Puzzle.TEXT_HELP:
    		((TextView)this.findViewById(R.id.text_out)).setText(R.string.text_help);
    		break;
    	case Puzzle.TEXT_CREDITS:
    		((TextView)this.findViewById(R.id.text_out)).setText(R.string.text_credits);
    		break;
    	
    	}
    }
    
    public ArrayList<FileListNode> getFileList() {
    	ArrayList<FileListNode> mList = new ArrayList<FileListNode>();
    	mList.clear();
    	//Log.e("TAG", "path " + mPath);
    	File mFile = new File(mPath);
    	
    	
    	
    	if (mFile.isDirectory() && !mFile.isHidden() ) {
    		String names[] = mFile.list();
    		
    		for (int i = 0; i < names.length; i ++) {
    			FileListNode mNode = new FileListNode(names[i]);
    			
    			File mSub = new File(mFile.getAbsolutePath() + "/" + names[i]);
    			if (mSub.isFile()) {
    				
    				if (Puzzle.checkPathEnding(mSub.getName())) {
    					mNode = new FileListNode(FileListNode.TYPE_PIC_FILE, names[i]);
    				}
    				else {
    					mNode = new FileListNode(FileListNode.TYPE_FILE, names[i]);
    				}
    			}
    			else if (mSub.isDirectory()){
    				mNode = new FileListNode(FileListNode.TYPE_FOLDER, names[i]);
    			}
    			if (!mSub.isHidden() && mSub.canRead()) mList.add(mNode);
    		}
    	}
    	
    	return mList;
    }
    
    
    
    public void setGameStartVars(String mPath) {
    	int mDisplayWidth, mDisplayHeight;
    	int mNumTilesX , mNumTilesY;
    	int mTileX, mTileY;
    	float mScale = (float) 0.5;
    	float  mRatioImage, mRatioScreen;
    	
    	
    	mNumTilesX = 5;
    	mNumTilesY = 5;
    	
    	Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    	mDisplayWidth = display.getWidth();
    	mDisplayHeight = display.getHeight();
    	
 
		mBitmap = decodeFile(mPath);
		
		if (!checkBitmap(mBitmap)) {
			this.showView(Puzzle.VIEW_FILES);
			return;
		}
		
    	if(mBitmap.getWidth() > mBitmap.getHeight() ) { // height over width
    		Matrix mMatrix = new Matrix();
    		mMatrix.postRotate(90);
    		Bitmap mMap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 
    				mMatrix, true);
    		mBitmap = mMap;
    		//change orientation?
    	}
    	else {
    		
    		//leave orientation?
    	}
    	
    	mRatioImage = (float) mBitmap.getHeight()/ (float) mBitmap.getWidth();
    	mRatioScreen = (float) mDisplayHeight/ (float) mDisplayWidth;
    	
    	if (mRatioImage <= mRatioScreen) {
    		mScale = ( (float)mDisplayWidth /(float)mBitmap.getWidth());
    		//Log.e("Puzzle","here");

    	}
    	else {
    		//
    		mScale = ( (float)mDisplayHeight /(float)mBitmap.getHeight());

    	}
    	
    	mValues.setLargeMap(mBitmap);
    	
    	//calculate image ratio again!!
    	mTileX = mBitmap.getWidth() / mNumTilesX;
    	mTileY = mBitmap.getHeight() / mNumTilesY;
    	
    	mValues.setDisplayX(mDisplayWidth);
    	mValues.setDisplayY(mDisplayHeight);
    	
    	mValues.setTileX(mTileX);
    	mValues.setTileY(mTileY);
    	
    	mValues.setPicSizeX(mBitmap.getWidth());
    	mValues.setPicSizeY(mBitmap.getHeight());
    	
    	mValues.setOrientation(GameValues.ORIENTATION_PORTRAIT);
    	mValues.setNumTilesX(mNumTilesX);
    	mValues.setNumTilesY(mNumTilesY);
    	mValues.setScaleX(mScale);
    	mValues.setScaleY(mScale);
    	
    	
    	mManager.launchFillArray();
    }
    
    public void setNonRandomBlankTile() {
    	//non random blank tile
    	mValues.setBlankX(TileManager.NON_RANDOM_BLANK_TILE / 5	);
    	mValues.setBlankY(TileManager.NON_RANDOM_BLANK_TILE % 5 );
    	
    }
    
    private Bitmap decodeFile(String mP){
        Bitmap b = null;
        
        try {
        	
        	File mFile = new File(mP);
        	
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            FileInputStream fis = new FileInputStream(mFile);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();

            int IMAGE_MAX_SIZE = 320;
            
            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int)Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

            //Decode with inSampleSize
            FileInputStream fis2 = new FileInputStream(mFile);

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            b = BitmapFactory.decodeStream(fis2, null, o2);
            fis2.close();
            
        } catch (IOException e) {
        	e.printStackTrace();
        	mHandler.sendEmptyMessage(Puzzle.MSG_DIALOG_FILENOTFOUND);
        }
        
        return b;
    }
    
    public boolean checkBitmap(Bitmap mBitmap) {
    	boolean mReturn = true;
    	if (mBitmap == null) {
    		mReturn = false;
    	}
    	return mReturn;
    }

    
    protected Dialog onCreateDialog(int id) {
	    Dialog dialog;
	    AlertDialog.Builder builder;
    	AlertDialog alertDialog;

    	Context mContext = getApplicationContext();
    	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    	View layout;
    	TextView text;
    	ImageView image;
    	String mPositive, mNegative, mDialogText;
    	
	    switch(id) {
	    case Puzzle.MSG_DIALOG_FINISHED:
	    	
	    	layout = inflater.inflate(R.layout.congrats_dialog,
	    			(ViewGroup) findViewById(R.id.dialog_root));
	    	mDialogText = new String( "You solved the puzzle! Keep playing or exit now? " );
	    	text = (TextView) layout.findViewById(R.id.dialog_text);
	    	text.setText(mDialogText);
	    	
	    	image = (ImageView) layout.findViewById(R.id.dialog_image);
	    	image.setImageResource(R.drawable.ic_launcher);
	    	mPositive = new String("Keep Playing.");
   	    	mNegative = new String("Exit Now.");
	    	builder = new AlertDialog.Builder(this);
	    	builder.setView(layout);
	    	builder.setCancelable(false)
    	       .setPositiveButton(mPositive, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	  
    	        	   dialog.cancel();
    	        	   removeDialog(Puzzle.MSG_DIALOG_FINISHED);
    	        	   
    	           }
    	       })
    	       .setNegativeButton(mNegative, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   finish();

    	                dialog.cancel();
    	                removeDialog(Puzzle.MSG_DIALOG_FINISHED);
    	           }
    	       });
	    	
	    	alertDialog = builder.create();
	    	
	    	dialog = (Dialog) alertDialog;
	    	
	    	break;
	    //////////////////////////////
	    case Puzzle.MSG_DIALOG_FILENOTFOUND:
	    	sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, 
	    			Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	    	
	    	//Log.e("Puzzle", "here");
	    	
	    	layout = inflater.inflate(R.layout.congrats_dialog,
	    			(ViewGroup) findViewById(R.id.dialog_root));
	    	mDialogText = new String( "The filename of the source picture has changed " +
	    			"or the connection to the source picture has been broken. You must choose a new picture." );
	    	text = (TextView) layout.findViewById(R.id.dialog_text);
	    	text.setText(mDialogText);
	    	
	    	image = (ImageView) layout.findViewById(R.id.dialog_image);
	    	image.setImageResource(R.drawable.ic_launcher);
	    	mPositive = new String("Choose New Pic.");
   	    	mNegative = new String("Exit Now.");
	    	builder = new AlertDialog.Builder(this);
	    	builder.setView(layout);
	    	builder.setCancelable(false)
    	       .setPositiveButton(mPositive, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	  
    	        	   dialog.cancel();
    	        	   removeDialog(Puzzle.MSG_DIALOG_FILENOTFOUND);
    	        	   
    	           }
    	       })
    	       .setNegativeButton(mNegative, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   finish();

    	                dialog.cancel();
    	                removeDialog(Puzzle.MSG_DIALOG_FILENOTFOUND);
    	           }
    	       });
	    	
	    	alertDialog = builder.create();
	    	
	    	dialog = (Dialog) alertDialog;
	    	
	    	break;
	     ////////////////////////////////
	    default:
	        dialog = null;
	    }
	    return dialog;
	}
    
    
    private class SplashScreen extends AsyncTask<Integer, Void, String> {
 	   @Override
 	   protected void onPreExecute() {
 		   showView(Puzzle.VIEW_SPLASH);
 		   myFadeInAnimation = AnimationUtils.loadAnimation(Puzzle.this, R.anim.fadein);
 		   findViewById(R.id.view_splash).startAnimation(myFadeInAnimation);
 	   }
 	   
 	   @Override
 	   protected String doInBackground(Integer ... mSec) {
 		   String mReturn = new String("");
 		   try {
 			   Thread.sleep(3500);
 		   }
 		   catch (Exception e) {
 			   
 		   }
 		   return mReturn;
 	   }
 	   
 	   @Override
 	   protected void onPostExecute(String mResult) {
 		   myFadeInAnimation.scaleCurrentDuration(0);
 		   //showView(Players.VIEW_PLAYERS);
 		   onPostSplash();
 	   }
    }
    
    public Handler mHandler = new Handler() {
    	public void handleMessage(Message msg) {
    		
    		switch(msg.what) {
    		case Puzzle.MSG_REDRAW:
    			mManager.showAllTiles();
    			mTilesLayout.invalidate();
    			break;
    		case Puzzle.MSG_SWITCH:
    			((Tile) msg.obj ).setShift(Tile.SHIFT_DOWN);
    			mManager.switchWithBlank((Tile) msg.obj	, true);
    			break;
    		case Puzzle.MSG_SWITCH_CLASSIC:
    			((Tile) msg.obj ).setShift(Tile.SHIFT_DOWN);
    			mManager.switchWithBlankClassic((Tile) msg.obj	);
    			break;
    		case Puzzle.MSG_DIALOG_FINISHED:
    			showDialog(Puzzle.MSG_DIALOG_FINISHED);
    			break;
    		case Puzzle.MSG_DIALOG_FILENOTFOUND:
    			showDialog(Puzzle.MSG_DIALOG_FILENOTFOUND);
    			break;
    		}
    		
    	}
    };

	public LinearLayout getTilesLayout() {
		return mTilesLayout;
	}

	public void setTilesLayout(LinearLayout mTilesLayout) {
		this.mTilesLayout = mTilesLayout;
	}

}