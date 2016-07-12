package com.zjf.kaw.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zjf.kaw.entity.News;

/**
 * Created by zjf 2016-6-30下午7:11:42
 */
public class JSONParser {
	/**
	 * 解析json字符串 获取新闻的基本信息
	 * 
	 * @param json
	 *            { Newsurl: {url: [{},{},{},{}] } , NewsInfo: {} }
	 * @return
	 * @throws JSONException
	 */
	public static News parseNewsInfo(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		JSONObject urlObj = obj.getJSONObject("Newsurl");
		JSONObject infoObj = obj.getJSONObject("NewsInfo");
		JSONArray urlAry = urlObj.getJSONArray("url");

		return null;
	}
	/**
	 * 解析搜索结果列表
	 * 
	 * @param json
	 *            { News_list : [{},{},{},{}] }
	 * @return
	 * @throws JSONException
	 */
	public static List<News> parseSearchResult(String json)
			throws JSONException {
		JSONObject obj = new JSONObject(json);
		JSONArray ary = obj.getJSONArray("News_list");
		List<News> news= new ArrayList<News>();
		for (int i = 0; i < ary.length(); i++) {
			JSONObject nObj = ary.getJSONObject(i);
			News n = new News();
			n.setChannel(nObj.getString("channel"));
			n.setId(nObj.getString("id"));
			n.setImg(nObj.getString("imageurls"));
			n.setTime(nObj.getString("pubDate"));
			n.setAuthor(nObj.getString("source"));
			n.setTitle(nObj.getString("title"));
					
		
			news.add(n);
		}
		return news;
	}

}
