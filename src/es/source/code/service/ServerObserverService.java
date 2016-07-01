package es.source.code.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import es.source.code.model.Food;
import es.source.code.model.FoodMenu;

public class ServerObserverService extends Service {
	
	private ArrayList<Food> stock;
	StockThread st;

	private final Messenger msger = new Messenger(new cMessageHandler());
	private Messenger aMessenger = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		stock = new ArrayList<Food>();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return msger.getBinder();
	}
	
	class cMessageHandler extends Handler{
		@Override
		public void handleMessage(Message msg){
			switch(msg.what){
				case 0:
					if (st != null) {
			            st.interrupt();
			        }
					System.out.println("service msg.what:0");
					break;
				case 1:
					aMessenger = msg.replyTo;
					st = new StockThread();
					st.start();
					System.out.println("service msg.what:1");
					break;
				
			}
		}
	}
	
	class StockThread extends Thread{
		public void run(){
			
			try {
				FoodMenu menu =new FoodMenu();
				ArrayList<Food> cold = menu.getColdFoods();
				for(Food food : cold){
					stock.add(food);
				}
				ArrayList<Food> hot = menu.getHotFoods();
				for(Food food : hot){
					stock.add(food);
				}
				ArrayList<Food> sea = menu.getSeaFoods();
				for(Food food : sea){
					stock.add(food);
				}
				ArrayList<Food> drink = menu.getDrinkFoods();
				for(Food food : drink){
					stock.add(food);
				}
				if(isAppInForeground(ServerObserverService.this)){
					Message msg = Message.obtain(null, 10);
					Bundle bundle = new Bundle();
					bundle.putSerializable("stock", stock);
					msg.setData(bundle);
					aMessenger.send(msg);
				}
				sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static boolean isAppInForeground(Context context) {  
	    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
	    List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();  
	    for (RunningAppProcessInfo appProcess : appProcesses) {  
	        if (appProcess.processName.equals(context.getPackageName())) {  
	            return appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND;  
	        }  
	    }  
	    return false;  
	} 
}
