package vn.techmaster.hoangphan.tourist_app.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hoangpt on 1/9/15.
 */
public class NetworkUtils {

    public static String parse(String uriStr){
        //exp: uriStr = https://api.flickr.com/services/rest/?method=flickr.people.getPhotos&api_key=de5916cc22be95f2482cb8df43f08316
        // &user_id=97808811%40N03&format=json&nojsoncallback=1&per_page=5
        //tạo một connection tới FLickr Restful
        String lines, readedLines = "";
        try {
            URL url = new URL(uriStr);
            HttpURLConnection cnn  =  (HttpURLConnection) url.openConnection();
            cnn.setRequestMethod("GET");
            cnn.setAllowUserInteraction(true);

            cnn.connect();

            int status = cnn.getResponseCode();
            InputStream is = null;
            if (200 == status) {
                //read from input stream -->json class
                is = cnn.getInputStream();
            }

            //đọc streaming về local (String json)
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while((lines = reader.readLine()) != null){
                readedLines += lines;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readedLines;
    }

    public static InputStream connectServer(String uriStr) {
        InputStream is = null;

        try {
            URL url = new URL(uriStr);
            HttpURLConnection cnn  =  (HttpURLConnection) url.openConnection();
            cnn.setRequestMethod("GET");
            cnn.setAllowUserInteraction(true);

            cnn.connect();

            int status = cnn.getResponseCode();

            if (200 == status) {
                //read from input stream -->json class
                is = cnn.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
}
