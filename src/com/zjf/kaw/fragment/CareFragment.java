package com.zjf.kaw.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.kaw.R;
import com.zjf.kaw.activity.NewsDetailActivity;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.CareItem;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.view.ICareView;
import com.zjf.kaw.view.QQListView;

public class CareFragment extends Fragment implements ICareView {
	@ViewInject(R.id.lv_care)
	private QQListView lvCare;
	@ViewInject(R.id.tvEmptyCart)
	private TextView tvEmptyCart;
	@ViewInject(R.id.btnEdit)
	private Button btnEdit;
	private List<News> news;
	private List<ApplicationInfo> mAppList;
	private List<CareItem> items;
	private String link;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_care, null);
		// 初始化控件
		x.view().inject(this, view);
		items =MyApplication.getContext().getCare().getItems();
		// 设置监听
		setListeners();
		 link = getActivity().getIntent().getStringExtra("linkurl");
		
		lvCare.setAdapter(new MyAdapter());
		
		
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 设置适配器
		
	}

	

	// 设置监听
	private void setListeners() {
		lvCare.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(lvCare.canClick()){
					Intent intent = new Intent(getActivity(),
							NewsDetailActivity.class);
					String url = news.get(position).getUrl();
					intent.putExtra("linkurl", url);
					startActivity(intent);
				}
				
			}
		});
	

	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(null==convertView){
				convertView=View.inflate(getActivity(), R.layout.item_care_lv_careitem, null);
			}
			TextView tv1=(TextView) convertView.findViewById(R.id.tv);
			TextView delete=(TextView) convertView.findViewById(R.id.care_delete);
			TextView share=(TextView) convertView.findViewById(R.id.care_share);
			tv1.setTag(news.get(position));
			
			final int pos=position;
			delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					news.remove(pos);
					notifyDataSetChanged();
					lvCare.turnToNormal();
					
				}
			});
			share.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_TEXT, link);
					startActivity(Intent.createChooser(intent, "分享到："));
					
				}
			});
			
			return null;
		}
		
	}
	
}
