/**
 * http://iziroi.9xkun.com
 * 
 * LICENSE
 * 
 * This source file is belong to iziroi.9xkun.com. Please come to this site and
 * get more source code. Can send email to me at: phantichhoang@gmail.com
 * 
 * @copyright Copyright (c) 2013-2014 iziroi
 * @author hoangpt
 * @version $Id$
 * @since
 */

package com.hoangphan.tutor0301_wjpass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FollowerAdapter extends ArrayAdapter<String> {

  Context app;
  String[] list;
  
  public FollowerAdapter(Context context, String[] objects) {
    super(context, R.layout.follower_custom, objects);
    app = context;
    list = objects;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View row = inflater.inflate(R.layout.follower_custom, parent, false);
    
    TextView phone = (TextView) row.findViewById(R.id.os);
    ImageView logo = (ImageView) row.findViewById(R.id.logo);
    
    String namePhone = list[position];
    phone.setText(namePhone); //set textview
    if(namePhone.equals("Ton Ngo Khong")){ //set imageview
      logo.setImageResource(R.drawable.tnk);
    } else if(namePhone.equals("Tru Bat Gioi")){
      logo.setImageResource(R.drawable.tbg);
    } else {
      logo.setImageResource(R.drawable.st);
    }
    
    return row;
  }
  
  


  
}
