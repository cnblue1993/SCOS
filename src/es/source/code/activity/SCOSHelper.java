package es.source.code.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.ustc.scos.R;

import es.source.code.model.MailSender;

public class SCOSHelper extends Activity {

	private Context mContext;
    private GridView gv_helper;
    private BaseAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;
    
    private mServiceReceiver mReceiver01, mReceiver02;
    /* 自定义ACTION常数，作为广播的Intent Filter识别常数 */
    //SMS_SEND_ACTIOIN 为发送成功接收到的回复；SMS_DELIVERED_ACTION 是短信接受者接受到短信的回复
    private static String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
    private static String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
    
    MyHandler myHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helper);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		myHandler = new MyHandler();
		
        mContext = SCOSHelper.this;
        gv_helper = (GridView) findViewById(R.id.gv_helper);

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.call, "电话人工帮助"));
        mData.add(new Icon(R.drawable.sms, "短信帮助"));
        mData.add(new Icon(R.drawable.email, "邮件帮助"));

        mAdapter = new MyAdapter<Icon>(mData, R.layout.helper_item) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_helper, obj.getiId());
                holder.setText(R.id.tv_helper, obj.getiName());
            }
        };

        gv_helper.setAdapter(mAdapter);

        gv_helper.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
					case 0:
						Intent intent = new Intent();
				        intent.setAction(Intent.ACTION_CALL);
				        intent.setData(Uri.parse("tel:5554"));
				        startActivity(intent);
						break;

					case 1:
//						Intent intent2 = new Intent();
//				        intent2.setAction(Intent.ACTION_SENDTO);
//				        intent2.setData(Uri.parse("smsto:5554"));
//				        intent2.putExtra("sms_body", "test scos helper");
//				        
//				        PendingIntent mSendPI = PendingIntent.getBroadcast(getApplicationContext(), 0, intent2, 0);
//				        startActivity(intent2);
						
						/* 欲发送的电话号码 */
				        String strDestAddress = "5554";
				        
				        /* 欲发送的短信内容 */
				        String strMessage = "test scos helper";
				        
				        /* 建立SmsManager对象 */
				        SmsManager smsManager = SmsManager.getDefault();
				        
				        // TODO Auto-generated method stub
				        try
				        {
				          /* 建立自定义Action常数的Intent(给PendingIntent参数之用) */
				          Intent itSend = new Intent(SMS_SEND_ACTIOIN);
				          Intent itDeliver = new Intent(SMS_DELIVERED_ACTION);
				          
				          /* sentIntent参数为传送后接受的广播信息PendingIntent */
				          PendingIntent mSendPI = PendingIntent.getBroadcast(getApplicationContext(), 0, itSend, 0);
				          
				          /* deliveryIntent参数为送达后接受的广播信息PendingIntent */
				          PendingIntent mDeliverPI = PendingIntent.getBroadcast(getApplicationContext(), 0, itDeliver, 0);
				          
				          /* 发送SMS短信，注意倒数的两个PendingIntent参数 */
				          smsManager.sendTextMessage(strDestAddress, null, strMessage, mSendPI, mDeliverPI);
				        }
				        catch(Exception e)
				        {
				          e.printStackTrace();
				        }
						break;
						
					case 2:
						SenderRunnable senderRunnable = new SenderRunnable(
								"Luopanpan1993@gmail.com", "luo19930501");
						senderRunnable.setMail("this is the test subject",
								"this is the test body", "Luopanpan1993@gmail.com",null);
						new Thread(senderRunnable).start();
						break;
				}
			}
        });

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		/* 取消注册自定义Receiver */
	    unregisterReceiver(mReceiver01);
	    unregisterReceiver(mReceiver02);
	    
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		/* 自定义IntentFilter为SENT_SMS_ACTIOIN Receiver */
	    IntentFilter mFilter01;
	    mFilter01 = new IntentFilter(SMS_SEND_ACTIOIN);
	   // mFilter01 = new IntentFilter(Intent.ACTION_SENDTO);
	    mReceiver01 = new mServiceReceiver();
	    registerReceiver(mReceiver01, mFilter01);
	    
	    /* 自定义IntentFilter为DELIVERED_SMS_ACTION Receiver */
	    mFilter01 = new IntentFilter(SMS_DELIVERED_ACTION);
	    mReceiver02 = new mServiceReceiver();
	    registerReceiver(mReceiver02, mFilter01);
	    
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

			case android.R.id.home:
				this.finish();
		
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class mServiceReceiver extends BroadcastReceiver
	  {
	    @Override
	    public void onReceive(Context context, Intent intent)
	    {
	      // TODO Auto-generated method stub
	      
	      //mTextView01.setText(intent.getAction().toString());
	      if (intent.getAction().equals(SMS_SEND_ACTIOIN))
	    	//if (intent.getAction().equals(Intent.ACTION_SENDTO))
	      {
	        try
	        {
	          /* android.content.BroadcastReceiver.getResultCode()方法 */
	          //Retrieve the current result code, as set by the previous receiver.
	          switch(getResultCode())
	          {
	            case Activity.RESULT_OK:
	              /* 发送短信成功 */
	              //mTextView01.setText(R.string.str_sms_sent_success);
	            	Toast.makeText(SCOSHelper.this, "求助短信发送成功", Toast.LENGTH_LONG).show();
	            	break;	
	            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	              /* 发送短信失败 */
	            	Toast.makeText(SCOSHelper.this, "求助短信发送成功", Toast.LENGTH_LONG).show();
	            	break;
	            case SmsManager.RESULT_ERROR_RADIO_OFF:
	            	break;
	            case SmsManager.RESULT_ERROR_NULL_PDU:
	            	break;
	          }        
	        }
	        catch(Exception e)
	        {
	          e.getStackTrace();
	        }
	      }
	      else if(intent.getAction().equals(SMS_DELIVERED_ACTION))
	      {
	        try
	        {
	          /* android.content.BroadcastReceiver.getResultCode()方法 */
	          switch(getResultCode())
	          {
	            case Activity.RESULT_OK:
	              /* 短信 */
	              //mTextView01.setText(R.string.str_sms_sent_success);
	            	Toast.makeText(SCOSHelper.this, "求助短信接收成功", Toast.LENGTH_LONG).show();
	            	break;
	            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	              /* 短信未送达 */
	              //mTextView01.setText(R.string.str_sms_sent_failed);
	            	Toast.makeText(SCOSHelper.this, "求助短信接收失败", Toast.LENGTH_LONG).show();
	            	break;
	            case SmsManager.RESULT_ERROR_RADIO_OFF:
	              break;
	            case SmsManager.RESULT_ERROR_NULL_PDU:
	              break;
	          }        
	        }
	        catch(Exception e)
	        {
	          e.getStackTrace();
	        }
	      }      
	    }
	  }
	class SenderRunnable implements Runnable {

		private String user;
		private String password;
		private String subject;
		private String body;
		private String receiver;
		private MailSender sender;
		private String attachment;
		
		public SenderRunnable(String user, String password) {
			this.user = user;
			this.password = password;
			sender = new MailSender(user, password);
	
			String mailhost=user.substring(user.lastIndexOf("@")+1, user.lastIndexOf("."));
			if(!mailhost.equals("gmail")){
				mailhost="smtp."+mailhost+".com";
				sender.setMailhost(mailhost);
			}
			
		}

		public void setMail(String subject, String body, String receiver,String attachment) {
			this.subject = subject;
			this.body = body;
			this.receiver = receiver;
			this.attachment=attachment;
		}

		public void run() {
			// TODO Auto-generated method stub
			try {  
				Message msg = new Message(); 
	            Bundle bundle = new Bundle();// 存放数据 
	            bundle.putInt("return", 1); 
	            msg.setData(bundle); 
	 
	            myHandler.sendMessage(msg);
	            
				sender.sendMail(subject, body, user, receiver,attachment);	
	            
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	class MyHandler extends Handler { 
	    public MyHandler() { 
	    } 

	    public MyHandler(Looper L) { 
	        super(L); 
	    } 

	    // 子类必须重写此方法，接受数据 
	    @Override 
	    public void handleMessage(Message msg) { 
	        // TODO Auto-generated method stub 
	    	
	        super.handleMessage(msg); 
	        Bundle bundle = msg.getData();
	        if(bundle.getInt("return") == 1){
	        	Toast.makeText(SCOSHelper.this, "求助邮件已发送成功", Toast.LENGTH_LONG).show();
	        }
	    } 
	}
}
