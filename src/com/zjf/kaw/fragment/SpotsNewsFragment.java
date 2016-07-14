package com.zjf.kaw.fragment;

import java.util.List;

import com.zjf.kaw.R;
import com.zjf.kaw.activity.NewsDetailActivity;
import com.zjf.kaw.adapter.NewsAdapter;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.INewsModel;
import com.zjf.kaw.model.IModel.AsyncCallBack;
import com.zjf.kaw.model.impl.NewsModelImpl;
import com.zjf.kaw.presenter.impl.NewsPresenterImpl;
import com.zjf.kaw.view.NewsView;

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
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;


/**
/ *Created by zjf 2016-7-12����4:03:32
 */
public class SpotsNewsFragment extends Fragment implements NewsView {
	private NewsAdapter adapter;
	private ListView newsListView;
	private List<News> news;
	private ImageButton ivBack;
	private INewsModel model;
	SwipeRefreshLayout fresh;
	private int page = 1;
	private NewsPresenterImpl newsImpl;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.sport_fragment, null);
		setViews(view);
		newsImpl = new NewsPresenterImpl(this, getActivity());
		model = new NewsModelImpl(getActivity());
		newsImpl.getSportsNews(page);
		setLisenters();
		return view;
	}

	@Override
	public void showPopularNewsList(List<News> news) {
		
	}

	// ��ʼ��view
	private void setViews(View view) {
		newsListView = (ListView) view.findViewById(R.id.lv_sportsnews);
		ivBack = (ImageButton) view.findViewById(R.id.ib_newsinfo_back);
	}

	private void setLisenters() {
		// �������б���Ӽ���
		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						NewsDetailActivity.class);
				String url = news.get(position).getUrl();
				intent.putExtra("linkurl", url);
				startActivity(intent);
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
						page += 1;
						if (page <= 10) {
							Toast.makeText(getActivity(), "�������ظ���",
									Toast.LENGTH_SHORT).show();
							model.findPopularNewsList(new AsyncCallBack() {

								@Override
								public void onSuccess(List<News> newslist) {
									if (news != null) {
										news.addAll(newslist);
										showPopularNewsList(news);
									}
								}

								@Override
								public void onFailed() {

								}
							}, page);
						}
						if (page == 10) {
							Toast.makeText(getActivity(), "�Ѿ�����ȫ������",
									Toast.LENGTH_SHORT).show();
						}

					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
					break;
				}

			}

			// ������ʱִ�и÷���
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount == totalItemCount) {
					isBottom = true;
				} else {
					isBottom = false;
				}
			}
		});

	}

	@Override
	public void showSportsNewsList(List<News> news) {
		this.news = news;
		if (adapter == null) {
			adapter = new NewsAdapter(getActivity(), news);
			newsListView.setAdapter(adapter);
			Log.i("111111111111", newsListView.toString());
		}
		adapter.notifyDataSetChanged();

	}

	@Override
	public void showTechNewsList(List<News> news) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showAutoNewsList(List<News> news) {
		// TODO Auto-generated method stub

	}
}
