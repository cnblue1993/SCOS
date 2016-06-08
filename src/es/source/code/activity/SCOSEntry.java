package es.source.code.activity;

import android.R;
import android.R.layout;
import android.R.xml;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SCOSEntry extends Activity {
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
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}
