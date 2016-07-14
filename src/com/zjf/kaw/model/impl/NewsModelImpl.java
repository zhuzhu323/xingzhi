package com.zjf.kaw.model.impl;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.INewsModel;
import com.zjf.kaw.util.GlobalConsts;
import com.zjf.kaw.util.HttpUtils;
import com.zjf.kaw.util.JSONParserUtils;
import com.zjf.kaw.util.UrlFactory;

/**
 * Created by zjf 2016-7-14ÏÂÎç1:53:18
 */
public class NewsModelImpl implements INewsModel {

	public NewsModelImpl(Context context) {
	}

	@Override
	public void findPopularNewsList(final AsyncCallBack asyncCallBack,
			final int page) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			@Override
			protected List<News> doInBackground(String... params) {
				String json = HttpUtils.request(GlobalConsts.NEWS_BASE_Url,
						UrlFactory.getPopularNewsUrl(page));
				try {
					List<News> Newslist = JSONParserUtils.getNews(json);
					return Newslist;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}

			//
			protected void onPostExecute(java.util.List<News> result) {

				asyncCallBack.onSuccess(result);
			}
		};
		task.execute();

	}

	@Override
	public void findSportsNewsList(final AsyncCallBack asyncCallBack,
			final int page) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			@Override
			protected List<News> doInBackground(String... params) {
				String json = HttpUtils.request(GlobalConsts.NEWS_BASE_Url,
						UrlFactory.getSportsNewsUrl(page));
				Log.i("edu",json);
				try {
					List<News> Newslist = JSONParserUtils.getNews(json);
					return Newslist;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}

			//
			protected void onPostExecute(java.util.List<News> result) {
				Log.i("edu", result.toString());
				asyncCallBack.onSuccess(result);
			}
		};
		task.execute();

	}

	@Override
	public void findTechNewsList(final AsyncCallBack asyncCallBack,
			final int page) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			@Override
			protected List<News> doInBackground(String... params) {
				String json = HttpUtils.request(GlobalConsts.NEWS_BASE_Url,
						UrlFactory.getTechNewsUrl(page));
				try {
					List<News> Newslist = JSONParserUtils.getNews(json);
					return Newslist;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}

			//
			protected void onPostExecute(java.util.List<News> result) {

				asyncCallBack.onSuccess(result);
			}
		};
		task.execute();

	}

	@Override
	public void findAutoNewsList(final AsyncCallBack asyncCallBack,
			final int page) {
		AsyncTask<String, String, List<News>> task = new AsyncTask<String, String, List<News>>() {

			@Override
			protected List<News> doInBackground(String... params) {
				String json = HttpUtils.request(GlobalConsts.NEWS_BASE_Url,
						UrlFactory.getAutoNewsUrl(page));
				try {
					List<News> Newslist = JSONParserUtils.getNews(json);
					return Newslist;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}

			//
			protected void onPostExecute(java.util.List<News> result) {

				asyncCallBack.onSuccess(result);
			}
		};
		task.execute();

	}

}
