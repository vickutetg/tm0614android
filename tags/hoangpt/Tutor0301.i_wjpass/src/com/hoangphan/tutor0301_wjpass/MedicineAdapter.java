package com.hoangphan.tutor0301_wjpass;

import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MedicineAdapter extends ArrayAdapter<String> {

  String[] medicines = {"Med1", "Med2"};
  int[] medImage = {R.drawable.med1, R.drawable.med2};
  
  public MedicineAdapter(Context context) {
    super(context, R.layout.follower_custom);
    addAll(Arrays.asList(medicines));
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Activity app = (Activity) getContext();
    LayoutInflater inflater = app.getLayoutInflater();
    View row = inflater.inflate(R.layout.follower_custom, parent, false);
    
    TextView phone = (TextView) row.findViewById(R.id.os);
    ImageView logo = (ImageView) row.findViewById(R.id.logo);
    
    phone.setText(medicines[position]);
    logo.setImageResource(medImage[position]);
    
    return row;
  }
}
