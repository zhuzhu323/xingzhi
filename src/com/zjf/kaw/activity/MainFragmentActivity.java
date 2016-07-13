package com.zjf.kaw.activity;

/**
 *Created by zjf 2016-6-30下午1:20:15
 */

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zjf.kaw.R;
import com.zjf.kaw.fragment.CareFragment;
import com.zjf.kaw.fragment.MineFragment;
import com.zjf.kaw.fragment.NewsFragment;
import com.zjf.kaw.fragment.VideoFragment;

public class MainFragmentActivity extends FragmentActivity {
	NewsFragment newsFragment;
	ArrayList<Fragment> fragmentList = new ArrayList();
	ArrayList<Button> btnList = new ArrayList();
	int currentFragment = 0;
	int clickBtn;

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
			transaction.add(R.id.fl_container1, newsFragment);
			transaction.show(newsFragment);
			// 提交，执行add，show
			transaction.commit();

			CareFragment careFragment = new CareFragment();
			VideoFragment videoFragment = new VideoFragment();
			MineFragment mineFragment = new MineFragment();
			fragmentList.add(newsFragment);
			fragmentList.add(videoFragment);
			fragmentList.add(careFragment);
			fragmentList.add(mineFragment);

			Button newsBtn = (Button) findViewById(R.id.rb_tab_menu_first);
			Button videoBtn = (Button) findViewById(R.id.rb_tab_menu_video);
			Button careBtn = (Button) findViewById(R.id.rb_tab_menu_care);
			Button mineBtn = (Button) findViewById(R.id.rb_tab_menu_mine);

			btnList.add(newsBtn);
			btnList.add(videoBtn);
			btnList.add(careBtn);
			btnList.add(mineBtn);
			btnList.get(currentFragment).setSelected(true);
			for (Button btn : btnList) {
				btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 判断单机哪个button
						switch (v.getId()) {
						case R.id.rb_tab_menu_first:
							clickBtn = 0;
							break;
						case R.id.rb_tab_menu_video:
							clickBtn = 1;
							break;
						case R.id.rb_tab_menu_care:
							clickBtn = 2;
							break;
						case R.id.rb_tab_menu_mine:
							clickBtn = 3;
							break;
					
						}
						//判断要不要显示别的fragment
						if(clickBtn!=currentFragment){
							Fragment fragment=fragmentList.get(clickBtn);
							FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
							//判断fragment是否添加过
							if(!fragment.isAdded()){
								transaction.add(R.id.fl_container1, fragment);
							}
							//隐藏以前的fragment
							transaction.hide(fragmentList.get(currentFragment));
							//显示新的fragment
							transaction.show(fragment);
							transaction.commit();
							//currentFragment值更改，更改字体及图标颜色
							btnList.get(currentFragment).setSelected(false);
							btnList.get(clickBtn).setSelected(true);
							currentFragment=clickBtn;
						}

					}
				});
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
