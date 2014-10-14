package com.hoangphan.cookie;       
       
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class ArtOfCookie extends BaseAdapter {

    private Context c;// pointer to the input context
	private Integer[] images; // pointer to the input images
	private int width,height;
	// constructor
	public ArtOfCookie(Context c, Integer[] adapterImages) {
		this.c = c;
		images = adapterImages;
		// getting screen size to split it to halfs

		WindowManager wm = (WindowManager) c
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		Point size = new Point();
		display.getSize(size);
		 width = size.x;
		 height = size.y;
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int imagePos) {
		return images[imagePos];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	/*
	 * Method that create a new ImageView for each item referenced by the
	 * Adapter
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// create an image view to hold our selected image
		ImageView imageView = new ImageView(c);
		imageView.setImageResource(images[position]);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams((width/2)-5, height/5));

		return imageView;
	}

}
