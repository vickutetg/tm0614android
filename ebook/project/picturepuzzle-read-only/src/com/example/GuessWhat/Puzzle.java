package com.example.GuessWhat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.ClipData.Item;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.graphics.drawable.BitmapDrawable;

public class Puzzle extends Activity {

	/*
	public class ImagePieces {
		public Bitmap  bmp;
		public boolean isInGallery;
		public int     index; 
		
	};*/
	
	
	
	/// Tag for logging
	private static final String TAG = "PuzzleActivity";

	// number of pieces to split
	private static final int NUM_OF_PIECES = 9;

	// list to keep track of image pieces in the gallery
	List<Integer> mGalleryList=new ArrayList<Integer>();
	List<Bitmap> mBmpPiecesList=new ArrayList<Bitmap>();

	// bitmap array pieces
	//ImagePieces[] mImgPieces = new ImagePieces[NUM_OF_PIECES];
	Bitmap[] mBmpPieces=new Bitmap[NUM_OF_PIECES];
	boolean[] mBmpInGallery = new boolean[NUM_OF_PIECES];

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);

		int resId=getIntent().getIntExtra("BitmapImage", 0);
		Bitmap scratch=BitmapFactory.decodeResource(getResources(),resId);

		// create pieces
		createBitmapPieces(scratch);

		GridView gdView=(GridView) findViewById(R.id.gridviewToAssemble);

		gdView.setAdapter(new GridAdapter(this));
		
		gdView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg1, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					Log.v(TAG,"gridview on touch fired");
					
					GridView parent=(GridView)arg1;
					int x = (int) event.getX();
					int y = (int) event.getY();
					

					int position = parent.pointToPosition(x, y);
					ImageView viewToBeDragged=(ImageView) parent.getChildAt(position);
					
							if (viewToBeDragged.getTag() != null){
								
								ClipData.Item item=new ClipData.Item((CharSequence)viewToBeDragged.getTag().toString());
								//int t=(Integer)arg1.getTag();
								ClipDescription NOTE_STREAM_TYPES= new ClipDescription ((CharSequence)viewToBeDragged.getTag().toString(),new String[ ] {ClipDescription.MIMETYPE_TEXT_PLAIN});
								ClipData clipData=new ClipData(NOTE_STREAM_TYPES,item);
								clipData.addItem(item);
								DragShadowBuilder shadowBuilder=new  View.DragShadowBuilder(viewToBeDragged);
								viewToBeDragged.startDrag(clipData, shadowBuilder, viewToBeDragged, 0);
								//arg1.setVisibility(View.INVISIBLE);
								
								return true;
							}else{
								Log.v(TAG, "Invalid Tag ");
								return false;
							}
				}
				return true;
			}

		});
		
		/*gdView.setClickable(true);

		gdView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				Log.v(TAG, "OnLongListener Fired");
				if (arg1.getTag() != null){
					ClipData.Item item=new ClipData.Item((CharSequence)arg1.getTag().toString());
					//int t=(Integer)arg1.getTag();
					ClipDescription NOTE_STREAM_TYPES= new ClipDescription ((CharSequence)arg1.getTag().toString(),new String[ ] {ClipDescription.MIMETYPE_TEXT_PLAIN});
					ClipData clipData=new ClipData(NOTE_STREAM_TYPES,item);
					clipData.addItem(item);
					DragShadowBuilder shadowBuilder=new  View.DragShadowBuilder(arg1);
					arg1.startDrag(clipData, shadowBuilder, arg1, 0);
					//arg1.setVisibility(View.INVISIBLE);
					return true;
				}else{
					Log.v(TAG, "Invalid Tag ");
					return false;
				}
				
			}
		});*/
		
		

		gdView.setOnDragListener(new View.OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {

				int action=event.getAction();
				switch(event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					Log.v(TAG,"drag_Exited entered");

					break;
				case DragEvent.ACTION_DROP:
					int i;

					GridView parent=(GridView) v;
					GridAdapter adapter=(GridAdapter) parent.getAdapter();
					int x=(int)event.getX();
					int y=(int) event.getY();
					int position=parent.pointToPosition(x, y);
					int relativePosition=position-parent.getFirstVisiblePosition();
					Log.v(TAG,"position is = " + position);
					Log.v(TAG,"relative Position is = " + relativePosition);
					ImageView dragged=(ImageView) event.getLocalState();

					ImageView dropped=(ImageView)parent.getChildAt(relativePosition);

					if ((position>AdapterView.INVALID_POSITION) && (dropped.getDrawable() == null)){

						int imageindex=(Integer)dragged.getTag();
						Log.v(TAG,"dragged.getTag() = " + (Integer)dragged.getTag());

						if (mBmpInGallery[imageindex]){

							for (i=0;i<mGalleryList.size();i++){
								if (mGalleryList.get(i) == imageindex){
									break;
								}
							}
							if (i == mGalleryList.size()) imageindex = -1;

							//Log.v(TAG , "ImageIndex = " + imageindex);
							if ((imageindex >= 0) && (imageindex < NUM_OF_PIECES)){
								dropped.setImageBitmap(mBmpPieces[imageindex]);
								dropped.setTag(imageindex);

								mBmpInGallery[imageindex] = false;

								mGalleryList.remove(i);

								Log.v(TAG,"mGalleryList size = " + mGalleryList.size());
							//dragged.setVisibility(View.INVISIBLE);
							}
						}else{
							
							// not in gallery, should be in grid
							dropped.setImageBitmap(mBmpPieces[imageindex]);
							dropped.setTag(imageindex);
							
							dragged.setTag(null);
							dragged.setImageDrawable(null);

						}

						adapter.notifyDataSetChanged();

					}		
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					
					//v.invalidate();
					
					Log.v(TAG,"drag_Ended entered");
					Gallery gal= (Gallery) findViewById(R.id.GallerycutPieces);
					CutImagesAdapter galleryAdapter=(CutImagesAdapter) gal.getAdapter();
					galleryAdapter.notifyDataSetChanged();
					//adapter.notifyDataSetChanged();
					break;
				}
				return true;


			}

		});


		Gallery gallery=(Gallery) findViewById(R.id.GallerycutPieces);
		gallery.setAdapter(new CutImagesAdapter(this));
		gallery.setOnDragListener(new OnDragListener(){

			@Override
			public boolean onDrag(View v, DragEvent event) {
				int action=event.getAction();
				switch(event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					Log.v(TAG,"drag_Exited entered");

					break;
				case DragEvent.ACTION_DROP:
					Gallery parentGallery= (Gallery) v;
					CutImagesAdapter adapter=(CutImagesAdapter) parentGallery.getAdapter();
					int x=(int)event.getX();
					int y=(int) event.getY();
					int position=parentGallery.pointToPosition(x, y);
					Log.v(TAG,"gallery Position = " + position);
					ImageView dragged=(ImageView) event.getLocalState();
					int galleryIndex=(Integer)dragged.getTag();
					//if (position>AdapterView.INVALID_POSITION){
					//mGalleryList.add(galleryIndex);
					if (!mBmpInGallery[galleryIndex]){
					if ((position > 0 ) && (position < NUM_OF_PIECES)){
					mGalleryList.add(position,galleryIndex);
					}
					else {
						mGalleryList.add(position +1,galleryIndex);
						
					}
					mBmpInGallery[galleryIndex] = true;
					dragged.setTag(null);
					dragged.setImageDrawable(null);
					//adapter.notifyDataSetChanged();
					
					Log.v(TAG,"Gallery index = " + galleryIndex);
					}
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					
					
					break;
				}
				return true;


			}

				
			
		});
		gallery.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg1, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					Log.v(TAG,"allery on touch listener fired");
					Gallery parent =(Gallery) arg1;
					int x=(int)event.getX();
					int y=(int) event.getY();
					int position=parent.pointToPosition(x, y);
					
					ImageView viewDraggedFromGallery=(ImageView) parent.getChildAt(position);
					if(viewDraggedFromGallery.getTag() != null){
				ClipData.Item item=new ClipData.Item((CharSequence)viewDraggedFromGallery.getTag().toString());
				//int t=(Integer)arg1.getTag();
				ClipDescription NOTE_STREAM_TYPES= new ClipDescription ((CharSequence)viewDraggedFromGallery.getTag().toString(),new String[ ] {ClipDescription.MIMETYPE_TEXT_PLAIN});
				ClipData clipData=new ClipData(NOTE_STREAM_TYPES,item);
				clipData.addItem(item);
				DragShadowBuilder shadowBuilder=new  View.DragShadowBuilder(viewDraggedFromGallery);
				arg1.startDrag(clipData, shadowBuilder, viewDraggedFromGallery, 0);
				return true;
				}
					else{
						Log.v(TAG,"gallery on touch- Invalid Tag");
						return false;
						
					}
				}
				//arg1.setVisibility(View.INVISIBLE);
				return false;
			}
			
		});
		
		/*gallery.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				ClipData.Item item=new ClipData.Item((CharSequence)arg1.getTag().toString());
				//int t=(Integer)arg1.getTag();
				ClipDescription NOTE_STREAM_TYPES= new ClipDescription ((CharSequence)arg1.getTag().toString(),new String[ ] {ClipDescription.MIMETYPE_TEXT_PLAIN});
				ClipData clipData=new ClipData(NOTE_STREAM_TYPES,item);
				clipData.addItem(item);
				DragShadowBuilder shadowBuilder=new  View.DragShadowBuilder(arg1);
				arg1.startDrag(clipData, shadowBuilder, arg1, 0);
				//arg1.setVisibility(View.INVISIBLE);
				return true;
			}

		});*/




	}

	public class GridAdapter extends BaseAdapter{
		private Context mContext;

		public GridAdapter(Context con) {
			mContext=con;
		}

		@Override
		public int getCount() {
			return mBmpPieces.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ImageView iView;
			if (arg1 == null) {  // if it's not recycled, initialize some attributes
				iView = new ImageView(mContext);
				iView.setLayoutParams(new GridView.LayoutParams(225,200));
				iView.setScaleType(ImageView.ScaleType.FIT_XY);
				iView.setPadding(2,2,2,2);
				iView.setBackgroundColor(Color.CYAN);

			} else {
				iView = (ImageView) arg1;

			}
			/*ColorMatrix matrix=new ColorMatrix();
		matrix.setSaturation(0);
		ColorMatrixColorFilter filter=new ColorMatrixColorFilter(matrix);

        iView.setImageBitmap(mBmpPieces[arg0]);
        iView.setColorFilter(filter);
        iView.setAlpha(200);*/





			return iView;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puzzle, menu);
		return true;
	}

	/// function to create pieces
	void createBitmapPieces(Bitmap source){

		int k=0;
		int width=source.getWidth();
		int height=source.getHeight();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				mBmpPieces[k]=Bitmap.createBitmap(source,(width*j)/3,(i*height)/3,width/3,height/3);
				mBmpInGallery[k] = true;
				mGalleryList.add(k);
				k++;
			}
		}
		// shuffle pieces
		Collections.shuffle(mGalleryList);
	} 

	public class CutImagesAdapter extends BaseAdapter {

		private Context ctx;
		int defaultItemBackground;

		public CutImagesAdapter(Context c) {
			ctx = c;
			TypedArray tarray = obtainStyledAttributes(R.styleable.Gallery1);
			defaultItemBackground = tarray.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
			tarray.recycle();
		}

		@Override
		public int getCount() {

			return mBmpPieces.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}


		public void removeItem(int position)
		{
			Log.v(TAG,"RemoveItem " + position);
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			ImageView img = new ImageView(ctx);
			//int index = -1;

			if (arg0 < mGalleryList.size()){
				int index=mGalleryList.get(arg0);
				img.setImageBitmap(mBmpPieces[index]);
				img.setTag(index);
				Log.v(TAG,"Gallery View index = " + index);
				img.setScaleType(ImageView.ScaleType.FIT_XY);
				img.setLayoutParams(new Gallery.LayoutParams(300,200));
				//img.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.FILL_PARENT,Gallery.LayoutParams.FILL_PARENT));
				
				img.setBackgroundResource(defaultItemBackground);
			}
			//Log.v(TAG, "Setting index = " + index + "arg = " + arg0);

			return img;


		}

	}



}




