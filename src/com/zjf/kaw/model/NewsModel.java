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
 * Created by zjf 2016-6-30下午3:06:18
 */
public class NewsModel {
	/**
	 * 查询新闻列表
	 * 
	 * @param mainFragmentActivity
	 */
	public void findAllNewsList(final Callback callback, final String url,final String parameter) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			// 工作线程中执行，发送http请求，解析list

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

			// 主线程中执行，调用fragment的方法执行
			@Override
			protected void onPostExecute(List<News> result) {
				callback.onDataLoaded(result);
			}
		};

		task.execute();// 执行异步任务
	}

	public interface Callback {
		/**
		 * 当列表加载完毕后 将会调用该方法 在该方法的实现中需要执行列表加载完毕后的业务逻辑
		 * 
		 * @param musics
		 */
		void onDataLoaded(List<News> news);


		
	}

}
