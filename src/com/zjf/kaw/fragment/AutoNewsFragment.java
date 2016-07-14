package com.zjf.kaw.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.zjf.kaw.R;
import com.zjf.kaw.activity.NewsDetailActivity;
import com.zjf.kaw.adapter.NewsAdapter;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.IModel.AsyncCallBack;
import com.zjf.kaw.model.INewsModel;
import com.zjf.kaw.model.impl.NewsModelImpl;
import com.zjf.kaw.presenter.impl.NewsPresenterImpl;
import com.zjf.kaw.view.NewsView;

/**
 * Created by zjf 2016-7-12下午4:06:08
 */
public class AutoNewsFragment extends Fragment implements NewsView {
	private NewsAdapter adapter;
	private ListView newsListView;
	private List<News> news;
	private ImageButton ivBack;
	private INewsModel model;
	SwipeRefreshLayout fresh;
	private int page = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.auto_fragment, null);
		setViews(view);
		NewsPresenterImpl newsImpl = new NewsPresenterImpl(this, getActivity());
		model = new NewsModelImpl(getActivity());
		newsImpl.getAutoNews(page);
		setLisenters();
		return view;
	}

	@Override
	public void showPopularNewsList(List<News> news) {

	}

	// 初始化view
	private void setViews(View view) {
		newsListView = (ListView) view.findViewById(R.id.lv_autonews);
		ivBack = (ImageButton) view.findViewById(R.id.ib_newsinfo_back);
	}

	private void setLisenters() {
		// 给新闻列表添加监听
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
		// 给listview滚动添加监听
		newsListView.setOnScrollListener(new OnScrollListener() {
			private boolean isBottom;
			private String channelId;

			// 当滚动状态改变时执行
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case SCROLL_STATE_FLING:
					break;
				case SCROLL_STATE_IDLE:
					if (isBottom) {
						page += 1;
						if (page <= 10) {
							Toast.makeText(getActivity(), "下拉加载更多",
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
							Toast.makeText(getActivity(), "已经加载全部新闻",
									Toast.LENGTH_SHORT).show();
						}

					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
					break;
				}

			}

			// 当滚动时执行该方法
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

	}

	@Override
	public void showTechNewsList(List<News> news) {
	}

	@Override
	public void showAutoNewsList(List<News> news) {
		this.news = news;
		if (adapter == null) {
			adapter = new NewsAdapter(getActivity(), news);
			newsListView.setAdapter(adapter);
		}
		adapter.notifyDataSetChanged();
	}
}
