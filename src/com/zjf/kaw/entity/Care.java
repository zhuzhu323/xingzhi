package com.zjf.kaw.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.util.GlobalConsts;

public class Care implements Serializable {
	private List<CareItem> items = new ArrayList<CareItem>();

	public List<CareItem> getItems() {
		return this.items;
	}

	// 移除收藏项
	public void deleteNews(String id) {
		for (CareItem item : items) {
			if (item.getNews().getId() == id) {
				items.remove(item);
				return;
			}
		}
		saveCare();
	}

	// 持久化到文件中，下次打开的时候收藏夹依然存在
	public void saveCare() {
		try {
			File file = new File(MyApplication.getContext().getCacheDir(),
					GlobalConsts.CART_CACHE_FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file));
			oos.writeObject(this);
			oos.flush();
			oos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从序列化的文件中读取收藏夹信息
	 */
	public Care readCare() {
		try {
			File file = new File(MyApplication.getContext().getCacheDir(),
					GlobalConsts.CART_CACHE_FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			Care care = (Care) ois.readObject();
			if (care == null) {
				return new Care();
			}
			return care;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Care();

	}
	
}
