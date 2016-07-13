package com.zjf.kaw.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonParser;
import com.zjf.kaw.entity.News;
import com.zjf.kaw.util.GlobalConsts;
import com.zjf.kaw.util.HttpUtils;
import com.zjf.kaw.util.JSONParserUtils;
import com.zjf.kaw.util.UrlFactory;

/**
 * Created by zjf 2016-6-30����3:06:18
 */
public class NewsModel {
	/**
	 * ��ѯ�����б�
	 * 
	 * @param mainFragmentActivity
	 */
	public void findAllNewsList(final Callback callback, final String url,final String parameter) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			// �����߳���ִ�У�����http���󣬽���list

			@Override
			protected List<News> doInBackground(String... params) {
				String json = HttpUtils.request(url,
						parameter);
				Log.i("Test", json + "\n");
				try {
					List<News> Newslist = JSONParserUtils.parserJson(json);
					return Newslist;
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
