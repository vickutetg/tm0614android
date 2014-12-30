package com.example.GuessWhat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SelectCategory extends Activity {
	circleView cView;
	//Button btn01,btn02,btn03,btn04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		int numOfElements=5;
		
		View[] elems=new View[numOfElements];
		
			 final ImageView iv1=new ImageView(this);
			 
			 iv1.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.strawberry,25,25));
			
			//iv1.setImageResource(R.drawable.strawberry);
			
			//iv1.setAdjustViewBounds(true);
			//iv1.setMaxHeight(250);
			//iv1.setMaxWidth(150);
			iv1.setBackgroundResource(R.drawable.imgborder);
			iv1.setLayoutParams(new RelativeLayout.LayoutParams(
	                RelativeLayout.LayoutParams.WRAP_CONTENT,
	                RelativeLayout.LayoutParams.WRAP_CONTENT));
			elems[0]=iv1;
			
			
			iv1.setOnClickListener(new OnClickListener(){

				public void onClick(View arg0) {
					Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim );
					shake.reset();
					shake.setFillAfter(true);
					iv1.startAnimation(shake);
					Intent intent=new Intent(arg0.getContext(),ImageGallery.class);
					startActivity(intent);
				}
				
			});
			
			final ImageView iv2=new ImageView(this);
			 iv2.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.blueberries,50,50));
			//iv2.setImageResource(R.drawable.duck);
			
			//iv2.setAdjustViewBounds(true);
			//iv2.setMaxHeight(250);
			//iv2.setMaxWidth(150);
			iv2.setBackgroundResource(R.drawable.imgborder);
			
			iv2.setLayoutParams(new RelativeLayout.LayoutParams(
	                RelativeLayout.LayoutParams.WRAP_CONTENT,
	                RelativeLayout.LayoutParams.WRAP_CONTENT));
			elems[1]=iv2;
			
			iv2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim );
					shake.reset();
					shake.setFillAfter(true);
					iv2.startAnimation(shake);
					
				}
				
			});
			
			final ImageView iv3=new ImageView(this);
			
			 iv3.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.cars,50,50));
			
			//iv3.setImageResource(R.drawable.cars);
			
			//iv3.setAdjustViewBounds(true);
			//iv3.setMaxHeight(250);
			//iv3.setMaxWidth(150);
			iv3.setBackgroundResource(R.drawable.imgborder);
			iv3.setLayoutParams(new RelativeLayout.LayoutParams(
	                RelativeLayout.LayoutParams.WRAP_CONTENT,
	                RelativeLayout.LayoutParams.WRAP_CONTENT));
			elems[2]=iv3;
			
			iv3.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim );
					shake.reset();
					shake.setFillAfter(true);
					iv3.startAnimation(shake);
				}
				
			});
			
	
			
			final ImageView iv4=new ImageView(this);
			 iv4.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.scene1,50,50));
			//iv4.setImageResource(R.drawable.scene1);
			
			//iv4.setAdjustViewBounds(true);
			//iv4.setMaxHeight(250);
			//iv4.setMaxWidth(150);
			iv4.setBackgroundResource(R.drawable.imgborder);
			iv4.setLayoutParams(new RelativeLayout.LayoutParams(
	                RelativeLayout.LayoutParams.WRAP_CONTENT,
	                RelativeLayout.LayoutParams.WRAP_CONTENT));
			elems[3]=iv4;
			
			iv4.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim );
					shake.reset();
					shake.setFillAfter(true);
					iv4.startAnimation(shake);
				}
				
			});
			
final ImageView iv5=new ImageView(this);
iv5.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.tangfish,50,50));
			
			//iv5.setImageResource(R.drawable.tangfish);
			
			//iv5.setAdjustViewBounds(true);
			//iv5.setMaxHeight(250);
			//iv5.setMaxWidth(150);
			iv5.setBackgroundResource(R.drawable.imgborder);
			iv5.setLayoutParams(new RelativeLayout.LayoutParams(
	                RelativeLayout.LayoutParams.WRAP_CONTENT,
	                RelativeLayout.LayoutParams.WRAP_CONTENT));
			elems[4]=iv5;
			
			iv5.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim );
					shake.reset();
					shake.setFillAfter(true);
					iv5.startAnimation(shake);
				}
				
			});
			cView = new circleView(this, 250, elems);
            setContentView(cView);
		/*setContentView(R.layout.activity_select_category);
		btn01=(Button) findViewById(R.id.Button01);
		btn02=(Button) findViewById(R.id.Button02);
		btn03=(Button) findViewById(R.id.Button03);
		btn04=(Button) findViewById(R.id.Button04);
		btn01.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(arg0.getContext(),ImageGallery.class);
				startActivity(intent);
				
			}
			
		});*/
		
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 10;

    if (height > reqHeight || width > reqWidth) {

        // Calculate ratios of height and width to requested height and width
        final int heightRatio = Math.round((float) height / (float) reqHeight);
        final int widthRatio = Math.round((float) width / (float) reqWidth);

        // Choose the smallest ratio as inSampleSize value, this will guarantee
        // a final image with both dimensions larger than or equal to the
        // requested height and width.
        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
    }

    return inSampleSize;
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_category, menu);
		return true;
	}
	
	

}
