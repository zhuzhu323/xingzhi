package com.zjf.kaw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zjf.kaw.R;
import com.zjf.kaw.app.MyApplication;

public class MainActivity extends Activity {
	int count=3;
	private TextView tvCount;
	private TextView tvEnter;
	private boolean pressed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setViews();
		setListeners();
		MyApplication myApplication = (MyApplication) getApplication();
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if(pressed==true){
					finish();
				}else{
				tvCount.setText(String.valueOf(count+"s"));
				count=count-1;                                                                                
				if(count>-1){
					handler.postDelayed(this, 1000);
				}else{
					startActivity(new Intent(MainActivity.this,
							MainFragmentActivity.class));
					finish();
					
					}
					
				}
			}
		}, 0);
	}

	private void setListeners() {
		tvEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						MainFragmentActivity.class));
				pressed=true;
				finish();
				
			}
		});
		
	}

	private void setViews() {
	tvCount=(TextView) findViewById(R.id.tv_count);
	tvEnter=(TextView) findViewById(R.id.tv_enter);
		
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
