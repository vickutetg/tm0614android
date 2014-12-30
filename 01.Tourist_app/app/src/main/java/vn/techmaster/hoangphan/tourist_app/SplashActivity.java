package vn.techmaster.hoangphan.tourist_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import vn.techmaster.hoangphan.tourist_app.act.MainActivity;


public class SplashActivity extends Activity {

    private Animation.AnimationListener animeListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //start Main
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);

            //finish Splash
            SplashActivity.this.finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //animation
        _startAnimation();
    }

    private void _startAnimation() {
        //load animation config from xml
        Animation fadein = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein);

        //apply to view
        TextView topTitle = (TextView) findViewById(R.id.TextViewTopTitle);
        topTitle.setAnimation(fadein);

        Animation fadein2 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein2);
        TextView bottomTitle = (TextView) findViewById(R.id.TextViewBottomTitle);
        bottomTitle.setAnimation(fadein2);

        //custom anime
        Animation customeAmine = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.custom_anim);
        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

        LayoutAnimationController controller = new LayoutAnimationController(SplashActivity.this, null);
        controller.setAnimation(customeAmine);

        int size = table.getChildCount();
        for(int i=0; i<size; i++){
            TableRow row = (TableRow) table.getChildAt(i);
            if (row != null) {
                row.setLayoutAnimation(controller);
            }
        }

        fadein2.setAnimationListener(animeListener);
    }


}
