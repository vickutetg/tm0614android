package com.hoangphan.hotgirl.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hoangphan.hotgirl.data.Albums;
import android.content.Context;

public class XMLUtil
{
	private static InputStream 				inputStream;
	private static Document 				doc;
	private static DocumentBuilder 			db;
	private static DocumentBuilderFactory 	dbf;
	
	//Parser data từ file AlbumsList XML vào mảng List
	public static List<Albums> parserAlbumsListData(Context mContext)
	{
		try
		{
			inputStream = mContext.getAssets().open("xml/albumslist.xml");
			dbf 		= DocumentBuilderFactory.newInstance();
		    db 			= dbf.newDocumentBuilder();
		    doc 		= db.parse(inputStream);		    
		    doc.getDocumentElement().normalize();
		    NodeList nodeLst = doc.getElementsByTagName("items");
			List<Albums> mAlbums = new ArrayList<Albums>();
			for (int i = 0; i < nodeLst.getLength(); i++)
		    {
	      	    Node note = nodeLst.item(i);      	    
	      	    if (note.getNodeType() == Node.ELEMENT_NODE)
	      	    {
	      	    	//ID
	      	    	Element idElement 		= (Element) note;
	      	    	NodeList noteID 		= idElement.getElementsByTagName("id");
	      	    	Element ID 				= (Element) noteID.item(0);
	      	    	NodeList nodelistID 	= ID.getChildNodes();
	      	    	//Title
	      	    	Element titleElement 	= (Element) note;
	      	    	NodeList noteTitle 		= titleElement.getElementsByTagName("title");
	      	    	Element title 			= (Element) noteTitle.item(0);
	      	    	NodeList nodelistTitle 	= title.getChildNodes();
	      	        //Image
	      	        Element imagesElement 	= (Element) note;
	    	    	NodeList noteImage 		= imagesElement.getElementsByTagName("image");
	    	    	Element image 			= (Element) noteImage.item(0);
	    	    	NodeList nodelistImage 	= image.getChildNodes();
	    	        
	    	    	String valueID 		= ((Node) nodelistID.item(0)).getNodeValue();
	    	        String valueTitle 	= ((Node) nodelistTitle.item(0)).getNodeValue();
	    	        String imagesName 	= ((Node) nodelistImage.item(0)).getNodeValue();
	    	        
	    	        mAlbums.add(new Albums(valueID, valueTitle, imagesName));
	      	    }
		    }	
			return mAlbums;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static List<Albums> parserAlbumsGridData(Context mContext, String filename)
	{
		try
		{
			inputStream = mContext.getAssets().open("xml/" + filename + ".xml");
			dbf 		= DocumentBuilderFactory.newInstance();
		    db 			= dbf.newDocumentBuilder();
		    doc 		= db.parse(inputStream);		    
		    doc.getDocumentElement().normalize();
		    NodeList nodeLst = doc.getElementsByTagName("items");
			List<Albums> mAlbums = new ArrayList<Albums>();
			for (int i = 0; i < nodeLst.getLength(); i++)
		    {
	      	    Node note = nodeLst.item(i);      	    
	      	    if (note.getNodeType() == Node.ELEMENT_NODE)
	      	    {
	      	    	//Title
	      	    	Element titleElement 	= (Element) note;
	      	    	NodeList noteTitle 		= titleElement.getElementsByTagName("title");
	      	    	Element title 			= (Element) noteTitle.item(0);
	      	    	NodeList nodelistTitle 	= title.getChildNodes();
	      	        //Image
	      	        Element imagesElement 	= (Element) note;
	    	    	NodeList noteImage 		= imagesElement.getElementsByTagName("image");
	    	    	Element image 			= (Element) noteImage.item(0);
	    	    	NodeList nodelistImage 	= image.getChildNodes();
	    	        
	    	        String valueTitle 	= ((Node) nodelistTitle.item(0)).getNodeValue();
	    	        String imagesName 	= ((Node) nodelistImage.item(0)).getNodeValue();
	    	        
	    	        mAlbums.add(new Albums(valueTitle, imagesName));
	      	    }
		    }	
			return mAlbums;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
