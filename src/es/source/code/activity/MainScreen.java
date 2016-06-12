package es.source.code.activity;

import com.ustc.scos.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainScreen extends Activity {
	//导航栏
 	private LinearLayout ly_order;
	private LinearLayout ly_form;
	private LinearLayout ly_login;
	private LinearLayout ly_help;
	
	private final int entryRequest = 0;
	private final int loginRequest = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		//绑定布局
		ly_order = (LinearLayout) findViewById(R.id.ly_order);
		ly_form = (LinearLayout) findViewById(R.id.ly_form);
		ly_login = (LinearLayout) findViewById(R.id.ly_login);
		ly_help = (LinearLayout) findViewById(R.id.ly_help);
		
		ly_login.setOnClickListener(clickListener);
		
		Intent intent =getIntent();
		String entryReturn = intent.getStringExtra("entryReturn");
        String loginReturn = intent.getStringExtra("loginReturn");
        
        if(!("FromEntry".equals(entryReturn) || "LoginSuccess".equals(loginReturn))){
        	hideOrderAndForm();
        }else{
        	showOrderAndForm();
        }
		
	}
	public void hideOrderAndForm(){
		ly_form.setVisibility(View.GONE);
		ly_order.setVisibility(View.GONE);
	}
	
	public void showOrderAndForm(){
		ly_form.setVisibility(View.VISIBLE);
		ly_order.setVisibility(View.VISIBLE);
	}
	
	public OnClickListener clickListener = new OnClickListener() {  
		  
        public void onClick(View v) { 
            switch (v.getId()) {
				case R.id.ly_order:
					
					break;
				case R.id.ly_form:
					
					break;
				case R.id.ly_login:
					Intent login_intent = new Intent(MainScreen.this,LoginOrRegister.class);
	                startActivity(login_intent);
					break;
				case R.id.ly_help:
	
					break;
			}
        }  
    };
    
    

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
