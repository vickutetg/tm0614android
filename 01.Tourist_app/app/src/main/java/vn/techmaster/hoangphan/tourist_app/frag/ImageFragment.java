package vn.techmaster.hoangphan.tourist_app.frag;

import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import vn.techmaster.hoangphan.tourist_app.R;
import vn.techmaster.hoangphan.tourist_app.act.MainActivity;
import vn.techmaster.hoangphan.tourist_app.utils.NetworkUtils;

/**
 * Created by hoangpt on 1/16/15.
 */
public class ImageFragment extends Fragment {

    ImageView imageView;
    private ProgressBar progressBar;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity parent = (MainActivity) getActivity();
        progressBar = parent.prgCircle;
        progressBar.setVisibility(View.VISIBLE);

        //check argument
        Bundle b = this.getArguments();
        String url = null;
        String name = null;
        if (b != null) {
            url = b.getString("URL");
            name = b.getString("name");
        }

        //if has url-->so load using async task
        if (url != null) {
            LoadImageTask li = new LoadImageTask(imageView, url, name);
            li.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageView  = (ImageView) inflater.inflate(R.layout.frag_image, container, false);
        return imageView;
    }

    private class LoadImageTask extends AsyncTask<Void, Void, Long>{

        ImageView imageView;
        String imageUrl, name;
        Bitmap bitmap;

        /**
         * constructor
         *
         * @param imageView
         * @param imageUrl
         */
        public LoadImageTask(ImageView imageView, String imageUrl, String name) {
            this.imageView = imageView;
            this.imageUrl = imageUrl;
            this.name = name;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (result==0){
                progressBar.setVisibility(View.GONE);
                imageView.setImageBitmap(bitmap);
            }
        }


        @Override
        protected Long doInBackground(Void... params) {
            //String imageFileName = imageUrl.replace(":", "");
            //imageFileName = imageFileName.replace("/", "");
            //imageFileName = imageFileName.replace(".", "");
            String imageFileName = this.name;
            File imageFile = new File(getActivity().getCacheDir(), imageFileName);

            //nếu tồn tại trong cache dir thì đọc ra
            if (imageFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                return 0l;
            }

            //kiem tra co bi offline khong
            MainActivity parent = (MainActivity) getActivity();
            if(!parent._checkInternet()){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyz);
                return 0l;
            } else {
                OutputStream imageOS;
                try {
                    //đọc binary ảnh từ server
                    imageFile.createNewFile();
                    InputStream is = NetworkUtils.connectServer(imageUrl);
                    bitmap = BitmapFactory.decodeStream(is);

                    //lưu vào ổ cứng làm cache
                    imageOS = new BufferedOutputStream(new FileOutputStream(imageFile));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOS);
                    imageOS.flush();
                    imageOS.close();
                    return (0l);
                } catch (Exception e) {
                    e.printStackTrace();
                    return (1l);
                }
            }
        }
    }


}
