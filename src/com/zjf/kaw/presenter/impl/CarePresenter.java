package com.zjf.kaw.presenter.impl;

import com.zjf.kaw.entity.News;
import com.zjf.kaw.model.ICareModel;
import com.zjf.kaw.presenter.ICarePresenter;
import com.zjf.kaw.view.ICareView;

public class CarePresenter implements ICarePresenter {
	private ICareModel model;
	

	public CarePresenter(ICareModel model, ICareView view) {
		super();
		this.model = model;
	
	}

	@Override
	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		return model.addNews(news);
	}

	@Override
	public void deleteNews(String newsId) {
		model.deleteNews(newsId);
		
		
	}

}
