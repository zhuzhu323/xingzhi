package com.zjf.kaw.activity;

/**
 *Created by zjf 2016-6-30����1:20:15
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
			// ��ʾ��һ��fragment
			newsFragment = new NewsFragment();
			FragmentManager manager = getSupportFragmentManager();
			// Tansaction����
			FragmentTransaction transaction = manager.beginTransaction();
			// ��NewsFragment��ʾ��linearLayout��
			transaction.add(R.id.vp_pagers, newsFragment);
			transaction.show(newsFragment);
			// �ύ��ִ��add��show
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// NewsModel model=new NewsModel();
		// model.findAllNews(MainFragmentActivity.this);
		/**
		 * �ؼ���ʼ��
		 */
		setViews();
		/**
		 * ��viewpager����������
		 */
		setAdapter();
		/**
		 * ���ü���
		 */
		setListeners();
	}

	/**
	 * ��viewpager����������
	 */
	private void setAdapter() {
		fragments = new ArrayList<Fragment>();
		// ��fragments���������Fragment����
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
	 * �ؼ���ʼ��
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
// ����viewpager ���Ƶײ�������
//		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
//			public void onPageSelected(int position) {
//				switch (position) {
//				case 0:
//					Log.i("info", "�����˵�1ҳ..");
//					rbFirstPage.setChecked(true);
//					break;
//				case 1:
//					Log.i("info", "�����˵�2ҳ..");
//					rbVideo.setChecked(true);
//					break;
//				case 2:
//					Log.i("info", "�����˵�3ҳ..");
//					rbCare.setChecked(true);
//					break;
//				case 3:
//					Log.i("info", "�����˵�4ҳ..");
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

		// ������� ����viewpager
		radioGroupMenu
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.rb_tab_menu_first:
							Log.i("info", "ѡ����firstpage");
							viewPager.setCurrentItem(0);
							break;
						case R.id.rb_tab_menu_video:
							Log.i("info", "ѡ����video");
							viewPager.setCurrentItem(1);
							break;
						case R.id.rb_tab_menu_care:
							Log.i("info", "ѡ����care");
							viewPager.setCurrentItem(2);
							break;
						case R.id.rb_tab_menu_mine:
							Log.i("info", "ѡ����mine..");
							viewPager.setCurrentItem(3);
							break;
						}
					}
				});
	}
}
