package vn.techmaster.hoangphan.tourist_app.service;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.model.Flickphoto;

/**
 * Created by hoangpt on 1/9/15.
 */

public class StubFlickrService extends FlickrService {
    @Override
    public ArrayList<String> getAllImageNames() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> names = new ArrayList<>();
        names.add("anh_so_1.jpg");
        names.add("anh_so_2.jpg");
        names.add("anh_so_3.jpg");
        names.add("anh_so_4.jpg");
        names.add("anh_so_5.jpg");

        return names;
    }

    @Override
    public ArrayList<Flickphoto> getAllFlickrPhotos() {
        return null;
    }
}
