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

package com.hoangphan.tutor0202_mxflower;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FlowerActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //show toast help, collection
    String[] helps = new String[]{
        "I am Mai Xuan.",
        "I am sexy, kute and an flower girl.",
        "If you want to buy flower, enter flower name and press Buy button."+
            "Wish you happy."
    };
    for (int i = 0; i < helps.length; i++) {
      Toast toast = Toast.makeText(getBaseContext(), helps[i], 1);
      toast.show();
    }
    
    //get ui from viewlayer
    final EditText flowerEdit = (EditText) findViewById(R.id.flowerEdit);
    final Button buyBtn = (Button) findViewById(R.id.buyBtn);
    final TextView orderTxt = (TextView) findViewById(R.id.orderTxt);
    
    //config events
    buyBtn.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
       String flower = flowerEdit.getText().toString();
       flowerEdit.setText("");
       
       //add to order
       String before = orderTxt.getText().toString();
       String after = before + "\n" + flower;
       orderTxt.setText(after);
       
       //flash messenger
       Toast.makeText(getBaseContext(), flower+" add successfully", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
