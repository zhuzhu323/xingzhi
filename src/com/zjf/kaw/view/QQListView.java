package com.zjf.kaw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

public class QQListView extends ListView {
	private int mScreenWidth;	// ��Ļ���
	private int mDownX;			// ���µ��xֵ
	private int mDownY;			// ���µ��yֵ
	private int mDeleteBtnWidth;// ɾ����ť�Ŀ��
	
	private boolean isDeleteShown;	// ɾ����ť�Ƿ�������ʾ
	
	private ViewGroup mPointChild;	// ��ǰ�����item
	private LinearLayout.LayoutParams mLayoutParams;	// ��ǰ�����item��LayoutParams
	
	public QQListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public QQListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		// ��ȡ��Ļ���
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			performActionDown(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			return performActionMove(ev);
		case MotionEvent.ACTION_UP:
			performActionUp();
			break;
		}
		
		return super.onTouchEvent(ev);
	}

	// ����action_down�¼�
	private void performActionDown(MotionEvent ev) {
		if(isDeleteShown) {
			turnToNormal();
		}
		
		mDownX = (int) ev.getX();
		mDownY = (int) ev.getY();
		// ��ȡ��ǰ���item
		mPointChild = (ViewGroup) getChildAt(pointToPosition(mDownX, mDownY)
				- getFirstVisiblePosition());
		// ��ȡɾ����ť�Ŀ��
		mDeleteBtnWidth = mPointChild.getChildAt(1).getLayoutParams().width;
		mLayoutParams = (LinearLayout.LayoutParams) mPointChild.getChildAt(0)
				.getLayoutParams();
		// ΪʲôҪ��������layout_width ������Ļ���
		// ��Ϊmatch_parentʱ����������ô������������ʾɾ����ť
		// why�� ��Ϊmatch_parentʱ��ViewGroup�Ͳ�ȥ����ʣ�µ�view
		mLayoutParams.width = mScreenWidth;
		mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
	}
	
	// ����action_move�¼�
	private boolean performActionMove(MotionEvent ev) {
		int nowX = (int) ev.getX();
		int nowY = (int) ev.getY();
		if(Math.abs(nowX - mDownX) > Math.abs(nowY - mDownY)) {
			// ������󻬶�
			if(nowX < mDownX) {
				// ����Ҫƫ�Ƶľ���
				int scroll = (nowX - mDownX) / 2;
				// ���������ɾ����ť�Ŀ�ȣ� �����Ϊɾ����ť�Ŀ��
				if(-scroll >= mDeleteBtnWidth) {
					scroll = -mDeleteBtnWidth;
				}
				// ��������leftMargin
				mLayoutParams.leftMargin = scroll;
				mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
			}
			
			return true;
		}
		return super.onTouchEvent(ev);
	}
	
	// ����action_up�¼�
	private void performActionUp() {
		// ƫ��������button��һ�룬����ʾbutton
		// ����ָ�Ĭ��
		if(-mLayoutParams.leftMargin >= mDeleteBtnWidth / 2) {
			mLayoutParams.leftMargin = -mDeleteBtnWidth;
			isDeleteShown = true;
		}else {
			turnToNormal();
		}
		
		mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
	}

	/**
	 * ��Ϊ����״̬
	 */
	public void turnToNormal() {
		mLayoutParams.leftMargin = 0;
		mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
		isDeleteShown = false;
	}
	
	/**
	 * ��ǰ�Ƿ�ɵ��
	 * @return �Ƿ�ɵ��
	 */
	public boolean canClick() {
		return !isDeleteShown;
	}
}
