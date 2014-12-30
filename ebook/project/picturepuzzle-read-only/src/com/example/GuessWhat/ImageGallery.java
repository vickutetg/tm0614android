package com.example.GuessWhat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageGallery extends Activity {
	//private static final String TAG = "ImageGalleryActivity";
	Integer[] fruitPics={
			R.drawable.strawberry,
			R.drawable.duck ,
			R.drawable.apples,
			R.drawable.oranges,
			R.drawable.avocado,
			R.drawable.cherry,
			R.drawable.grapes
	};
	ImageView imageView;
	//Bitmap bitmap;
	int resourceId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_gallery);
		imageView=(ImageView)findViewById(R.id.ImageView01);
		Gallery ga=(Gallery)findViewById(R.id.Gallery01);
		
		ga.setAdapter(new ImageAdapter(this));
		
		ga.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				imageView.setImageResource(fruitPics[arg2]);
				 resourceId=fruitPics[arg2];
				 
				/*imageView.setDrawingCacheEnabled(true);
				imageView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
			            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			imageView.layout(0, 0, imageView.getMeasuredWidth(), imageView.getMeasuredHeight()); 
				imageView.buildDrawingCache(true);
				bitmap=Bitmap.createBitmap(imageView.getDrawingCache());
				imageView.setDrawingCacheEnabled(false);*/
				
				
			}
			
		});
		
	}
	public void onBtnStartClicked(View v){
		//if (v.getId() == R.id.btnStart){
		
			Intent i= new Intent(this,Puzzle.class);
			
			i.putExtra("BitmapImage", resourceId);
			startActivity(i);
		//}
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_gallery, menu);
		return true;
	}
	
	public class ImageAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
    	public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
    	public int getCount() {
    		
    		return fruitPics.length;
    	}

    	@Override
    	public Object getItem(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public long getItemId(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public View getView(int arg0, View arg1, ViewGroup arg2) {
    		ImageView iv = new ImageView(ctx);
    		iv.setImageResource(fruitPics[arg0]);
    		//Log.v(TAG,"value of arg0 = " + arg0);
    		iv.setScaleType(ImageView.ScaleType.FIT_XY);
    		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
    		iv.setBackgroundResource(imageBackground);
    		return iv;
    	}

    }
	
	

}
