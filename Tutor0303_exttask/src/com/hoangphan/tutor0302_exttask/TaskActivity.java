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

package com.hoangphan.tutor0302_exttask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TaskActivity extends Activity {

  private EditText txtWork;
  private EditText txtHour;
  private EditText txtMinute;
  private ListView list;
  private ArrayList<Work> listWorks;
  private ArrayAdapter<Work> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    txtWork = (EditText) findViewById(R.id.txtWork);
    txtHour = (EditText) findViewById(R.id.txtHour);
    txtMinute = (EditText) findViewById(R.id.txtMinute);
    
    //build list view
    list = (ListView) findViewById(R.id.list);
    listWorks = new ArrayList<Work>();
    
    //need adapter for input data and layout
    adapter = new WorkAdapter(this, R.layout.work_custom, listWorks);
    list.setAdapter(adapter);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position,
          long id) {
        WorkView wv = (WorkView) view;
        wv.checkBox.setChecked(true);
      }
    });
  }

  @SuppressLint("NewApi")
  public void onAddWork(View v){
    //validate
    if(txtWork.getText().toString().equals("")){
      AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
      builder.setTitle("Error info provided");
      builder.setMessage("Not blank for either work name, hour or minute.");
      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          System.out.println("OK click");
        }
      });
      builder.show();
    } else {
      Work work = new Work();
      work.setName(txtWork.getText().toString());
      work.setHour(Integer.parseInt(txtHour.getText().toString()));
      work.setMinute(Integer.parseInt(txtMinute.getText().toString()));
      listWorks.add(work);
      Collections.sort(listWorks, new Comparator<Work>() {

        @Override
        public int compare(Work thisWork, Work thatWork) {
          int thisTime = thisWork.getHour()*60 + thisWork.getMinute();
          int thatTime = thatWork.getHour()*60 + thatWork.getMinute();
          return Integer.compare(thisTime, thatTime);
        }
      });
      Log.d("add", work.toString());
      adapter.notifyDataSetChanged();
      
      //reset form
      txtWork.setText("");
      txtHour.setText("");
      txtMinute.setText("");
    }
  }

}
