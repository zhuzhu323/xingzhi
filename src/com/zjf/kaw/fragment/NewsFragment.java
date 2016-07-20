package com.zjf.kaw.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjf.kaw.R;
import com.zjf.kaw.ui.PagerTab;
import com.zjf.kaw.util.UIUtils;

public class NewsFragment extends Fragment  {
	private PagerTab mPagerTab;
	private ViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, null);
		
		
		mPagerTab = (PagerTab)view.findViewById(R.id.pager_tab);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_pager);

		mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
		mPagerTab.setViewPager(mViewPager);

		mPagerTab.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BaseFragment fragment = FragmentFactory
						.createFragment(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		return view;
	}
	
	
	class MyPagerAdapter extends FragmentPagerAdapter {

		private String[] mTabNames;// 页签名称集合

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			mTabNames = UIUtils.getStringArray(R.array.tab_names);
			Log.i("212",mTabNames.toString());
		}

		// 加载每个页签标题
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNames[position];
		}

		// 返回Fragment对象,将onCreateView方法的返回view填充给ViewPager
		// 此方法类似instantiateItem
		@Override
		public Fragment getItem(int position) {
			// 从工厂类中生产Fragment并返回
			return FragmentFactory.createFragment(position);
		}

		// 返回item个数
		@Override
		public int getCount() {
			return mTabNames.length;
		}
	}
}
