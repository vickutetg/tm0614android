package com.hoangphan.cookie; 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ImagesView extends Activity {

    private ViewFlipper flipper;// to slides views
	private float inintialTouchX;// x position of first touch on screen

	static Images appImages; // object for the class that holds our images
	private GridView view1, view2;// pointers to each slide (each slide is a
									// grid view)

	private TextView title;// reference to the title of the current slide

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		// initializing out images
		appImages = new Images();

		title = (TextView) findViewById(R.id.title);

		view1 = (GridView) findViewById(R.id.grid_view);
		view2 = (GridView) findViewById(R.id.grid_view2);

		/*
		 * passing our adapter to the view to show our images we set before.
		 */

		view1.setAdapter(new ArtOfCookie(this, appImages.getSet1()));
		view2.setAdapter(new ArtOfCookie(this, appImages.getSet2()));

		// set item listner for the grid of each slide
		view1.setOnItemClickListener(new imageListener());
		view2.setOnItemClickListener(new imageListener());
		// add touch listner to recognize sweeping
		view1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
				// TODO Auto-generated method stub
				handleSweepOnScreen(paramMotionEvent);
				return false;
			}
		});
		view2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
				// TODO Auto-generated method stub
				handleSweepOnScreen(paramMotionEvent);
				return false;
			}
		});

		flipper = (ViewFlipper) findViewById(R.id.flipper);

		inintialTouchX = 0;
	}

	/*
	 * Handling sweeping on the screen
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		handleSweepOnScreen(event);
		return false;
	}

	/*
	 * the actual method for detecting touch on screen with sweeping
	 */
	private void handleSweepOnScreen(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// touched the screen
			inintialTouchX = event.getX();
			break;
		case MotionEvent.ACTION_UP:// release the touch
			float finalX = event.getX();
			// detect the current view to decide which one to be displayed
			if (inintialTouchX > finalX) {// sweep from right to left
				if (flipper.getDisplayedChild() == 1)// no next view
					break;
				// set animation
				flipper.setInAnimation(this, R.anim.in_right);
				flipper.setOutAnimation(this, R.anim.out_left);
				flipper.showNext();
				// change title to main dishes
				title.setText(R.string.dishes_title);
			} else if (inintialTouchX < finalX) {// swap from left to right
				if (flipper.getDisplayedChild() == 0)// no prev view
					break;
				// set animation
				flipper.setInAnimation(this, R.anim.in_left);
				flipper.setOutAnimation(this, R.anim.out_right);
				// change title to main dishes
				title.setText(R.string.sweets_title);
				flipper.showPrevious();

			}
			break;
		}
	}

	/*
	 * this is an inner class to handle item listener for each of our slids
	 */
	class imageListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> paramAdapterView,
				View paramView, int position, long paramLong) {
			// intent to open the details when image
			// selected
			Intent imageDetailsIntent = new Intent(getApplicationContext(),
					ImageDetails.class);
			// sending the selected item position in its array and also the
			// slide number
			imageDetailsIntent.putExtra("pos", position);
			imageDetailsIntent
					.putExtra("slideNum", flipper.getDisplayedChild());
			startActivity(imageDetailsIntent);
		}

	}
}
