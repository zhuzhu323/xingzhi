package com.zjf.kaw.presenter;

import com.zjf.kaw.entity.News;


public interface ICarePresenter extends IPresenter{
	/**
	 * �������
	 */
	public boolean addNews(News news);
	/**
	 * ɾ������
	 */
	public void deleteNews(String newsId);

}
