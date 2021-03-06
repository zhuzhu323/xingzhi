package com.zjf.kaw.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zjf.kaw.entity.News;

/**
 * Created by zjf 2016-6-30����7:11:42
 */
public class JSONParserUtils {
	public static List<News> getNews(String json) throws Exception {

		JSONObject obj = new JSONObject(json);
		JSONObject data = obj.getJSONObject("data");

		String channel = data.getString("channel");
		List<News> data1 = new ArrayList<News>();
		JSONArray array1 = data.getJSONArray("article");
		for (int i = 0; i < array1.length(); i++) {
			JSONObject content = array1.getJSONObject(i);
			News news = new News();
			news.setTitle(content.getString("title"));
			news.setUrl(content.getString("url"));
			news.setAuthor(content.getString("author"));
			news.setTime(content.getString("time"));
			news.setImg(content.getString("img"));
			data1.add(news);
		}
		return data1;
	}
}
