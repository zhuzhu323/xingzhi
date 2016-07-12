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
 * ͼƬ�첽�������صĹ�����
 * �Զ���� ���߳�����ͼƬ
 * �ڴ滺��   �ļ�����
 * @author pjy
 *
 */
public class ImageLoader {
	//��������ʵ���ڴ滺���map
	private Map<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
	private Context context;
	//����ͼƬ�������񼯺�
	private List<ImageLoadTask> tasks=new ArrayList<ImageLoadTask>();
	//����������ѭ������еĹ����߳�
	private Thread workThread;
	private boolean isLoop=true;
	private ListView listView;
	// ����handler ��ʾͼƬ
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOAD_IMAGE_SUCCESS: // ͼƬ���سɹ�
				ImageLoadTask task = (ImageLoadTask) msg.obj;
				Bitmap bitmap = task.bitmap;
				// ͨ��listView.findViewWithTag()������ȡ��Ӧ��imageView
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
		//��ʼ����������workThread
		workThread = new Thread(){
			public void run() {
				//������ѭ���񼯺�  һ��������  ���ȡȻ��ִ�����ز���
				while(isLoop){
					if(!tasks.isEmpty()){ //������
						ImageLoadTask task=tasks.remove(0);
						String path = task.path;
						//����ͼƬ
						Bitmap bitmap = loadBitmap(path);
						task.bitmap = bitmap;
						//��imageView ���� Bitmap
						Message msg = new Message();
						msg.what = HANDLER_LOAD_IMAGE_SUCCESS;
						msg.obj = task;
						handler.sendMessage(msg);
						
					}else{ //û������  �����̵߳ȴ�
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
	 * ͨ��path ����ͼƬ
	 * 
	 * @param path
	 * @return
	 */
	public Bitmap loadBitmap(String path) {
//		try {
//			InputStream is = HttpUtils.get(path);
//			Bitmap b = BitmapUtils.loadBitmap(is, 50, 50);
//			// ���سɹ� ��b�����ڴ滺����
//			cache.put(path, new SoftReference<Bitmap>(b));
//			// �����ļ�����Ŀ¼��
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
	 * ͨ��path ����ͼƬ ���Ұ�ͼƬ��ʾ����Ӧ��ImageView��
	 * @param imageView
	 * @param path
	 */
	public void displayImage(ImageView imageView, String imagePath){

		//����ͼƬ  �ӻ����ж�ȡͼƬ 
		SoftReference<Bitmap> ref=cache.get(imagePath);
		if(ref!=null){
			Bitmap bitmap=ref.get();
			if(bitmap!=null){ //ͼƬ��û�б�����
				imageView.setImageBitmap(bitmap);
				return;
			}
		}
		//�ڴ滺����û��ͼƬ  ��ȥ�ļ������ж�ȡ
		String filename = imagePath.substring(imagePath.lastIndexOf("/")+1);
		File f = new File(context.getCacheDir(), filename);
		Bitmap bitmap=BitmapUtils.loadBitmap(f.getAbsolutePath());
		if(bitmap!=null){
			imageView.setImageBitmap(bitmap);
			//���ڴ滺�����ٴ�һ��
			cache.put(imagePath, new SoftReference<Bitmap>(bitmap));
			return;
		}
		// �����񼯺������һ��ͼƬ��������
		//��imageView����һ��tagֵ  ����handler��ͨ��tagֵ�ҵ�imageView
		imageView.setTag(imagePath);
		ImageLoadTask task = new ImageLoadTask();
		task.path = imagePath;
		tasks.add(task);
		//���ѹ����߳�  �Ͻ������ɻ�
		synchronized (workThread) {
			workThread.notify();
		}
	}
	
	/**
	 * ��װһ��ͼƬ��������
	 */
	class ImageLoadTask {
		String path;   //ͼƬ·��
		Bitmap bitmap;  //ͨ��·�����سɹ���bitmap
	}

	/**
	 * ֹͣ�߳�
	 */
	public void stopThread() {
		isLoop = false;
		synchronized (workThread) {
			// ���ѹ����߳�
			workThread.notify();
		}
	}
	
}








