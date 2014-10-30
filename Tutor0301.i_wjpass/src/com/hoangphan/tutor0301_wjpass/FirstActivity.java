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

import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
  }

  public void gotoTest(View v){
    Intent i = new Intent("com.hoangphan.tutor0301_hqinfo.SecondActivity");
    
    //pass data to other activity
    i.putExtra("helloMsg", "Welcome to app info.");
    i.putExtra("dateInt", 26);
    
    startActivity(i);
  }
  
  public void gotoFirst(View v){
    Intent i = new Intent(this, SecondActivity.class);
    
    //pass data using bundle
    Bundle b = new Bundle();
    b.putString("helpMsg", "I am helper and want to guide each step");
    b.putInt("monthInt", 12);
    b.putStringArray("stepArr", new String[] {"Click Info", "Click Place", "Click MobileOS"});
    i.putExtras(b);
    
    startActivityForResult(i, Constant.REQUEST_FROM1_TO2);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //check also button back, not cancel button
    if(requestCode == Constant.REQUEST_FROM1_TO2){
      if(resultCode == RESULT_OK){ //click goFirstButton
        String name = data.getData().toString();
        int monthInt = data.getIntExtra("monthInt", 0);
        String age = data.getStringExtra("age");
        _toaster(name);
        _toaster("This month is: "+monthInt);
        
        TextView txtInfo = (TextView) findViewById(R.id.infoTxt);
        txtInfo.append("\nYour name: "+name);
        txtInfo.append("\nYour age: "+age);
      } else { //press cancel button
        _toaster("Why click back");
      } 
    }
  }
  
  private void _toaster(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    Intent data = getIntent();
    TextView txtInfo = (TextView) findViewById(R.id.infoTxt);
    
    //not check anything, request_code or result_code
    Bundle b = data.getExtras();
    if(null != b){
      Set<String> keys = b.keySet();
      for (String key : keys) {
        String value = b.getString(key);
        if(null != value){
          txtInfo.append("\nYour "+key+": "+value);
        }
      }
    }
  }  
}
