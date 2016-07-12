package com.zjf.kaw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.zjf.kaw.R;
import com.zjf.kaw.app.MyApplication;
import com.zjf.kaw.model.NewsModel;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyApplication myApplication = (MyApplication) getApplication();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(MainActivity.this,
						MainFragmentActivity.class));
				finish();
			}
		}, 3000);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

}
