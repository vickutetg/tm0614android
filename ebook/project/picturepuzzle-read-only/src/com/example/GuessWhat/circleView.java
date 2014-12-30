package com.example.GuessWhat;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

 
public class circleView extends RelativeLayout  {
         static final int centerId = 111;
         private final int radius;
 
         private RelativeLayout.LayoutParams createNewRelativeLayoutParams() {
                   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                      RelativeLayout.LayoutParams.WRAP_CONTENT,
                                      RelativeLayout.LayoutParams.WRAP_CONTENT);
                   lp.addRule(RelativeLayout.ABOVE, centerId);
                   lp.addRule(RIGHT_OF, centerId);
                   return lp;
         }
 
         private View prepareElementForCircle(View elem, int distX, int distY) {
                   RelativeLayout.LayoutParams lp = createNewRelativeLayoutParams();
 
                   elem.measure(0, 0);
                   int deltaX = elem.getMeasuredWidth() / 2;
                   int deltaY = elem.getMeasuredHeight() / 2;
                   lp.setMargins(distX - deltaX, 0, 0, radius - distY - deltaY);
                   elem.setLayoutParams(lp);
                   return elem;
         }
 
         public circleView(Context context, int radius,  View[] elements) {
                   super(context);
                   this.radius = radius;
 
                   RelativeLayout.LayoutParams lpView = new RelativeLayout.LayoutParams(
                                      RelativeLayout.LayoutParams.FILL_PARENT,
                                      RelativeLayout.LayoutParams.FILL_PARENT);
                   this.setLayoutParams(lpView);
 
                   View center = new View(context);
                   center.setId(centerId);
                   RelativeLayout.LayoutParams lpcenter = new RelativeLayout.LayoutParams(
                                      0, 0);
                   lpcenter.addRule(CENTER_HORIZONTAL);
                   lpcenter.addRule(CENTER_VERTICAL);
                   center.setLayoutParams(lpcenter);
                   this.addView(center);
                   
                   TextView tv= new TextView(context);

                   RelativeLayout.LayoutParams lptextview=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

                   lptextview.addRule(CENTER_HORIZONTAL);

                   lptextview.addRule(CENTER_VERTICAL);

                   tv.setText("Select a Category");

                   tv.setLayoutParams(lptextview);

                   this.addView(tv);


 
                   this.addView(prepareElementForCircle(elements[0], 0, 0));
                   if (elements.length % 2 == 0) {
                            this.addView(prepareElementForCircle(elements[elements.length / 2],
                                               0, 2 * radius));
                   }
                   if (elements.length > 2) {
                            for (int i = 1; i <= (elements.length - 1) / 2; i++) {
                                      int y = i * 4 * radius / elements.length;
                                      int x = (int) Math.sqrt(Math.pow(radius, 2)
                                                        - Math.pow((radius - y), 2));
                                      this.addView(prepareElementForCircle(elements[i], x, y));
                                      this.addView(prepareElementForCircle(elements[elements.length
                                                        - i], -x, y));
                            }
                   }
                   
                  /* for (int i=0;i< elements.length;i++){
                	   
                	   elements[i].setOnTouchListener(new OnTouchListener(){
                		  
						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							if (arg1.getAction() == MotionEvent.ACTION_HOVER_ENTER){
								
								int newWidth=arg0.getWidth() * 2;
								int newHeight=arg0.getHeight() * 2;
								RelativeLayout.LayoutParams layout=new RelativeLayout.LayoutParams(newWidth, newHeight);
							
								arg0.setLayoutParams(layout);
							
							}
							return true;
						}
                	   });
                   
                   }*/
         }

		
 
}