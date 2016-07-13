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
 * Created by zjf 2016-7-5����7:36:05
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
		// Ϊ������ť��Ӽ���
		// Ϊ���ذ�����Ӽ���
		ivBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				finish();

			}
		});
		// Ϊ����ť��Ӽ���
		ivShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, link);
				startActivity(Intent.createChooser(intent, "������"));

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
		// webview����web��Դ
		webView.loadUrl(link);
		// ����webviewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��webview��
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// ����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
				view.loadUrl(url);
				return true;
			}
		});

	}
}
