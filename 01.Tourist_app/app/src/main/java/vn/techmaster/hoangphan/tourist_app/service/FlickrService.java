package vn.techmaster.hoangphan.tourist_app.service;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.model.Flickphoto;

/**
 * Created by hoangpt on 1/9/15.
 */
public abstract class FlickrService {


    public static String API_KEY = "de5916cc22be95f2482cb8df43f08316";
    public static int NUMBER = 30;

    protected String flickrUrl = "https://api.flickr.com/services/rest/?method=flickr.people.getPhotos&api_key="+API_KEY+
            "&user_id=97808811%40N03&format=json&nojsoncallback=1&per_page="+NUMBER;



    public abstract ArrayList<String> getAllImageNames(ArrayList<Flickphoto> photos);

    public abstract ArrayList<Flickphoto> getAllFlickrPhotos();

    public static FlickrService initService(String name){
        if (name.equals("Stub")){
            return new StubFlickrService();
        } else {
            return new RealFlickrService();
        }
    }
}
