package com.example.moveback;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
    class MyView extends View{
    	final int BACK_HEIGHT = 1700;
    	private Bitmap back;
    	private Bitmap plane;
    	final int WIDTH = 320;
    	final int HEIGHT = 440;
    	private int startY = BACK_HEIGHT - HEIGHT;
    	
		public MyView(Context context) {
			super(context);
			back = BitmapFactory.decodeResource(context.getResources(), R.drawable.back_img);
			plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
			final Handler handler = new Handler(){
				public void handleMessage(Message msg){
					if(msg.what == 0x123){
						if(startY <= 0){
							startY = BACK_HEIGHT - HEIGHT;
						}
						else
						{
							startY -= 3;
						}							
					}
					invalidate();	
				}
			};
			new Timer().schedule(new TimerTask(){

				@Override
				public void run() {
					handler.sendEmptyMessage(0x123);
					
				}
				
			}, 0, 100);
		}
    	@Override
    	public void onDraw(Canvas canvas){
    		Bitmap bitmap2 = Bitmap.createBitmap(back, 0, startY, WIDTH, HEIGHT);
    		canvas.drawBitmap(bitmap2, 0, 0, null);
    		canvas.drawBitmap(plane, 160, 380, null);
    	}
    }
}
