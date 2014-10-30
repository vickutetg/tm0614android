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

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class FourActivity extends ListActivity {

    private Intent i1;

    static final String[] list = {"Ton Ngo Khong", "Tru Bat Gioi", "Sa Tang"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setListAdapter(new FollowerAdapter(this, list));
      
      Intent i4 = getIntent();
      i1 = new Intent(this, FirstActivity.class);
      i1.putExtras(i4);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      //get selected items
      String follower = (String) getListAdapter().getItem(position);
      i1.putExtra("followers", follower);
      startActivity(i1);
    } 
}
