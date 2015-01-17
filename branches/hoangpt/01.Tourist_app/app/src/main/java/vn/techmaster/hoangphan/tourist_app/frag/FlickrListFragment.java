package vn.techmaster.hoangphan.tourist_app.frag;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.R;
import vn.techmaster.hoangphan.tourist_app.act.MainActivity;
import vn.techmaster.hoangphan.tourist_app.model.Flickphoto;

/**
 * Created by hoangpt on 12/19/14.
 */
public class FlickrListFragment extends ListFragment {

    private ArrayList<String> names;
    private ArrayList<Flickphoto> photos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity currentAct = (MainActivity) getActivity();
        names = currentAct.names;
        photos = currentAct.photos;

        if(names != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                    currentAct,
                    android.R.layout.simple_list_item_1,
                    names
            );
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //lấy url
        Flickphoto photo = photos.get(position);
        String photoUrl = photo.getPhotoUrl(true);

        //lấy ra framgent và set argument cho nó
        //dê truyền data giữa các fragment, cũng dùng bundle (argument)
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("URL", photoUrl);
        args.putString("name", photo.title+"hjk4wltfjhsdi74tgifdjkslgyerio");
        imageFragment.setArguments(args);

        //thay bằng fragment view (có thể làm bằng dialog)
        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, imageFragment);
        ft.addToBackStack("Image");
        ft.commit();
        Toast.makeText(this.getActivity().getApplicationContext(),
                photo.title, Toast.LENGTH_SHORT).show();
    }
}
