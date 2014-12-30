package com.example.GuessWhat;

import android.app.Activity;
import com.animoto.android.views.DraggableGridView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.*;


public class GuessWhat extends Activity {
	Bitmap[] bmp=new Bitmap[9];
	DraggableGridView dgv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //setContentView(new Outline(this));
        
        setContentView(R.layout.main);
        
        int resId=getIntent().getIntExtra("BitmapImage", 0);
        
       
        //Drawable image=Drawable.createFromPath(bmpPath);
        
        //Bitmap scratch=(Bitmap) getIntent().getParcelableExtra("BitmapImage");
        Bitmap scratch=BitmapFactory.decodeResource(getResources(),resId);
        bmp=createBitmapPieces(scratch);
        //GridView gridview=(GridView) findViewById(R.id.gridview);
       // gridview.setAdapter(new ImageAdapter(this,bmp));
        dgv=((DraggableGridView) findViewById(R.id.dgv));
        loadImages(bmp);
        
       
    }
    
    private void loadImages(Bitmap[] Images){
    	List<Integer> list=new ArrayList<Integer>();
    	for (int i=0; i<=Images.length-1;i++){
    		list.add(i);
    	}
    	Collections.shuffle(list);	
    	for (int j=0;j<=Images.length-1;j++){
    		int index=list.get(j);
    		ImageView iv=new ImageView(getApplicationContext());
    		iv.setImageDrawable(new BitmapDrawable(Images[index]));
    		dgv.addView(iv);
    		
    	}
    	
    }
    /*class Outline extends View{
    	public Outline(Context context){
    		super(context);
    }
    	
    	@Override
    	public void onDraw(Canvas canvas){
    		Bitmap scratch=BitmapFactory.decodeResource(getResources(),R.drawable.kite);
    		createBitmapPieces(scratch);
    		canvas.drawColor(Color.YELLOW);
    		
    		//scratch.eraseColor(Color.BLACK);
    		
    		//canvas.drawBitmap(scratch,10,10,null);
    		
    		
    	}*/
    	
    	public Bitmap[] createBitmapPieces(Bitmap source){
    		
    		int k=0;
    		int width=source.getWidth();
    		int height=source.getHeight();
    		for(int i=0;i<3;i++){
    		   for(int j=0;j<3;j++){
    		    bmp[k]=Bitmap.createBitmap(source,(width*j)/3,(i*height)/3,width/3,height/3);
    		    k++;
    		}

    		}
    		return bmp;
    		} 
    	
    }
