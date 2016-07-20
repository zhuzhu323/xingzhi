//package com.zjf.kaw.adapter;
//
//import java.util.List;
//
//import com.zjf.kaw.R;
//import com.zjf.kaw.entity.CareItem;
//import com.zjf.kaw.presenter.ICarePresenter;
//
//import android.content.Context;
//import android.content.pm.ApplicationInfo;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class CareItemAdapter extends BaseAdapter{
//private Context context;
//private List<CareItem> items;
//private ListView listView;
//private ICarePresenter presenter;
//@Override
//public int getCount() {
//	// TODO Auto-generated method stub
//	return items.size();
//}
//@Override
//public CareItem getItem(int position) {
//	// TODO Auto-generated method stub
//	return items.get(position);
//}
//@Override
//public long getItemId(int position) {
//	// TODO Auto-generated method stub
//	return position;
//}
//@Override
//public View getView(int position, View convertView, ViewGroup parent) {
//	if (convertView == null) {
//		convertView = View.inflate(get,
//				R.layout.item_list_app, null);
//		new ViewHolder(convertView);
//	}
//	ViewHolder holder = (ViewHolder) convertView.getTag();
//	CareItem items = getItem(position);
//	holder.iv_icon.setImageDrawable(items.loadIcon(getPackageManager()));
//	holder.tv_name.setText(items.loadLabel(getPackageManager()));
//	return convertView;
//}
//
//class ViewHolder {
//	ImageView iv_icon;
//	TextView tv_name;
//
//	public ViewHolder(View view) {
//		iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
//		tv_name = (TextView) view.findViewById(R.id.tv_name);
//		view.setTag(this);
//	}
//}
//}
