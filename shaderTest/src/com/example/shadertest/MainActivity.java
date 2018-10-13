package com.example.shadertest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Shader[] shaders = new Shader[5];
	private int[] colors;
	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myView = (MyView) findViewById(R.id.my_view);
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.water);
		colors = new int[]{Color.RED, Color.GREEN, Color.BLUE};
		shaders[0] = new BitmapShader(bm, TileMode.REPEAT, TileMode.MIRROR);
		shaders[1] = new LinearGradient(0, 0, 100, 100, colors, null, TileMode.REPEAT);
		shaders[2] = new RadialGradient(100, 100, 80, colors, null, TileMode.REPEAT);
		shaders[3] = new SweepGradient(160, 160, colors, null);
		shaders[4] = new ComposeShader(shaders[1], shaders[2], PorterDuff.Mode.DARKEN);
		Button bn1 = (Button) findViewById(R.id.bn1);
		Button bn2 = (Button) findViewById(R.id.bn2);
		Button bn3 = (Button) findViewById(R.id.bn3);
		Button bn4 = (Button) findViewById(R.id.bn4);
		Button bn5 = (Button) findViewById(R.id.bn5);
		
		bn1.setOnClickListener(this);
		bn2.setOnClickListener(this);		
		bn3.setOnClickListener(this);
		bn4.setOnClickListener(this);
		bn5.setOnClickListener(this);		
	}
	@Override
	public void onClick(View source){
		switch(source.getId()){
		case R.id.bn1:
			myView.paint.setShader(shaders[0]);
			break;
		case R.id.bn2:
			myView.paint.setShader(shaders[1]);
			break;
		case R.id.bn3:
			myView.paint.setShader(shaders[2]);
			break;			
		case R.id.bn4:
			myView.paint.setShader(shaders[3]);
			break;	
		case R.id.bn5:
			myView.paint.setShader(shaders[4]);
			break;
		}
		myView.invalidate();
	}
}
