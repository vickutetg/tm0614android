package com.hoangphan.cookie;       

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDetails extends Activity {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// settung the xml file that will be shown
		setContentView(R.layout.image_details);
		/**
		 * The xml file contains two components an imageview and a text filed
		 * scrollable so the next two lines for pointing to these components to
		 * edit them
		 */

		ImageView selected = (ImageView) findViewById(R.id.shownImageId);
		TextView tView = (TextView) findViewById(R.id.textId);

		// get pos of selected image
		int pos = getIntent().getExtras().getInt("pos");
		int slideNum = getIntent().getExtras().getInt("slideNum");
		
		String imageName;// name of the selected image
		if (slideNum == 0) {// set1
			imageName = getResources().getResourceName(
					ImagesView.appImages.getSet1()[pos]);
			selected.setImageResource(ImagesView.appImages.getSet1()[pos]);
		} else {
			// set2
			imageName = getResources().getResourceName(
					ImagesView.appImages.getSet2()[pos]);
			selected.setImageResource(ImagesView.appImages.getSet2()[pos]);
		}
		// parsing the image name to get the name without its path
		String[] splitt = imageName.split("/");
		imageName = splitt[1];

		// getting the text of the selected image
		int textsrcId = getResources().getIdentifier(imageName, "string",
				getPackageName());
		tView.setText(getString(textsrcId));
	}
}
