package com.zjf.kaw.entity;

public class News {
	private String id;
	private String channel;
	private String title;
	private String url;
	private String img;
	private String author;
	private String time;
	public News() {
		super();
	}
	public News(String id, String channel, String title, String url,
			String img, String author, String time) {
		super();
		this.id = id;
		this.channel = channel;
		this.title = title;
		this.url = url;
		this.img = img;
		this.author = author;
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


	
}