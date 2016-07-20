package com.zjf.kaw.app;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zjf.kaw.entity.Care;
import com.zjf.kaw.entity.News;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

/**
 * Created by zjf 2016-6-30ÏÂÎç1:22:25
 */
public class MyApplication extends Application {
	public ArrayList<Activity> activityList = new ArrayList<Activity>();
	private static MyApplication context;
	private RequestQueue queue;
	private List<News> news;
	private Care care;

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public static MyApplication getContext() {
		return context;
	}

	public RequestQueue getQueue() {
		return queue;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		x.Ext.init(this);
		context = this;
		care=new Care();
		care=care.readCare();
		queue = Volley.newRequestQueue(this);
	}

	public Care getCare() {
		return care;
	}

	public void setCare(Care care) {
		this.care = care;
	}

	public void finishActivity() {
		for (Activity activity : activityList) {
			try {
				activity.finish();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Process.killProcess(Process.myPid());
	}
}
