package com.example.butterfly;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private final static String TAG = "butterfly";
	private float curX = 0;
	private float curY = 30;
	float nextX, nextY = 0;
	Timer timer = new Timer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG,"oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView imageView = (ImageView)findViewById(R.id.butterfly);
		
		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x123)
				{
					if(nextX > 320)
					{
						curX = nextX = 0;
					}
					else
					{
						nextX += 8;
					}

					nextY = curY + (float)(Math.random() * 10);

					TranslateAnimation anim 
						= new TranslateAnimation(curX , nextX , curY , nextY);
					curX = nextX;
					curY = nextY;
					//anim.setDuration(200);
	
					imageView.startAnimation(anim);					
				}
			}			
		};
		final AnimationDrawable butterfly = (AnimationDrawable)imageView.getBackground();
		imageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.d(TAG, "onClick");
				
				if(butterfly.isRunning())
					butterfly.stop();
				
				butterfly.start();

				timer.schedule(new TimerTask()
				{
					@Override
					public void run()
					{
						handler.sendEmptyMessage(0x123);						
					}
					
				}, 0, 200);			
			}
		});

	}
	/*
	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
		timer.cancel();;
	}
	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		super.onResume();
		timer = new Timer();
	}
	@Override
	protected void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}
	@Override
	protected void onResume(){
		Log.d(TAG, "onResume");
		super.onResume();		
	}*/
}
