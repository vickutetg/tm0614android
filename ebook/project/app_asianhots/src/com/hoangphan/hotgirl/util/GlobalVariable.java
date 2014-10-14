package com.hoangphan.hotgirl.util;

import java.util.ArrayList;
import java.util.List;
import com.hoangphan.hotgirl.data.Albums;

import android.content.res.Configuration;
import android.graphics.Bitmap;

public class GlobalVariable
{
	public static int PANLEFT		= 0;
	public static int PANRIGHT		= 0;
	public static int IMAGESELECT 	= 0;
	
	public static String ALBUMSID		= "";
	public static String ALBUMSTITLE	= "";
	
	public static boolean ISCOVER 			= false;
	public static boolean SELECTPOSITION 	= false;
	public static boolean SETONCLICK 		= false;
	
	public static Configuration config;
	public static List<Albums> mAlbumsGrid = new ArrayList<Albums>();
	public static byte[] byteBuffer = new byte[128];
	public static Bitmap mBitmap	= null;
    
    public static void clearBitmap()
    {
    	if(mBitmap != null)
    	{
    		mBitmap.recycle();
    		mBitmap = null;
    	}
    }
}