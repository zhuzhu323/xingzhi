package com.zjf.kaw.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.zjf.kaw.R;
import com.zjf.kaw.activity.NewsDetailActivity;
import com.zjf.kaw.adapter.NewsAdapter;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.NewsModel;
import com.zjf.kaw.model.NewsModel.Callback;
import com.zjf.kaw.util.GlobalConsts;

/**
 * Created by zjf 2016-7-12下午1:54:52
 */
public class PopularNewsFragment extends Fragment {
	private NewsAdapter adapter;
	private ListView newsListView;
	private List<News> news;
	private ImageButton ivBack;
	NewsModel model;
	SwipeRefreshLayout fresh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.popular_fragment, null);
		setViews(view);
		model.findAllNewsList(new Callback() {

			@Override
			public void onDataLoaded(List<News> news) {
				PopularNewsFragment.this.news=news;
				setAdapter(news);
			}
		}, GlobalConsts.NEWS_BASE_Url,GlobalConsts.POPULAR_PARAMETER);
		setLisenters();
		return view;
	}

	// 初始化view
	private void setViews(View view) {
		newsListView = (ListView) view.findViewById(R.id.lv_news);
		ivBack = (ImageButton) view.findViewById(R.id.ib_newsinfo_back);
		Log.i("222222", newsListView.toString());
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
				// TODO Auto-generated method stub

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
						
						// 到底了，加载下一页
						Log.i("wwww", "有木有到底");
//						model.findAllNewsList(new Callback() {
//							// 将新数据添加到旧数据集合中，更新adapter
//
//							@Override
//							public void onDataLoaded(List<News> news) {
//								// TODO Auto-generated method stub
//								if (news.isEmpty()) {// 没有数据
//									Log.i("iiii", "加载加载");
//									Toast.makeText(getActivity(), "已经加载全部新闻",
//											Toast.LENGTH_SHORT).show();
//									return;
//								}
//								news.addAll(news);
//								adapter.notifyDataSetChanged();
//							}
//						}, GlobalConsts.NEWS_BASE_Url,10);
//						Toast.makeText(getActivity(), "已经加载全部新闻",
//								Toast.LENGTH_SHORT).show();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
					break;
				}

			}
			//当滚动时执行该方法
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem+visibleItemCount==totalItemCount){
					isBottom=true;
					Log.i("4444", "到底了");
				}else{
					isBottom=false;
				}
			}
		});

	}

	/**
	 * 给listview设置适配器
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
