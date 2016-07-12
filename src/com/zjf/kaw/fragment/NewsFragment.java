package com.zjf.kaw.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zjf.kaw.R;
import com.zjf.kaw.adapter.NewsAdapter;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.NewsModel;

public class NewsFragment extends Fragment {

	// private ListView listView;
	NewsModel model;
	private RadioGroup radioGroupNews;
	private NewsAdapter adapter;
	private List<News> news;
	private ViewPager viewPager;
	private ArrayList<Fragment> fragments;
	private RadioButton rbTitle1;
	private RadioButton rbTitle2;
	private RadioButton rbTitle3;
	private RadioButton rbTitle4;
	private PopularNewsFragment popularFragment;
	private SpotsNewsFragment spotFragment;
	private TechNewsFragment techFragment;
	private AutoNewsFragment autoFragment;
	// private MPagerAdapter1 mPagerAdapter;
	ArrayList<Fragment> fragmentList = new ArrayList();
	ArrayList<RadioButton> btnList = new ArrayList();
	// 当前显示第一个Fragment
	int currentFragment = 0;
	int clickBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, null);
		//显示第一个fragment
		popularFragment=new PopularNewsFragment();
		FragmentManager manager=getActivity().getSupportFragmentManager();
		//Transaction事务
		FragmentTransaction transaction=manager.beginTransaction();
		//将popularfragment显示在linearlayout中
		transaction.add(R.id.ll_news_fragment_contatiner, popularFragment);
		//提交、执行add、show
		transaction.commit();
		
		SpotsNewsFragment spotsFragment=new SpotsNewsFragment();
		TechNewsFragment techFragment=new TechNewsFragment();
		AutoNewsFragment autoFragment=new AutoNewsFragment();
		fragmentList.add(spotFragment);
		fragmentList.add(techFragment);
		fragmentList.add(autoFragment);
		
		rbTitle1 = (RadioButton) view.findViewById(R.id.rb_new_title_first);
		rbTitle2 = (RadioButton) view.findViewById(R.id.rb_new_title_second);
		rbTitle3 = (RadioButton) view.findViewById(R.id.rb_new_title_third);
		rbTitle4 = (RadioButton) view.findViewById(R.id.rb_new_title_fouth);
		btnList.add(rbTitle1);
		btnList.add(rbTitle2);
		btnList.add(rbTitle3);
		btnList.add(rbTitle4);
		btnList.get(currentFragment).setSelected(true);
		for(RadioButton btn:btnList){
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					switch (v.getId()) {
					case R.id.rb_new_title_first:
						clickBtn=0;
						break;
					case R.id.rb_new_title_second:
						clickBtn=1;
						break;
					case R.id.rb_new_title_third:
						clickBtn=2;
						break;
					case R.id.rb_new_title_fouth:
						clickBtn=03;
						break;
					}
					//判断要不要显示别的Fragment
					if(clickBtn!=currentFragment){
						Fragment fragment=fragmentList.get(clickBtn);
						FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
						//判断fragment是否添加过
						if(!fragment.isAdded()){
							transaction.add(R.id.ll_news_fragment_contatiner, fragment);
						}
						//隐藏以前的fragment
						transaction.hide(fragmentList.get(currentFragment));
						//显示新的fragment
						transaction.show(fragment);
						transaction.commit();
						
					}
					
				}
			});
		}
		return view;
		
	}

//	// 初始化view
//	private void setViews(View view) {
//		radioGroupNews = (RadioGroup) view.findViewById(R.id.rg_news_title);
//
//		popularFragment = new PopularNewsFragment();
//		addFragment(popularFragment);
//		showFragment(popularFragment);
//
//	}
//
//	// 添加fragment
//	private void addFragment(Fragment fragment) {
//		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
//		ft.add(R.id.ll_news_fragment_contatiner, fragment);
//		ft.commitAllowingStateLoss();
//	}
//
//	// 显示Fragment
//	private void showFragment(Fragment fragment) {
//		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
//		if (popularFragment != null) {
//			ft.hide(popularFragment);
//		}
//		if (spotFragment != null) {
//			ft.hide(spotFragment);
//
//		}
//		if (techFragment != null) {
//			ft.hide(techFragment);
//
//		}
//		if (autoFragment != null) {
//			ft.hide(autoFragment);
//		}
//		ft.show(fragment);
//		ft.commitAllowingStateLoss();
//	}
//
//	private void setListeners() {
//		radioGroupNews
//				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//					public void onCheckedChanged(RadioGroup group, int checkedId) {
//						switch (checkedId) {
//						case R.id.rb_new_title_first:
//							if (popularFragment == null) {
//								popularFragment = new PopularNewsFragment();
//								addFragment(popularFragment);
//								showFragment(popularFragment);
//							} else {
//								showFragment(popularFragment);
//							}
//							break;
//						case R.id.rb_new_title_second:
//							if (spotFragment == null) {
//								spotFragment = new SpotsNewsFragment();
//								addFragment(spotFragment);
//								showFragment(spotFragment);
//							} else {
//								showFragment(spotFragment);
//							}
//							break;
//						case R.id.rb_new_title_third:
//							if (techFragment == null) {
//								techFragment = new TechNewsFragment();
//								addFragment(techFragment);
//								showFragment(techFragment);
//							} else {
//								showFragment(techFragment);
//							}
//							break;
//						case R.id.rb_new_title_fouth:
//							if (autoFragment == null) {
//								autoFragment = new AutoNewsFragment();
//								addFragment(autoFragment);
//								showFragment(autoFragment);
//							} else {
//								showFragment(autoFragment);
//							}
//							break;
//						default:
//							break;
//
//						}
//					}
//				});
//	}
}
