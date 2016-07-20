package com.zjf.kaw.presenter;

import com.zjf.kaw.entity.News;


public interface ICarePresenter extends IPresenter{
	/**
	 * 添加新闻
	 */
	public boolean addNews(News news);
	/**
	 * 删除新闻
	 */
	public void deleteNews(String newsId);

}
