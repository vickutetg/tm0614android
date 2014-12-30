package org.davidliebman.android.awesomepic;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecordAdapter extends ArrayAdapter {

	private Context mContext;
	private ArrayList<FileListNode> mList;
	
	public RecordAdapter(Context context, int textViewResourceId, ArrayList<FileListNode> list) {
		super(context, textViewResourceId, list);
		mContext = context;
		mList = list;
	}

	@Override
	public View getView(int position, View mConvertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
		mConvertView = inflater.inflate(R.layout.file_row, null);
		
		TextView mText = (TextView)mConvertView.findViewById(R.id.text_file);
		mText.setText(mList.get(position).getName());
		
		ImageView mIcon = (ImageView) mConvertView.findViewById(R.id.icon);
		
		switch (mList.get(position).getType()) {
		case FileListNode.TYPE_FILE:
			mIcon.setImageResource(R.drawable.ic_list_nochoice);
			break;
		case FileListNode.TYPE_FOLDER:
			mIcon.setImageResource(R.drawable.ic_list_folder);
			break;
		case FileListNode.TYPE_PIC_FILE:
			mIcon.setImageResource(R.drawable.ic_list_picture);
			break;
		case FileListNode.TYPE_UNKNOWN:
			mIcon.setImageResource(R.drawable.ic_list_nochoice);
			break;
		}
		
		return mConvertView;
	}
	
}
