package com.zjf.kaw.fragment;

import java.util.List;

import com.zjf.kaw.R;
import com.zjf.kaw.activity.NewsDetailActivity;
import com.zjf.kaw.adapter.NewsAdapter;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.NewsModel;
import com.zjf.kaw.model.NewsModel.Callback;
import com.zjf.kaw.util.GlobalConsts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by zjf 2016-7-12����4:06:08
 */
public class AutoNewsFragment extends Fragment {
	private NewsAdapter adapter;
	private ListView newsListView;
	private List<News> news;
	private ImageButton ivBack;
	NewsModel model;
	SwipeRefreshLayout fresh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.auto_fragment, null);
		setViews(view);
		model.findAllNewsList(new Callback() {

			@Override
			public void onDataLoaded(List<News> news) {
				setAdapter(news);
			}
		}, GlobalConsts.NEWS_BASE_Url,GlobalConsts.AUTO_PARAMETER);
		setLisenters();
		return view;
	}

	// ��ʼ��view
	private void setViews(View view) {
		newsListView = (ListView) view.findViewById(R.id.lv_autonews);
		ivBack = (ImageButton) view.findViewById(R.id.ib_newsinfo_back);
		Log.i("222222", newsListView.toString());
	}

	private void setLisenters() {
		// �������б���Ӽ���
		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				news = MyApplication.getContent().getNews();
				Intent intent = new Intent(getActivity(),
						NewsDetailActivity.class);
				String url = news.get(position).getUrl();
				intent.putExtra("linkurl", url);
				startActivity(intent);
				// TODO Auto-generated method stub

			}
		});
		// ��listview������Ӽ���
		newsListView.setOnScrollListener(new OnScrollListener() {
			private boolean isBottom;
			private String channelId;

			// ������״̬�ı�ʱִ��
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case SCROLL_STATE_FLING:
					break;
				case SCROLL_STATE_IDLE:
					if (isBottom) {
						
						// �����ˣ�������һҳ
						Log.i("wwww", "��ľ�е���");
//						model.findAllNewsList(new Callback() {
//							// ����������ӵ������ݼ����У�����adapter
//
//							@Override
//							public void onDataLoaded(List<News> news) {
//								// TODO Auto-generated method stub
//								if (news.isEmpty()) {// û������
//									Log.i("iiii", "���ؼ���");
//									Toast.makeText(getActivity(), "�Ѿ�����ȫ������",
//											Toast.LENGTH_SHORT).show();
//									return;
//								}
//								news.addAll(news);
//								adapter.notifyDataSetChanged();
//							}
//						}, GlobalConsts.NEWS_BASE_Url,10);
//						Toast.makeText(getActivity(), "�Ѿ�����ȫ������",
//								Toast.LENGTH_SHORT).show();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
					break;
				}

			}
			//������ʱִ�и÷���
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem+visibleItemCount==totalItemCount){
					isBottom=true;
					Log.i("4444", "������");
				}else{
					isBottom=false;
				}
			}
		});

	}

	/**
	 * ��listview����������
	 */
	public void setAdapter(List<News> news) {
		adapter = new NewsAdapter(getActivity(), news);
		MyApplication.getContent().setNews(news);
		newsListView.setAdapter(adapter);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		model = new NewsModel();

	}

}
