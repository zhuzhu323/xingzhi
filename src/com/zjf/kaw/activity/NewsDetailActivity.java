package com.zjf.kaw.activity;

import java.io.File;
import java.util.List;

import com.zjf.kaw.R;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.fragment.PopularNewsFragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

/**
 * Created by zjf 2016-7-5下午7:36:05
 */
public class NewsDetailActivity extends Activity {
	private WebView webView;
	private ImageButton ivBack;
	private ImageButton ivShare;
	private List<News> news;
	private String link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsinfo_activity);
		 link = getIntent().getStringExtra("linkurl");
		init(link);
		setViews();
		setListeners();

	}

	private void setListeners() {
		// 为顶部按钮添加监听
		// 为返回按键添加监听
		ivBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				finish();

			}
		});
		// 为分享按钮添加监听
		ivShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, link);
				startActivity(Intent.createChooser(intent, "分享到："));

			}

		});

	}
	private void setViews() {
		// TODO Auto-generated method stub
		ivBack = (ImageButton) findViewById(R.id.ib_newsinfo_back);
		ivShare = (ImageButton) findViewById(R.id.ib_newsinfo_more);

	}

	private void init(String link) {
		// TODO Auto-generated method stub
		webView = (WebView) findViewById(R.id.wv_newsinfo_content);
		// webview加载web资源
		webView.loadUrl(link);
		// 覆盖webview默认使用第三方或系统默认浏览器打开网页的行为，使网页用webview打开
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});

	}
}
