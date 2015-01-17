package vn.techmaster.hoangphan.tourist_app.service;

import android.util.Log;

import org.apache.http.HttpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.techmaster.hoangphan.tourist_app.model.Flickphoto;
import vn.techmaster.hoangphan.tourist_app.utils.NetworkUtils;

/**
 * Created by hoangpt on 1/9/15.
 */

public class RealFlickrService extends FlickrService {


    @Override
    public ArrayList<String> getAllImageNames(ArrayList<Flickphoto> list) {
        //connect internet ->call request api get photos
        ArrayList<String> names = new ArrayList<String>();

        for (Flickphoto flickphoto: list){
            names.add(flickphoto.title);
        }

        return names;
    }

    public ArrayList<Flickphoto> getAllFlickrPhotos(){
        //parser
        String textJson = NetworkUtils.parse(flickrUrl);
        Log.d("jsonStr", textJson);
        ArrayList<Flickphoto> list = new ArrayList<>();

        try {
            JSONObject textJsonObj = new JSONObject(textJson);
            JSONObject photosJsonObj = textJsonObj.optJSONObject("photos");
            JSONArray arrJSON = photosJsonObj.optJSONArray("photo");

            //iteration
            for (int i = 0; i < arrJSON.length(); ++i) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                Flickphoto flickrPhoto = new Flickphoto();
                flickrPhoto.fromJSON(objJSON);
                list.add(flickrPhoto);

                //cache cac file anh vao trong storage (tmp)
                //đọc đường dẫn file ảnh (vừa viết xong)
                //lấy ảnh về và save vào thư mục caches
                //(/data/data/vn.techmaster.hoangphan/caches) -dùng network lấy về
                //-save Writer để write vào



                //cache vào db
                //nếu id của ảnh đã được lấy về từ lần trước -> bỏ qua
                //nếu chưa có => insert vào db và cache ảnh về
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
