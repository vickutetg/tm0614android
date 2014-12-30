package ducky.edu.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Weather extends Activity implements OnClickListener {

    private ImageView ivCurrent;
    private EditText etLocation;
    private Button bLocation;
    private TextView tvDate;
    private TextView tvTemperature;
    private TextView tvHumidity;
    private TextView tvCondition;

    private ImageView ivDay1;
    private ImageView ivDay2;
    private ImageView ivDay3;
    private ImageView ivDay4;

    private final static String GOOGLE = "http://www.google.com";
    private final static String GOOGLE_WEATHER = "http://www.google.com/ig/api?weather=";

    private ArrayList<String> iconList;
    private ArrayList<String> dayList;
    private ArrayList<String> conditionList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        initialize();
    }

    private void initialize() {
        ivCurrent = (ImageView) findViewById(R.id.ivCurrent);
        etLocation = (EditText) findViewById(R.id.etLocation);
        bLocation = (Button) findViewById(R.id.bLocation);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTemperature = (TextView) findViewById(R.id.tvTemperature);
        tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        ivDay1 = (ImageView) findViewById(R.id.ivDay1);
        ivDay2 = (ImageView) findViewById(R.id.ivDay2);
        ivDay3 = (ImageView) findViewById(R.id.ivDay3);
        ivDay4 = (ImageView) findViewById(R.id.ivDay4);

        bLocation.setOnClickListener(this);
        ivDay1.setOnClickListener(this);
        ivDay2.setOnClickListener(this);
        ivDay3.setOnClickListener(this);
        ivDay4.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.about:
            // Lien quan about
            Intent about = new Intent("ducky.edu.weather.ABOUT");
            startActivity(about);
            break;
        case R.id.setting:
            // Lien quan den setup
            Intent setting = new Intent("ducky.edu.weather.SETTING");
            startActivity(setting);
            break;
        }
        return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bLocation:
            String location = etLocation.getText().toString();
            loadData(location);
            break;
        case R.id.ivDay1:
            clickImage(ivDay1, ivCurrent, 1);
            break;
        case R.id.ivDay2:
            clickImage(ivDay2, ivCurrent, 2);
            break;
        case R.id.ivDay3:
            clickImage(ivDay3, ivCurrent, 3);
            break;
        case R.id.ivDay4:
            clickImage(ivDay4, ivCurrent, 4);
            break;

        }
    }

    private void clickImage(ImageView ivDay, ImageView ivCurrent, int index) {
        ivCurrent.setImageDrawable(ivDay.getDrawable());
        tvCondition.setText(conditionList.get(index - 1));
        tvHumidity.setText(dayList.get(index - 1));
        tvDate.setText("");
        tvTemperature.setText("");
    }

    private void loadData(String location) {
        try {
            URL url = new URL(GOOGLE_WEATHER + location);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader reader = sp.getXMLReader();

            WeatherHandler handler = new WeatherHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(url.openStream()));

            // Load du lieu tu weatherhander
            loadIcon(handler.getIcon(), ivCurrent);
            tvDate.setText(handler.getDate());
            tvTemperature.setText(handler.getTempC());
            tvHumidity.setText(handler.getHumidity());
            tvCondition.setText(handler.getCondition());
            iconList = handler.getIconList();
            dayList = handler.getDayList();
            conditionList = handler.getConditionList();
            ImageView[] imageViews = { ivDay1, ivDay2, ivDay3, ivDay4 };
            for (int i = 0; i < imageViews.length; i++) {
                loadIcon(iconList.get(i), imageViews[i]);
            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Retype the location",
                    Toast.LENGTH_LONG).show();
            etLocation.requestFocus();
        }
    }

    private void loadIcon(String url, ImageView iv)
            throws MalformedURLException, IOException {
        InputStream is = (InputStream) new URL(GOOGLE + url).getContent();
        if (is == null) {
            return;
        }
        Drawable drawable = Drawable.createFromStream(is, "Weather icon");
        iv.setImageDrawable(drawable);
    }

}
