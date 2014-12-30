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

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DogActivity extends Activity {

  private static final int NORMAL_DLG = 0;
  private static final int WAITING_DLG = 1;
  private static final int DOWNLOADING_DLG = 2;
  private ImageView dog;
  private TextView dogNr;
  
  //private Handler pgrHandler;
  private ProgressDialog pgrDialog;
  private int progressNr = 5;
  private int i = 0;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dog);
    
//    pgrHandler = new Handler() {
//
//      @Override
//      public void handleMessage(Message msg) {
//        super.handleMessage(msg);
//        if(progressNr >= 100){
//          pgrDialog.dismiss();
//        } else {
//          pgrDialog.incrementProgressBy(progressNr++);
//          pgrHandler.sendEmptyMessageDelayed(0, 1000);
//        }
//      }
//    };
  }
  
  @Override
  protected void onResume() {
    super.onResume();
  }

  @SuppressLint("SdCardPath")
  private void _timerMethod() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
            int j = i%8;
            dogNr.setText("Dog image at "+j);
            String pathImg = "/sdcard/Download/cover/"+j+".jpg";
            dog.setImageBitmap(BitmapFactory.decodeFile(pathImg));
            i++;
        }
    });      
  }
  
  @SuppressLint("SdCardPath")
  public void displayImage(View v){
    dog = (ImageView) findViewById(R.id.dogImg);
    dog.setImageBitmap(BitmapFactory.decodeFile("/sdcard/Download/dog/T0.gif"));
    dogNr = (TextView) findViewById(R.id.dogNr);
    
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {

        @Override
        public void run() {
            _timerMethod();
        }
    }, 0,100);
  }
  
  @SuppressWarnings("deprecation")
  public void displayNormal(View v){
    showDialog(NORMAL_DLG);
  }
  
  @SuppressWarnings("deprecation")
  public void displayWaiting(View v){
    showDialog(WAITING_DLG);
  }
  
  public void displayDownloading(View v){
    //showDialog(DOWNLOADING_DLG);
  }
  
  
  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
      case NORMAL_DLG:
        return _showNormalDialog();
      case WAITING_DLG:
        return _showWaitingDialog();
      case DOWNLOADING_DLG:
        return _showDownloadDialog();
      default:
        break;
    }
    
    return null;
  }

  private Dialog _showDownloadDialog() {
    pgrDialog = new ProgressDialog(this);
    pgrDialog.setTitle("We are trying to download");
    pgrDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    pgrDialog.setProgress(progressNr);
    
/*    new Thread(new Runnable() {
      
      @Override
      public void run() {
        for (int i = 0; i <= 15; i++) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          pgrDialog.incrementProgressBy(100/15);
        }
        pgrDialog.dismiss();
        
      }
    }).start();*/
    
    //pgrHandler.sendEmptyMessage(0);
    return pgrDialog;
  }

  private Dialog _showWaitingDialog() {
    final ProgressDialog pgrDialog = ProgressDialog.show(this, "Please wait", "We are trying to retrive data", true);
    new Thread(new Runnable() {
      
      @Override
      public void run() {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        pgrDialog.dismiss();
        
      }
    }).start();
    
    return pgrDialog;
  }

  private Dialog _showNormalDialog() {
    String[] presidents = {"Vo Nguyen Giap", "Ho chi minh"};
    return new AlertDialog.Builder(this)
      .setTitle("Normal Dialog")
      .setMultiChoiceItems(presidents, new boolean[presidents.length], new DialogInterface.OnMultiChoiceClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        }
      })
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
      })
      .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
      })
      .create();    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.dog, menu);
    return true;
  }

}
