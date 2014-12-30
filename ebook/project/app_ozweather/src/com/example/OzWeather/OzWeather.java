package com.example.OzWeather;         
         
import android.app.Activity;         
import android.os.Bundle; 
import android.webkit.WebView;  
import android.webkit.WebViewClient;         
         
public class OzWeather extends Activity         
{  
    WebView mWebView; 
    /** Called when the activity is first created. */         
    @Override         
    public void onCreate(Bundle savedInstanceState)         
    {         
        super.onCreate(savedInstanceState);         
        setContentView(R.layout.main);   
        mWebView = (WebView) findViewById(R.id.webview); 
        mWebView.getSettings().setJavaScriptEnabled(true);         
        mWebView.loadUrl("http://htmlpress.net/weather/");               
	mWebView.setWebViewClient(new HelloWebViewClient());
 
        //will continue to improve this app to cover major cities  
    }  
 
    private class HelloWebViewClient extends WebViewClient { 
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) { 
            view.loadUrl(url); 
            return true; 
        } 
    }   
  
}           