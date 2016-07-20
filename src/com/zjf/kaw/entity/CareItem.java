package com.zjf.kaw.entity;

import java.io.Serializable;

public class CareItem implements Serializable{
private News news;


public CareItem(){
	
}


public CareItem(News news) {
	super();
	this.news = news;
}


public News getNews() {
	return news;
}


public void setNews(News news) {
	this.news = news;
}

}
