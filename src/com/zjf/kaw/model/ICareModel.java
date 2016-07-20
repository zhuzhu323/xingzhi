package com.zjf.kaw.model;

import com.zjf.kaw.entity.News;

public interface ICareModel extends IModel{
	/**
	 * 添加新闻
	 * 
	 */
	public boolean addNews(News news);
	/**
	 * 删除新闻
	 * 
	 */
	public void deleteNews(String newsId);

}
