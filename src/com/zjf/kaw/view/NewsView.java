package com.zjf.kaw.view;

import java.util.List;

import com.zjf.kaw.entity.News;

/**
 *Created by zjf 2016-7-14ионГ10:14:31
 */
public interface NewsView {
	void showPopularNewsList(List<News>news);
	void showSportsNewsList(List<News>news);
	void showTechNewsList(List<News>news);
	void showAutoNewsList(List<News>news);

}
