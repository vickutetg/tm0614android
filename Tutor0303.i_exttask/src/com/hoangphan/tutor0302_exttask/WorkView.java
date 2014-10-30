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

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkView extends LinearLayout {
  public CheckBox checkBox;
  public TextView workName;
  public TextView workTime;
  
  public WorkView(Context context) {
    super(context);
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.work_custom, this, true);
    
    checkBox = (CheckBox) findViewById(R.id.chk);
    workName = (TextView) findViewById(R.id.workName); 
    workTime = (TextView) findViewById(R.id.workTime); 
  }
}
