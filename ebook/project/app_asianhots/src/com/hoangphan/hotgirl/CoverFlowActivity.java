package com.hoangphan.hotgirl;

import com.hoangphan.hotgirl.coverflow.CoverFlow;
import com.hoangphan.hotgirl.util.FileUtil;
import com.hoangphan.hotgirl.util.GlobalVariable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class CoverFlowActivity extends Activity
{
	private String maxSize;	
	private Bundle extra;
	private TextView txtTitle;
	private TextView txtSeekBar;
	private TextView txtTitleImage;
	private SeekBar seekBar;
	private ImageButton btnBack;
	private Activity mActivity;
	private CoverFlow coverFlow;
	private LinearLayout layoutConverFlow;
	private LinearLayout layoutMain;
	private RelativeLayout relayoutConverFlow;
	private ImageAdapter coverImageAdapter;
	private ParserTask task;
	
	private boolean checkSeekBar = false;
	private int count;
	private int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;
	private int FILL_PARENT  = LinearLayout.LayoutParams.FILL_PARENT;
	
	private Bitmap originalImage;
	private Bitmap reflectionImage;
	private Bitmap bitmapWithReflection;
	private ImageView[] mImages;
	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.coverflow_list_activity);
	    mActivity 				= this;
	    layoutConverFlow 		= (LinearLayout)findViewById(R.id.layout_coverflow);
	    layoutMain				= (LinearLayout)findViewById(R.id.cover_flow_view_image);
	    txtSeekBar 				= (TextView)findViewById(R.id.txt_seekbar_coverflow);
	    txtTitle 				= (TextView)findViewById(R.id.txt_title_coverflow);
	    btnBack 				= (ImageButton)findViewById(R.id.btn_back_coverflow);
	    seekBar 				= (SeekBar)findViewById(R.id.seekbar_coverflow);
	    txtTitleImage 			= (TextView)findViewById(R.id.txt_title_image_coverflow);
	    relayoutConverFlow		= (RelativeLayout)findViewById(R.id.relative_layout_coverflow);
	    
	    extra 		= getIntent().getExtras();
	    count 		= Integer.valueOf(extra.getString("COUNT"));
	    
	    txtTitle.setText(GlobalVariable.ALBUMSTITLE);

        btnBack.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				finish();				
			}
		});
        
        seekBar.setOnTouchListener(new OnTouchListener()
        {
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				checkSeekBar = true;
				return false;
			}
		});
        
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if(checkSeekBar)
				{
					count = progress;
					txtTitleImage.setText(GlobalVariable.mAlbumsGrid.get(GlobalVariable.mAlbumsGrid.size()-progress-1).getTitle());
					txtSeekBar.setText(String.valueOf(GlobalVariable.mAlbumsGrid.size()-progress) + "/" + maxSize);
					coverFlow.setSelection(progress, true);
					GlobalVariable.IMAGESELECT = progress;
				}
			}
		});
        
	    coverFlow = new CoverFlow(mActivity);
	    LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(FILL_PARENT, WRAP_CONTENT);
		coverFlow.setLayoutParams(rootParams);
		coverFlow.setAdapter(new ImageAdapter(mActivity));
	    task = new ParserTask(mActivity);
	    task.execute();
	}
	
	public class ImageAdapter extends BaseAdapter
	{
	    int mGalleryItemBackground;
	    private Context mContext;
	    private int countIndex;
	    private Canvas canvas;
	    private Matrix matrix;
	    private Paint deafaultPaint;
	    private Paint paint;
	    private LinearGradient shader;

	    public ImageAdapter(Context c)
	    {
	    	mContext = c;
	    	mImages = new ImageView[GlobalVariable.mAlbumsGrid.size()];
	    }
	    
		public boolean createReflectedImages()
		{
			//The gap we want between the reflection and the original image
		    final int reflectionGap = 4;

		    int index = 0;
		    for (int i = 0; i < GlobalVariable.mAlbumsGrid.size(); i++)
		    {
		    	countIndex++;
		        originalImage = decodeFile(GlobalVariable.mAlbumsGrid.get(i).getImage());
		        GlobalVariable.byteBuffer = null;
			    int width = originalImage.getWidth();
			    int height = originalImage.getHeight();
			    
			    //This will not scale but will flip on the Y axis
			    matrix = new Matrix();
			    matrix.preScale(1, -1);

			    //Create a Bitmap with the flip matrix applied to it.
			    //We only want the bottom half of the image
			    reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);

			    //Create a new bitmap with same width but taller to fit reflection
			    bitmapWithReflection = Bitmap.createBitmap(width, (height + height/2), Config.ARGB_8888);

			    //Create a new Canvas with the bitmap that's big enough for
			    //the image plus gap plus reflection
			    canvas = new Canvas(bitmapWithReflection);
			    //Draw in the original image
			    canvas.drawBitmap(originalImage, 0, 0, null);
			    //Draw in the gap
			    deafaultPaint = new Paint();
			    canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
			    //Draw in the reflection
			    canvas.drawBitmap(reflectionImage ,0, height + reflectionGap, null);

			    //Create a shader that is a linear gradient that covers the reflection
			    paint = new Paint();
			    shader = new LinearGradient(0, originalImage.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff,TileMode.CLAMP);
			    //Set the paint to use this shader (linear gradient)
			    paint.setShader(shader);
			    //Set the Transfer mode to be porter duff and destination in
			    paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			    //Draw a rectangle using the paint with our linear gradient
			    canvas.drawRect(0, height, width,
			    bitmapWithReflection.getHeight() + reflectionGap, paint);
	    
			    imageView = new ImageView(mContext);
			    imageView.setImageBitmap(bitmapWithReflection);
			    imageView.setLayoutParams(new CoverFlow.LayoutParams(145, 340));
			    imageView.setScaleType(ScaleType.MATRIX);
			    mImages[index++] = imageView;
		    }
		    
		    return true;
		}
		
		private Bitmap decodeFile(String filename)
		{
		    filename = "images/" + GlobalVariable.ALBUMSID + "/smallimage/" + filename;
		    GlobalVariable.byteBuffer = FileUtil.readFileFromAssets(mContext, filename);		    
		    return BitmapFactory.decodeByteArray(GlobalVariable.byteBuffer, 0, GlobalVariable.byteBuffer.length);
		}

	    public int getCount()
	    {
	        return GlobalVariable.mAlbumsGrid.size();
	    }

	    public Object getItem(int position)
	    {
	        return position;
	    }

	    public long getItemId(int position)
	    {
	        return position;
	    }

	    public View getView(final int position, View convertView, ViewGroup parent)
	    {
	    	return mImages[position];
	    }
		 /** Returns the size (0.0f to 1.0f) of the views
	     * depending on the 'offset' to the center. */
	     public float getScale(boolean focused, int offset)
	     {
	       /* Formula: 1 / (2 ^ offset) */
	         return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset)));
	     }

	}

	public void setSeekBar(int progress)
	{
		count = progress;
		checkSeekBar = false;
		txtTitleImage.setText(GlobalVariable.mAlbumsGrid.get(progress).getTitle());
		txtSeekBar.setText(String.valueOf(progress+1) + "/" + maxSize);
		seekBar.setProgress(progress);
	}
	
	private class ParserTask extends AsyncTask<String, Integer, Long>
	{
		private ProgressDialog mProgressDialog;
		
		private ParserTask(Activity context)
		{
			mActivity = context;
		}
		
		@Override
		protected void onPreExecute()
		{
			mProgressDialog = new ProgressDialog(mActivity);
			mProgressDialog.setMessage(mActivity.getString(R.string.msg_loading));
			mProgressDialog.show();
		}
		
		@Override
		protected Long doInBackground(String... params)
		{			
		    coverImageAdapter = new ImageAdapter(mActivity);		    
		    coverImageAdapter.createReflectedImages();		    
			return null;
		}
		
		@Override
		protected void onPostExecute(Long result)
		{
			coverFlow.setAdapter(coverImageAdapter);
			mProgressDialog.dismiss();
			coverFlow.setSpacing(-15);
		    coverFlow.setSelection(count, true);
		    layoutConverFlow.addView(coverFlow);
		    relayoutConverFlow.setVisibility(View.VISIBLE);		    
		    seekBar.setMax(GlobalVariable.mAlbumsGrid.size()-1);
		    seekBar.setProgress(count);
	        maxSize = String.valueOf(GlobalVariable.mAlbumsGrid.size());
	        txtSeekBar.setText(String.valueOf(count+1)+ "/" + maxSize);
	        txtTitleImage.setText(GlobalVariable.mAlbumsGrid.get(count).getTitle());
		}
	}

	public void getImages() 
	{
		releaseMemory();
		Intent returnResult = new Intent(GlobalVariable.ALBUMSID);
		returnResult.putExtra("COUNT", String.valueOf(count));
		setResult(RESULT_OK, returnResult);
		finish();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		releaseMemory();
		if(task != null)
		{
			task = null;
		}
	}
	
	public void releaseMemory()
	{
		layoutMain.removeAllViews();
		mImages = null;
		imageView = null;
		if(originalImage !=null)
	    {
	    	originalImage.recycle();
	    	originalImage = null;
	    }
		if(reflectionImage !=null)
	    {
	    	reflectionImage.recycle();
	    	reflectionImage = null;
	    }
		if(bitmapWithReflection !=null)
	    {
	    	bitmapWithReflection.recycle();
	    	bitmapWithReflection = null;
	    }
		GlobalVariable.byteBuffer = null;		
		System.gc();
	}
}
