package com.zjf.kaw.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.zjf.kaw.entity.News;
import com.zjf.kaw.util.GlobalConsts;
import com.zjf.kaw.util.HttpUtils;
import com.zjf.kaw.util.UrlFactory;

/**
 * Created by zjf 2016-6-30����3:06:18
 */
public class NewsModel {
	String httpUrl = "http://apis.baidu.com/3023/news/channel";
	String httpArg = "id=popular&page=1";
	/**
	 * ��ѯ�����б�
	 * 
	 * @param mainFragmentActivity
	 */
	public void findAllNewsList(final Callback callback, final String channelId) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			// �����߳���ִ�У�����http���󣬽���list

			@Override
			protected List<News> doInBackground(String... params) {
				String json = HttpUtils.request(GlobalConsts.NEWS_BASE_Url,
						channelId);
				Log.i("Test", json + "\n");

				try {
					JSONObject obj = new JSONObject(json);
					JSONObject data = obj
							.getJSONObject("data");

					String channel = data
							.getString("channel");
					Log.i("11",channel);
					List<News> data1 = new ArrayList<News>();
					JSONArray array1 = data.getJSONArray("article");
					Log.i("000",array1.toString());
					for (int i = 0; i < array1.length(); i++) {
						JSONObject content = array1.getJSONObject(i);
						News news = new News();
						news.setTitle(content.getString("title"));
						news.setUrl(content.getString("url"));
						news.setAuthor(content.getString("author"));
						news.setTime(content.getString("time"));					
						news.setImg(content.getString("img"));					
											

							data1.add(news);
						Log.i("Test", news.toString() + "\n");
						
					}
					return data1;
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}

			// ���߳���ִ�У�����fragment�ķ���ִ��
			@Override
			protected void onPostExecute(List<News> result) {
				callback.onDataLoaded(result);
			}
		};

		task.execute();// ִ���첽����
	}

	public interface Callback {
		/**
		 * ���б������Ϻ� ������ø÷��� �ڸ÷�����ʵ������Ҫִ���б������Ϻ��ҵ���߼�
		 * 
		 * @param musics
		 */
		void onDataLoaded(List<News> news);


		
	}

}
