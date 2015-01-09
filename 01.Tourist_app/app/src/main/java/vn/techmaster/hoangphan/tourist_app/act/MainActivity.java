package vn.techmaster.hoangphan.tourist_app.act;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.R;
import vn.techmaster.hoangphan.tourist_app.frag.FlickrGridFragment;
import vn.techmaster.hoangphan.tourist_app.frag.FlickrListFragment;
import vn.techmaster.hoangphan.tourist_app.service.FlickrService;

/**
 * Created by hoangpt on 12/19/14.
 */
public class MainActivity extends Activity {

    private android.app.ActionBar.Tab listTab;
    private android.app.ActionBar.Tab gridTab;
    private Tab picasoTab;

    private ProgressBar prgCircle;
    private FlickrListFragment flickrListFragment;

    public ArrayList<String> names;

    boolean isListLoad = false;
    boolean isGridLoad = false;


    /**
     * inner class
     */
    private class NavListener implements ActionBar.TabListener {
        @Override
        public void onTabSelected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
            if (tab.equals(listTab)) {
                _showListPhoto();
            } else if(tab.equals(gridTab)) {
                _showGridPhoto();
            } else {
                _showPicasoPhoto();
            }
        }

        @Override
        public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    private void _showPicasoPhoto() {

    }

    private void _showGridPhoto() {
        Log.d("OK", "Grid photo");

        FlickrGridFragment flickrGridFragment = new FlickrGridFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, flickrGridFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        if(!isGridLoad){
            prgCircle.setVisibility(View.VISIBLE);
            AsyncTask<Void, Long, Void> task = new LoadFlickImageAsyncTask().execute();
        }
    }


    private void _showListPhoto() {
        Log.d("OK", "Vao List photo");

        flickrListFragment = new FlickrListFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, flickrListFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        if(!isListLoad){
            prgCircle.setVisibility(View.VISIBLE);
            AsyncTask<Void, Long, Void> task = new LoadFlickImageAsyncTask().execute();
        }

        //if ok->display list

        //not ok->display Toast (tao khong lay duoc anh)
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init ui
        _initUI();

        //check internet connect, if not->turn on network, if ok->asyncTask


        //init AsyncTask to load image
    }

    private void _initUI() {

        prgCircle = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //add tab
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        listTab = actionBar.newTab().setText("List Photo").setTag("list_photo").setTabListener(new NavListener());
        gridTab = actionBar.newTab().setText("Grid Photo").setTag("grid_photo").setTabListener(new NavListener());
        picasoTab = actionBar.newTab().setText("Picaso").setTag("picaso_photo").setTabListener(new NavListener());

        actionBar.addTab(listTab);
        actionBar.addTab(gridTab);
        actionBar.addTab(picasoTab);

        return true;
    }

    private class LoadFlickImageAsyncTask extends AsyncTask<Void, Long, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            FlickrService service = FlickrService.initService("Stub");
            names = service.getAllImageName();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            _showListPhoto();
            prgCircle.setVisibility(View.INVISIBLE);
            isListLoad = true;
        }
    }

}
