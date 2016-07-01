package es.source.code.br;

import javax.mail.internet.ContentDisposition;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceStartedListener extends BroadcastReceiver {

	private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
	
    @Override
    public void onReceive(Context context, Intent intent) {
	    if (ACTION_BOOT.equals(intent.getAction())){
	    	System.out.println("broad");
	    	Intent updateIntent = new Intent();
			updateIntent.setAction("android.intent.action.UPDATESERVICE");
	
	        updateIntent.putExtra("type","new");
	
	        context.startService(updateIntent);  
	    }
    }
}
