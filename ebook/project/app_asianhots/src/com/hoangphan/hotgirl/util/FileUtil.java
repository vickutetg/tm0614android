package com.hoangphan.hotgirl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;

public class FileUtil
{
	public static InputStream in = null;
	public static ByteArrayOutputStream out = null;
	
	//Đọc file trả về kiểu byte
	public static byte[] readFileFromAssets(Context context, String filename)
	{
		in  = null; 					
		out = null; 	
		byte[] readBuffer = new byte[128]; 	
		try
		{
			int size = 0;
			in  = context.getAssets().open(filename);
			out = new ByteArrayOutputStream();
			while ((size = in.read(readBuffer)) > 0)
			{
				out.write(readBuffer, 0, size);
			}
			out.close();
			in.close();
		}
		catch (Exception e)
		{
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (Exception ignore)
				{
				}
			}
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (Exception ignore)
				{
				}
			}
			return null;
		}
		return out.toByteArray();
	}
	
	//Đọc file văn bản txt
	public static String readFileText(Context context)
	{
		in  = null; 					
		out = null;
		try
        {
			String filename = "xml/about.txt";
        	in  = context.getAssets().open(filename);
        	out = new ByteArrayOutputStream();
			
			int i = in.read();
			while (i != -1)
            {
				out.write(i);
				i = in.read();
            }
			in.close();	        
		}
        catch (IOException e)
        {
			e.printStackTrace();
		}
		return out.toString();
	}
}