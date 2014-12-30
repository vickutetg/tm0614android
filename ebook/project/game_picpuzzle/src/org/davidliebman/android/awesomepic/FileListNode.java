package org.davidliebman.android.awesomepic;


public class FileListNode {

	public String mName = new String("none");
	public int mType = 0;
	
	public static final int TYPE_FILE = 0;
	public static final int TYPE_FOLDER = 1;
	public static final int TYPE_PIC_FILE = 2;
	public static final int TYPE_UNKNOWN = 3;
	
	public FileListNode(int type, String name) {
		mName = name;
		mType = type;
	}
	public FileListNode (String name) {
		mName = name;
		mType = TYPE_UNKNOWN;
	}
	public FileListNode () {
		mType = TYPE_UNKNOWN;
	}
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public int getType() {
		return mType;
	}
	public void setType(int mType) {
		this.mType = mType;
	}
	//
}
