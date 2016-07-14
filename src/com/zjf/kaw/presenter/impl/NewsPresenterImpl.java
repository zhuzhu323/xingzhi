package com.zjf.kaw.presenter.impl;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.IModel.AsyncCallBack;
import com.zjf.kaw.model.INewsModel;
import com.zjf.kaw.model.impl.NewsModelImpl;
import com.zjf.kaw.presenter.INewsPresenter;
import com.zjf.kaw.view.NewsView;

/**
 * Created by zjf 2016-7-14ÉÏÎç10:30:14
 */
public class NewsPresenterImpl implements INewsPresenter {
	private NewsView view;
	private INewsModel model;

	public NewsPresenterImpl(NewsView view, Context context) {
		this.view = view;
		model = new NewsModelImpl(context);

	}

	@Override
	public void getPopularNews(int page) {
		model.findPopularNewsList(new AsyncCallBack() {

			@Override
			public void onSuccess(List<News> news) {
				view.showPopularNewsList(news);
				Log.i("5555", view.toString());
			}

			@Override
			public void onFailed() {

			}
		}, page);
	}

	@Override
	public void getSportsNews(int page) {
		model.findSportsNewsList(new AsyncCallBack() {

			@Override
			public void onSuccess(List<News> news) {
				view.showSportsNewsList(news);
			}

			@Override
			public void onFailed() {
				// TODO Auto-generated method stub

			}
		}, page);
	}

	@Override
	public void getTechNews(int page) {
		model.findTechNewsList(new AsyncCallBack() {

			@Override
			public void onSuccess(List<News> news) {
				view.showTechNewsList(news);

			}

			@Override
			public void onFailed() {
				// TODO Auto-generated method stub

			}
		}, page);
	}

	@Override
	public void getAutoNews(int page) {
		model.findAutoNewsList(new AsyncCallBack() {

			@Override
			public void onSuccess(List<News> news) {
				// TODO Auto-generated method stub
				view.showAutoNewsList(news);
				Log.i("aaa", view.toString());
			}

			@Override
			public void onFailed() {
				// TODO Auto-generated method stub

			}
		}, page);
	}
}