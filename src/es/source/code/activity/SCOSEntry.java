package es.source.code.activity;

import android.R;
import android.R.layout;
import android.R.xml;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SCOSEntry extends Activity {
	//手指坐标
	float x1=0,x2=0,y1=0,y2=0;
	private static final String TAG = "entry";
	private int loginState;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//取消标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//取消状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(com.ustc.scos.R.layout.entry);
//		Handler handler = new Handler();
//		handler.postDelayed(new entryHandler(), 2000);
	}
	
//	class entryHandler implements Runnable{
//		public void run(){
//			startActivity(new Intent(getApplication(),MainScreen.class));
//			SCOSEntry.this.finish();
//		}
//	}
	
	//左滑跳转
	@Override
	 public boolean onTouchEvent(MotionEvent event) {
	 //继承了Activity的onTouchEvent方法，直接监听点击事件
		
		 if(event.getAction() == MotionEvent.ACTION_DOWN) {
			 //当手指按下的时候
			 x1 = event.getX();
			 y1 = event.getY();
		 }
		 if(event.getAction() == MotionEvent.ACTION_UP) {
			  //当手指离开的时候
			  x2 = event.getX();
			  y2 = event.getY();
			  
			  
			  if(x1 - x2 > 50) {		//left
				  Log.i(TAG, "left");
				  Intent intent = new Intent("android.intent.action.SCOSMAIN");
				  intent.addCategory("android.intent.category.SCOSLAUNCHER");
				 // intent.putExtra("entryReturn", "FromEntry");
				  startActivity(intent);
				  
			  } else if(y2 - y1 > 50) {		//down
				  Log.i(TAG, "down");
			  } else if(y1 - y2 > 50) {	//up
				  Log.i(TAG, "up");  
			  } else if(x2 - x1 > 50) {		//right
					  Log.i(TAG, "right");
			  }
		 }
		 return super.onTouchEvent(event);
	 }
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
		//loginState = preferences.getInt("loginState", 0);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}
