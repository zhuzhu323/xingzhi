package com.zjf.kaw.util;

import com.zjf.kaw.app.MyApplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

/**
 * 工具类, 专门处理UI相关的逻辑
 * 
 * @author Kevin
 * 
 */
public class UIUtils {

	public static Context getContext() {
		return MyApplication.getContext();
	}


	/**
	 * 根据id获取字符串
	 */
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	/**
	 * 根据id获取图片
	 */
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	/**
	 * 根据id获取颜色值
	 */
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	/**
	 * 获取颜色状态集合
	 */
	public static ColorStateList getColorStateList(int id) {
		return getContext().getResources().getColorStateList(id);
	}

	/**
	 * 根据id获取尺寸
	 */
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);
	}

	/**
	 * 根据id获取字符串数组
	 */
	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	/**
	 * dp转px
	 */
	public static int dip2px(float dp) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (density * dp + 0.5);
	}

	/**
	 * px转dp
	 */
	public static float px2dip(float px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}

	/**
	 * 加载布局文件
	 */
	public static View inflate(int layoutId) {
		return View.inflate(getContext(), layoutId, null);
	}

	
}
