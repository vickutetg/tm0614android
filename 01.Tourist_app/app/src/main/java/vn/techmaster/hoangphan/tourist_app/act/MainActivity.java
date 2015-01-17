package vn.techmaster.hoangphan.tourist_app.act;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.R;
import vn.techmaster.hoangphan.tourist_app.frag.FlickrGridFragment;
import vn.techmaster.hoangphan.tourist_app.frag.FlickrListFragment;
import vn.techmaster.hoangphan.tourist_app.frag.WarningFragment;
import vn.techmaster.hoangphan.tourist_app.model.Flickphoto;
import vn.techmaster.hoangphan.tourist_app.service.FlickrService;

/**
 * Created by hoangpt on 12/19/14.
 */
public class MainActivity extends Activity {

    private static final String SERVICE_NAME = "Real";
    private static final Long FLICKR_OK = 1l;
    private static final Long FLICKR_FAIL = -1l;

    private android.app.ActionBar.Tab listTab;
    private android.app.ActionBar.Tab gridTab;
    private Tab picasoTab;

    public ProgressBar prgCircle;
    private FlickrListFragment flickrListFragment;

    public ArrayList<String> names;
    public ArrayList<Flickphoto> photos;

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

    }


    private void _showListPhoto() {
        Log.d("OK", "Vao List photo");

        flickrListFragment = new FlickrListFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, flickrListFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init ui
        _initUI();

        //check internet connect, if not->turn on network, if ok->asyncTask
        //kiểm tra Internet có ra ngoài được không
        if(!_checkInternet()){
            prgCircle.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Turn on internet", Toast.LENGTH_LONG).show();

            WarningFragment warningFragment = new WarningFragment();
            warningFragment.show(getFragmentManager(), "warning");
        } else {
            Log.d("Network", "OK");

            //ok ra ngoài, gọi service
            //init AsyncTask to load image
            new LoadFlickImageAsyncTask().execute();
        }
    }

    public boolean _checkInternet() {
        //connection manager, kiem tra
        ConnectivityManager connectService = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectService.getActiveNetworkInfo();

        //return false;
        return (networkInfo != null && networkInfo.isConnected());
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

    private class LoadFlickImageAsyncTask extends AsyncTask<Void, Long, Long>{

        @Override
        protected Long doInBackground(Void... params) {
            FlickrService service = FlickrService.initService(SERVICE_NAME);
            photos = service.getAllFlickrPhotos();
            names = service.getAllImageNames(photos);

            return FLICKR_OK;
        }

        @Override
        protected void onPostExecute(Long result) {
            if(result == FLICKR_FAIL){ //error
                Toast.makeText(getApplicationContext(),
                        "Something error", Toast.LENGTH_LONG).show();
            } else {
                //ok --> return 1l, not ok->0l
                _showListPhoto();
                prgCircle.setVisibility(View.INVISIBLE);
            }
        }
    }

}
