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

		private String[] mTabNames;// ҳǩ���Ƽ���

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			mTabNames = UIUtils.getStringArray(R.array.tab_names);
			Log.i("212",mTabNames.toString());
		}

		// ����ÿ��ҳǩ����
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNames[position];
		}

		// ����Fragment����,��onCreateView�����ķ���view����ViewPager
		// �˷�������instantiateItem
		@Override
		public Fragment getItem(int position) {
			// �ӹ�����������Fragment������
			return FragmentFactory.createFragment(position);
		}

		// ����item����
		@Override
		public int getCount() {
			return mTabNames.length;
		}
	}
}
