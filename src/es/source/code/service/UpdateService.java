package es.source.code.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ustc.scos.R;

public class UpdateService extends IntentService {
	private final String TAG = "UpdateService";

	public UpdateService() {
		super("UpdateService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getExtras().getString("type");  
        if(action.equals("new")){
        	notifyNewFood();
        }
	}
	
	private void notifyNewFood(){
		final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // params
        int smallIconId = R.drawable.order;
        Bitmap largeIcon = ((BitmapDrawable) getResources().getDrawable(R.drawable.order)).getBitmap();
        String info = "鱼香肉丝 20元/份 热菜";

        // action when clicked
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DETAIL");
        intent.addCategory("android.intent.category.DETAILLAUNCHER");
        intent.putExtra("from", 0);
        intent.putExtra("position", 0);
        

        builder.setLargeIcon(largeIcon)
                .setSmallIcon(smallIconId)
                .setContentTitle("新品上架:")
                .setContentText(info)
                .setTicker(info)
                .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0));

        final Notification n = builder.build();

        nm.notify(0, n);
	}
 
}
