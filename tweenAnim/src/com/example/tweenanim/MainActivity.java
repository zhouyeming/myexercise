package com.example.tweenanim;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView flower = (ImageView) findViewById(R.id.flower);
		final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		anim.setFillAfter(true);
		final Animation reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
		reverse.setFillAfter(true);
		Button bn = (Button) findViewById(R.id.bn);
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				if(msg.what == 0x123){
					flower.startAnimation(reverse);
				}
			}
		};
		bn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				flower.startAnimation(anim);
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						handler.sendEmptyMessage(0x123);
					}
					
				},3500);
			}			
		});
	}
}
