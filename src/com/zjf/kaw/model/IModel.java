package com.zjf.kaw.model;

import java.util.List;

import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.IModel.AsyncCallBack;

/**
 * Created by zjf 2016-7-14обнГ1:55:25
 */
public interface IModel {
	public interface AsyncCallBack {
		public void onSuccess(List<News> news);
		public void onFailed();
	}

}
