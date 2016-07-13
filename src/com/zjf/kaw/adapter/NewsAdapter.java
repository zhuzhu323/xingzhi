package com.zjf.kaw.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.bumptech.glide.Glide;
import com.zjf.kaw.R;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.util.GlobalConsts;
import com.zjf.kaw.util.ImageLoader;

public class NewsAdapter extends BaseAdapter {

	private List<News> news;
	private LayoutInflater inflater;
	private Context context;

	public NewsAdapter(Context context, List<News> news) {
		super();
		this.news = news;
		this.inflater = LayoutInflater.from(context);
		this.setContext(context);		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return news.size();
	}

	@Override
	public News getItem(int position) {
		// TODO Auto-generated method stub
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.news_item, null);
			holder = new ViewHolder();
			holder.ivPic = (ImageView) convertView.findViewById(R.id.ivNewsPic);
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.tvNewsTitle);
			holder.tvPdate = (TextView) convertView
					.findViewById(R.id.tvUpdateTime);
			holder.tvAuthor=(TextView) convertView.findViewById(R.id.tvSource);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		// 给holder中控件赋值
		News n = getItem(position);
//		long time=Long.parseLong(n.getTime());
		long time = System.currentTimeMillis();
		Date date=new Date(time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		holder.tvTitle.setText(n.getTitle());
		holder.tvPdate.setText(sdf.format(date));
		holder.tvAuthor.setText(n.getAuthor());
		Glide.with(context).load(n.getImg()).into(holder.ivPic);

		return convertView;
	}

	public class BitmapCache implements ImageCache {
		private LruCache<String, Bitmap> mCache;

		public BitmapCache() {
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes() * value.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String arg0) {
			// TODO Auto-generated method stub
			return mCache.get(arg0);
		}

		@Override
		public void putBitmap(String arg0, Bitmap arg1) {
			mCache.put(arg0, arg1);

		}

	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	class ViewHolder {
		ImageView ivPic;
		TextView tvTitle;
		TextView tvPdate;
		TextView tvAuthor;
	}
}
