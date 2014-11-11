package com.example.androidtablayout;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        // Tab in
        TabSpec internal = tabHost.newTabSpec("Internal");
        internal.setIndicator("Internal", getResources().getDrawable(R.drawable.internal_icon));
        Intent photosIntent = new Intent(this, Internal.class);
        internal.setContent(photosIntent);
        
        // Tab ex
        TabSpec external = tabHost.newTabSpec("External");
        external.setIndicator("External", getResources().getDrawable(R.drawable.external_icon));
        Intent songsIntent = new Intent(this, External.class);
        external.setContent(songsIntent);
        
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(internal); // Adding photos tab
        tabHost.addTab(external); // Adding songs tab
    }
}