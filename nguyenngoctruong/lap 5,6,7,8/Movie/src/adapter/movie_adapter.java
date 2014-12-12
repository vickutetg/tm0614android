package adapter;

import java.util.ArrayList;

import model.Movie;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.NguyenTruong.movie.R;

public class movie_adapter extends BaseAdapter{
	
	private Context mContext;
	public ArrayList<Movie> mlist = new ArrayList<Movie>();
	
	public movie_adapter(Context mContext, ArrayList<Movie> mlist){
		super();
		this.mContext = mContext;
		this.mlist = mlist;
	}
	
	@Override
	public int getCount() {
		int rtValue = 0;
		if (mlist != null) {
			rtValue = mlist.size();
		}
		return rtValue;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		LayoutInflater mInflater = LayoutInflater.from(mContext);
		convertView = mInflater.inflate(R.layout.girdview_layout, parent, false);
		
		holder = new ViewHolder();
		holder.poster = (ImageView) convertView.findViewById(R.id.imgPoster);
		holder.name = (TextView) convertView.findViewById(R.id.txtMovie);
		
		convertView.setTag(holder);
		
		Movie mv = mlist.get(position);
		holder.poster.setBackgroundResource(mv.getPoster());
		holder.name.setText(mv.getName());
		
		
		return convertView;
	}
	
	public class ViewHolder {
		ImageView poster;
		TextView name;
	}
	

}
