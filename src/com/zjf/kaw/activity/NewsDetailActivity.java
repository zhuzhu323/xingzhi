package com.zjf.kaw.activity;

import com.zjf.kaw.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *Created by zjf 2016-7-5����7:36:05
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
	//webview����web��Դ
	webView.loadUrl(link);
	//����webviewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��webview��
	 webView.setWebViewClient(new WebViewClient(){
         @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
          // TODO Auto-generated method stub
             //����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
           view.loadUrl(url);
          return true;
      }
     });
		
	
}
}
