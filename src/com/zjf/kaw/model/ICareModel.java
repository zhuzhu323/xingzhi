package com.zjf.kaw.model;

import com.zjf.kaw.entity.News;

public interface ICareModel extends IModel{
	/**
	 * �������
	 * 
	 */
	public boolean addNews(News news);
	/**
	 * ɾ������
	 * 
	 */
	public void deleteNews(String newsId);

}
