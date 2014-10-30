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

package com.hoangphan.tutor0302_doggy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class WebviewActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.dog, menu);
    return true;
  }

}
