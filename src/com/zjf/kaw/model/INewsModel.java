package com.zjf.kaw.model;

/**
 * Created by zjf 2016-7-14обнГ1:54:42
 */
public interface INewsModel extends IModel {
//	void faindAllNewsList(AsyncCallBack callBack);
void findPopularNewsList(AsyncCallBack asyncCallBack,int page);
void findSportsNewsList(AsyncCallBack asyncCallBack,int page);
void findTechNewsList(AsyncCallBack asyncCallBack,int page);
void findAutoNewsList(AsyncCallBack asyncCallBack,int page);
}