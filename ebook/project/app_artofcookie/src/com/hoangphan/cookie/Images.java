package com.hoangphan.cookie;       
       
public class Images {

    // dividing all images to two groups
	private Integer[] imageSet1, imageSet2;

	public Images() {
		// initializing images
		imageSet1 = new Integer[10];
		imageSet1[0] = R.drawable.set1_applepie;
		for (int i = 1; i < imageSet1.length; i++)
			imageSet1[i] = imageSet1[i - 1] + 1;

		// do the same for the second set
		imageSet2 = new Integer[10];
		imageSet2[0] = R.drawable.set2_aareekah;
		for (int i = 1; i < imageSet2.length; i++)
			imageSet2[i] = imageSet2[i - 1] + 1;
	}

	/*
	 * Getters
	 */

	public Integer[] getSet1() {
		return imageSet1;
	}

	public Integer[] getSet2() {
		return imageSet2;
	}
}
