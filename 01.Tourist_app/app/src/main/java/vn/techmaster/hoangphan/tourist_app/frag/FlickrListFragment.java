package vn.techmaster.hoangphan.tourist_app.frag;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.R;
import vn.techmaster.hoangphan.tourist_app.act.MainActivity;

/**
 * Created by hoangpt on 12/19/14.
 */
public class FlickrListFragment extends ListFragment {

    private ArrayList<String> names;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity currentAct = (MainActivity) getActivity();
        names = currentAct.names;

        if(names != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                    currentAct,
                    android.R.layout.simple_list_item_1,
                    names
            );
            setListAdapter(adapter);
        }
    }
}
