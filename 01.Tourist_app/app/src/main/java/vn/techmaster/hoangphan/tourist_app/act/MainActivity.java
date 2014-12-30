package vn.techmaster.hoangphan.tourist_app.act;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import vn.techmaster.hoangphan.tourist_app.R;
import vn.techmaster.hoangphan.tourist_app.frag.FlickrGridFragment;
import vn.techmaster.hoangphan.tourist_app.frag.FlickrListFragment;

/**
 * Created by hoangpt on 12/19/14.
 */
public class MainActivity extends Activity {

    private android.app.ActionBar.Tab listTab;
    private android.app.ActionBar.Tab gridTab;



    private class NavListener implements ActionBar.TabListener {
        @Override
        public void onTabSelected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
            if (tab.equals(listTab)) {
                _showListPhoto();
            } else {
                _showGridPhoto();
            }
        }

        @Override
        public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    private void _showGridPhoto() {
        Log.d("OK", "Grid photo");

        FlickrGridFragment flickrGridFragment = new FlickrGridFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, flickrGridFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    private void _showListPhoto() {
        Log.d("OK", "Vao day chua");

        FlickrListFragment flickrListFragment = new FlickrListFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, flickrListFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //add tab
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        listTab = actionBar.newTab().setText("List Photo").setTabListener(new NavListener());
        gridTab = actionBar.newTab().setText("Grid Photo").setTabListener(new NavListener());

        actionBar.addTab(listTab);
        actionBar.addTab(gridTab);

        return true;
    }


}
