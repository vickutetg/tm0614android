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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        //can get data here
        Intent i = getIntent();
        String name = i.getStringExtra("helloMsg");
        int dateInt = i.getIntExtra("dateInt", 0);
        
        _toaster(name);
        _toaster("Today is "+dateInt);
        
        Bundle b = getIntent().getExtras();
        String helpMsg = b.getString("helpMsg");
        int monthInt = b.getInt("monthInt");
        String[] stepArr = b.getStringArray("stepArr");
        
        _toaster(helpMsg);
        _toaster("This month is "+monthInt);
        for (String step : stepArr) {
          _toaster(step);
        }
    }

    private void _toaster(String msg) {
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void gotoFirst(View v){
      Intent i = new Intent();
      i.setData(Uri.parse("Why are you go back"));
      i.putExtra("monthInt", 12);
      
      TextView txtName = (TextView) findViewById(R.id.txtName);
      TextView txtAge = (TextView) findViewById(R.id.txtAge);
      
      i.setData(Uri.parse(txtName.getText().toString())); //we can parse data as uri
      i.putExtra("age", txtAge.getText().toString()); //or as normal
      
      setResult(RESULT_OK, i);
      finish();
    }
    
    public void gotoThird(View v){
      Intent i = new Intent(this, ThirdActivity.class);
      
      TextView txtName = (TextView) findViewById(R.id.txtName);
      TextView txtAge = (TextView) findViewById(R.id.txtAge);
      RadioButton radMale = (RadioButton) findViewById(R.id.radMale);
      RadioButton radFemale = (RadioButton) findViewById(R.id.radFemale);
      
      Bundle b = new Bundle();
      b.putString("name", txtName.getText().toString());
      b.putString("age", txtAge.getText().toString());
      
      String sex = "";
      if(radMale.isChecked()){
        sex = "Male";
      } else if(radFemale.isChecked()){
        sex = "Female";
      } else {
        sex = "Gay/Lesbian/Homo/Bisex";
      }
      
      b.putString("sex", sex);
      i.putExtras(b);
      
      //we can still call startActivityForResult but we do not listen callback
      startActivityForResult(i, Constant.REQUEST_FROM2_TO3);
    }
}
