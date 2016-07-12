package com.zjf.kaw.activity;

/**
 *Created by zjf 2016-6-30下午1:20:15
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zjf.kaw.R;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.fragment.CareFragment;
import com.zjf.kaw.fragment.MineFragment;
import com.zjf.kaw.fragment.NewsFragment;
import com.zjf.kaw.fragment.VideoFragment;

public class MainFragmentActivity extends FragmentActivity {
	private RadioButton rbFirstPage;
	private RadioButton rbMine;
	private RadioButton rbCare;
	private RadioButton rbVideo;
	private RadioGroup radioGroupMenu;
	private MyPagerAdapter pagerAdapter;
	private ListView newsListView;
	private List<News> news;

	private ViewPager viewPager;
	private ArrayList<Fragment> fragments;
	private NewsFragment newsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			this.setContentView(R.layout.fragment_main);
			// 显示第一个fragment
			newsFragment = new NewsFragment();
			FragmentManager manager = getSupportFragmentManager();
			// Tansaction事务
			FragmentTransaction transaction = manager.beginTransaction();
			// 把NewsFragment显示在linearLayout中
			transaction.add(R.id.vp_pagers, newsFragment);
			transaction.show(newsFragment);
			// 提交，执行add，show
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// NewsModel model=new NewsModel();
		// model.findAllNews(MainFragmentActivity.this);
		/**
		 * 控件初始化
		 */
		setViews();
		/**
		 * 给viewpager设置适配器
		 */
		setAdapter();
		/**
		 * 设置监听
		 */
		setListeners();
	}

	/**
	 * 给viewpager设置适配器
	 */
	private void setAdapter() {
		fragments = new ArrayList<Fragment>();
		// 向fragments集合中添加Fragment对象
		fragments.add(new NewsFragment());
		fragments.add(new VideoFragment());
		fragments.add(new CareFragment());
		fragments.add(new MineFragment());

		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(4);
		Log.i("111", pagerAdapter.toString());

	}

	class MyPagerAdapter extends FragmentPagerAdapter {
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

	}

	/**
	 * 控件初始化
	 */
	private void setViews() {
		viewPager = (ViewPager) findViewById(R.id.vp_pagers);
		radioGroupMenu = (RadioGroup) findViewById(R.id.rg_tab_menu);
		rbCare = (RadioButton) findViewById(R.id.rb_tab_menu_care);
		rbMine = (RadioButton) findViewById(R.id.rb_tab_menu_mine);
		rbFirstPage = (RadioButton) findViewById(R.id.rb_tab_menu_first);
		rbVideo = (RadioButton) findViewById(R.id.rb_tab_menu_video);
		newsListView=(ListView) findViewById(R.id.lv_news);
	}

	private void setListeners() {
// 滑动viewpager 控制底部导航栏
//		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
//			public void onPageSelected(int position) {
//				switch (position) {
//				case 0:
//					Log.i("info", "滚到了第1页..");
//					rbFirstPage.setChecked(true);
//					break;
//				case 1:
//					Log.i("info", "滚到了第2页..");
//					rbVideo.setChecked(true);
//					break;
//				case 2:
//					Log.i("info", "滚到了第3页..");
//					rbCare.setChecked(true);
//					break;
//				case 3:
//					Log.i("info", "滚到了第4页..");
//					rbMine.setChecked(true);
//					break;
//				}
//			}
//
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//			}
//
//			public void onPageScrollStateChanged(int arg0) {
//			}
//		});

		// 点击导航 控制viewpager
		radioGroupMenu
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.rb_tab_menu_first:
							Log.i("info", "选择了firstpage");
							viewPager.setCurrentItem(0);
							break;
						case R.id.rb_tab_menu_video:
							Log.i("info", "选择了video");
							viewPager.setCurrentItem(1);
							break;
						case R.id.rb_tab_menu_care:
							Log.i("info", "选择了care");
							viewPager.setCurrentItem(2);
							break;
						case R.id.rb_tab_menu_mine:
							Log.i("info", "选择了mine..");
							viewPager.setCurrentItem(3);
							break;
						}
					}
				});
	}
}
