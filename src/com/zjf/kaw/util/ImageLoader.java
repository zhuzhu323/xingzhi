package com.zjf.kaw.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjf.kaw.R;

/**
 * 图片异步批量加载的工具类
 * 自动完成 单线程下载图片
 * 内存缓存   文件缓存
 * @author pjy
 *
 */
public class ImageLoader {
	//声明用于实现内存缓存的map
	private Map<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
	private Context context;
	//声明图片下载任务集合
	private List<ImageLoadTask> tasks=new ArrayList<ImageLoadTask>();
	//声明用于轮循任务队列的工作线程
	private Thread workThread;
	private boolean isLoop=true;
	private ListView listView;
	// 声明handler 显示图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOAD_IMAGE_SUCCESS: // 图片下载成功
				ImageLoadTask task = (ImageLoadTask) msg.obj;
				Bitmap bitmap = task.bitmap;
				// 通过listView.findViewWithTag()方法获取相应的imageView
				ImageView imageView = (ImageView) listView
						.findViewWithTag(task.path);
				if (imageView != null) {
					if (bitmap != null) {
						imageView.setImageBitmap(bitmap);
					} else {
						imageView.setImageResource(R.drawable.ic_launcher);
					}
				}
				break;
			}
		}
	};

	public static final int HANDLER_LOAD_IMAGE_SUCCESS = 1;

	
	public ImageLoader(Context context, ListView listView) {
		this.context = context;
		this.listView = listView;
		//初始化并且启动workThread
		workThread = new Thread(){
			public void run() {
				//不断轮循任务集合  一旦有任务  则获取然后执行下载操作
				while(isLoop){
					if(!tasks.isEmpty()){ //有任务
						ImageLoadTask task=tasks.remove(0);
						String path = task.path;
						//下载图片
						Bitmap bitmap = loadBitmap(path);
						task.bitmap = bitmap;
						//给imageView 设置 Bitmap
						Message msg = new Message();
						msg.what = HANDLER_LOAD_IMAGE_SUCCESS;
						msg.obj = task;
						handler.sendMessage(msg);
						
					}else{ //没有任务  工作线程等待
						try {
							synchronized (workThread) {
								workThread.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		workThread.start();
	}
	
	/**
	 * 通过path 下载图片
	 * 
	 * @param path
	 * @return
	 */
	public Bitmap loadBitmap(String path) {
//		try {
//			InputStream is = HttpUtils.get(path);
//			Bitmap b = BitmapUtils.loadBitmap(is, 50, 50);
//			// 下载成功 把b存入内存缓存中
//			cache.put(path, new SoftReference<Bitmap>(b));
//			// 存入文件缓存目录中
//			String filename = path.substring(path.lastIndexOf("/") + 1);
//			// f --> /data/data/cn.tedu.music/cache/xxxxx.jpg
//			File f = new File(context.getCacheDir(), filename);
//			BitmapUtils.save(b, f.getAbsolutePath());
//			return b;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return null;
	}

	
	/**
	 * 通过path 下载图片 并且把图片显示在相应的ImageView中
	 * @param imageView
	 * @param path
	 */
	public void displayImage(ImageView imageView, String imagePath){

		//设置图片  从缓存中读取图片 
		SoftReference<Bitmap> ref=cache.get(imagePath);
		if(ref!=null){
			Bitmap bitmap=ref.get();
			if(bitmap!=null){ //图片还没有被销毁
				imageView.setImageBitmap(bitmap);
				return;
			}
		}
		//内存缓存中没有图片  则去文件缓存中读取
		String filename = imagePath.substring(imagePath.lastIndexOf("/")+1);
		File f = new File(context.getCacheDir(), filename);
		Bitmap bitmap=BitmapUtils.loadBitmap(f.getAbsolutePath());
		if(bitmap!=null){
			imageView.setImageBitmap(bitmap);
			//向内存缓存中再存一次
			cache.put(imagePath, new SoftReference<Bitmap>(bitmap));
			return;
		}
		// 向任务集合中添加一个图片下载任务
		//给imageView设置一个tag值  用于handler中通过tag值找到imageView
		imageView.setTag(imagePath);
		ImageLoadTask task = new ImageLoadTask();
		task.path = imagePath;
		tasks.add(task);
		//唤醒工作线程  赶紧起来干活
		synchronized (workThread) {
			workThread.notify();
		}
	}
	
	/**
	 * 封装一个图片下载任务
	 */
	class ImageLoadTask {
		String path;   //图片路径
		Bitmap bitmap;  //通过路径下载成功的bitmap
	}

	/**
	 * 停止线程
	 */
	public void stopThread() {
		isLoop = false;
		synchronized (workThread) {
			// 唤醒工作线程
			workThread.notify();
		}
	}
	
}








