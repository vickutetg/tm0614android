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
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class WorkAdapter extends ArrayAdapter<Work> {
  Context app;
  ArrayList<Work> list;

  public WorkAdapter(Context context, int resource, List<Work> objects) {
    super(context, resource, objects);
    app = context;
    list = (ArrayList<Work>) objects;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View workView = convertView;
    if(null == workView) {
      workView = new WorkView(app);
    }
    
    Work work = list.get(position);
    if(null != work) {
      WorkView row = (WorkView) workView;
      row.workName.setText(work.getName());
      row.workTime.setText(work.getHour() + ":" + work.getMinute());
      row.checkBox.setChecked(work.isDone());
    }
    
    return workView;
  }
}
