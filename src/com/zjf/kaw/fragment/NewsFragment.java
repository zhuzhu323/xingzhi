package com.zjf.kaw.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.zjf.kaw.R;
import com.zjf.kaw.adapter.NewsAdapter;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.NewsModel;

public class NewsFragment extends Fragment {

	// private ListView listView;
	NewsModel model;
	private RadioGroup radioGroupNews;
	private NewsAdapter adapter;
	private ListView newsListView;
	private List<News> news;
	private ViewPager viewPager;
	private ArrayList<Fragment> fragments;
	private RadioButton rbTitle1;
	private RadioButton rbTitle2;
	private RadioButton rbTitle3;
	private RadioButton rbTitle4;
	private MPagerAdapter1 mPagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, null);
		setViews(view);
		setListeners();
		return view;
	}

	// 初始化view
	private void setViews(View view) {
		rbTitle1 = (RadioButton) view.findViewById(R.id.rb_new_title_first);
		rbTitle2 = (RadioButton) view.findViewById(R.id.rb_new_title_second);
		rbTitle3 = (RadioButton) view.findViewById(R.id.rb_new_title_third);
		rbTitle4 = (RadioButton) view.findViewById(R.id.rb_new_title_fouth);
		viewPager = (ViewPager) view.findViewById(R.id.vp_pagers1);
		radioGroupNews = (RadioGroup) view.findViewById(R.id.rg_news_title);

	}

	private void setListeners() {
		// 滑动viewpager 控制底部导航栏
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					Log.i("info", "滚到了热点新闻..");
					rbTitle1.setChecked(true);
					break;
				case 1:
					Log.i("info", "滚到了体育新闻");
					rbTitle2.setChecked(true);
					break;
				case 2:
					Log.i("info", "滚到了科技新闻");
					rbTitle3.setChecked(true);
					break;
				case 3:
					Log.i("info", "滚到了汽车新闻");
					rbTitle4.setChecked(true);
					break;
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int arg0) {
			}
		});

		// 点击导航 控制viewpager
		radioGroupNews
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.rb_new_title_first:
							Log.i("info", "选择了1");
							viewPager.setCurrentItem(0);
							break;
						case R.id.rb_new_title_second:
							Log.i("info", "选择了2");
							viewPager.setCurrentItem(1);
							break;
						case R.id.rb_new_title_third:
							Log.i("info", "选择了3");
							viewPager.setCurrentItem(2);
							break;
						case R.id.rb_new_title_fouth:
							Log.i("info", "选择了4..");
							viewPager.setCurrentItem(3);
							break;
						}
					}
				});
	}

	public void setAdapter() {
		fragments = new ArrayList<Fragment>();
		// 向fragments集合中添加fragment对象
		fragments.add(new PopularNewsFragment());
		fragments.add(new SpotsNewsFragment());
		fragments.add(new TechNewsFragment());
		fragments.add(new AutoNewsFragment());

		mPagerAdapter = new MPagerAdapter1(getChildFragmentManager());
		viewPager.setAdapter(mPagerAdapter);
		viewPager.setCurrentItem(0);
		viewPager.setOffscreenPageLimit(4);
		Log.i("aaa", mPagerAdapter.toString());
	}

	public class MPagerAdapter1 extends FragmentPagerAdapter {

		public MPagerAdapter1(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}

	}
}
