package com.zjf.kaw.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zjf.kaw.R;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.INewsModel;
import com.zjf.kaw.view.NewsView;

public class NewsFragment extends Fragment {

	// private ListView listView;
	INewsModel model;
	@ViewInject(R.id.rg_news_title)
	private RadioGroup radioGroupNews;
	@ViewInject(R.id.vp_news_pagers)
	private ViewPager viewPager;
	private ArrayList<Fragment> fragments1;
	@ViewInject(R.id.rb_new_title_first)
	private RadioButton rbTitle1;
	@ViewInject(R.id.rb_new_title_second)
	private RadioButton rbTitle2;
	@ViewInject(R.id.rb_new_title_third)
	private RadioButton rbTitle3;
	@ViewInject(R.id.rb_new_title_fouth)
	private RadioButton rbTitle4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_news, null);
		intitData();
		x.view().inject(this, view);
		viewPager.setOffscreenPageLimit(4);
		// 设置adapter
		setAdapter();
		setListeners();
		return view;
	}

	private void setAdapter() {
		PagerAdapter adapter = new FragmentPagerAdapter(getActivity()
				.getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragments1.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return fragments1.get(arg0);
			}
		};
		viewPager.setAdapter(adapter);
	}

	// 初始化数据
	private void intitData() {

		fragments1 = new ArrayList<Fragment>();
		fragments1.add(new PopularNewsFragment());
		fragments1.add(new SpotsNewsFragment());
		fragments1.add(new TechNewsFragment());
		fragments1.add(new AutoNewsFragment());
	}

	private void setListeners() {
		radioGroupNews
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.rb_new_title_first:
							viewPager.setCurrentItem(0);
							break;
						case R.id.rb_new_title_second:
							viewPager.setCurrentItem(1);
							break;
						case R.id.rb_new_title_third:
							viewPager.setCurrentItem(2);
							break;
						case R.id.rb_new_title_fouth:
							viewPager.setCurrentItem(3);
							break;

						}
					}
				});
		/**
		 * vp监听器
		 */
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i2) {
				// if (v != 0) { // 当前是第3页
				// // 设置第三个fragment header的透明度
				// MineFragment fragment = (MineFragment) fragments1.get(3);
				// fragment.slide(v);
				// }
			}

			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rbTitle1.setChecked(true);
					break;
				case 1:
					rbTitle2.setChecked(true);
					break;
				case 2:
					rbTitle3.setChecked(true);
					break;
				case 3:
					rbTitle4.setChecked(true);
					break;

				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
