package com.zjf.kaw.activity;

/**
 *Created by zjf 2016-6-30����1:20:15
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
			// ��ʾ��һ��fragment
			newsFragment = new NewsFragment();
			FragmentManager manager = getSupportFragmentManager();
			// Tansaction����
			FragmentTransaction transaction = manager.beginTransaction();
			// ��NewsFragment��ʾ��linearLayout��
			transaction.add(R.id.fl_container1, newsFragment);
			transaction.show(newsFragment);
			// �ύ��ִ��add��show
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
						// �жϵ����ĸ�button
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
						//�ж�Ҫ��Ҫ��ʾ���fragment
						if(clickBtn!=currentFragment){
							Fragment fragment=fragmentList.get(clickBtn);
							FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
							//�ж�fragment�Ƿ���ӹ�
							if(!fragment.isAdded()){
								transaction.add(R.id.fl_container1, fragment);
							}
							//������ǰ��fragment
							transaction.hide(fragmentList.get(currentFragment));
							//��ʾ�µ�fragment
							transaction.show(fragment);
							transaction.commit();
							//currentFragmentֵ���ģ��������弰ͼ����ɫ
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
