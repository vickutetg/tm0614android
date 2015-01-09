package vn.techmaster.hoangphan.tourist_app.service;

import java.util.ArrayList;

/**
 * Created by hoangpt on 1/9/15.
 */

public class StubFlickrService extends FlickrService {
    @Override
    public ArrayList<String> getAllImageName() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> names = new ArrayList<>();
        names.add("anh_so_1.jpg");
        names.add("anh_so_2.jpg");
        names.add("anh_so_3.jpg");

        return names;
    }
}
