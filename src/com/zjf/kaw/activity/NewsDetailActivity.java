package com.zjf.kaw.activity;

import com.zjf.kaw.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *Created by zjf 2016-7-5下午7:36:05
 */
public class NewsDetailActivity extends Activity{
	private WebView webView;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.newsinfo_activity);
	String link = getIntent().getStringExtra("linkurl");
	init(link);
	
}

private void init(String link) {
	// TODO Auto-generated method stub
	webView=(WebView) findViewById(R.id.wv_newsinfo_content);
	//webview加载web资源
	webView.loadUrl(link);
	//覆盖webview默认使用第三方或系统默认浏览器打开网页的行为，使网页用webview打开
	 webView.setWebViewClient(new WebViewClient(){
         @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
          // TODO Auto-generated method stub
             //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
           view.loadUrl(url);
          return true;
      }
     });
		
	
}
}
