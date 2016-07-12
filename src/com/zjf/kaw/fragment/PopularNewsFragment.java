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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *Created by zjf 2016-7-12下午1:54:52
 */
public class PopularNewsFragment extends Fragment {
	private NewsAdapter adapter;
	private ListView newsListView;
	private List<News> news;
	NewsModel model;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

	View view = inflater.inflate(R.layout.sport_fragment, null);
	setViews(view);
	model.findAllNewsList(new Callback() {
		
		@Override
		public void onDataLoaded(List<News> news) {
		setAdapter(news);
		}
	}, GlobalConsts.NEWS_POPULAR_Url );
	setLisenters();
	return view;
}
//初始化view
	private void setViews(View view){
		newsListView=(ListView) view.findViewById(R.id.lv_news);
	}
	private void setLisenters() {
		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				news=MyApplication.getContent().getNews();
				Intent intent=new Intent(getActivity(),NewsDetailActivity.class);
				String url = news.get(position).getUrl();
				intent.putExtra("linkurl",url);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
	}
/**
* 给listview设置适配器
*/
	public void setAdapter(List<News> news){
		adapter=new NewsAdapter(getActivity(),news);
		MyApplication.getContent().setNews(news);
		newsListView.setAdapter(adapter);
		
	}
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	model = new NewsModel();
	
}


}
