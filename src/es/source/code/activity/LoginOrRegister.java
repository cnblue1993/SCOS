package es.source.code.activity;

import com.ustc.scos.R;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginOrRegister extends Activity {
	private EditText et_account;
	private EditText et_pwd;
	private Button btn_login;
	private Button btn_back;
	
	private int loginReturn = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		et_account = (EditText)findViewById(R.id.et_account);
		et_pwd = (EditText)findViewById(R.id.et_pwd);
		btn_login = (Button)findViewById(R.id.btn_login);
		btn_back = (Button)findViewById(R.id.btn_back);
	
		btn_login.setOnClickListener(clickListener);
		btn_back.setOnClickListener(clickListener);
		
	}
	
	public OnClickListener clickListener = new OnClickListener() {  
		  
        public void onClick(View v) {
        	
        	Intent intent = new Intent("android.intent.action.SCOSMAIN");
			intent.addCategory("android.intent.category.SCOSLAUNCHER");
        	boolean flag1 = true;
        	boolean flag2 = true;
            switch (v.getId()) {
				case R.id.btn_login:
					intent.putExtra("loginReturn", "LoginSuccess");
					flag1 = checkFormat(et_account.getText().toString());
					flag2 = checkFormat(et_pwd.getText().toString());
					break;
				case R.id.btn_back:
					intent.putExtra("loginReturn", "Return");
					break;
            } 
            if(flag1 && flag2){
            	startActivity(intent);
            }
            else{
            	if(!flag1)
            		et_account.setError("输入内容 不符合规则");
            	if(!flag2)
            		et_pwd.setError("输入内容 不符合规则");
            }
        }
    };
	
    boolean checkFormat(String string){
    	String regex = "^[a-zA-Z0-9]+$";
    	return string.length()>0 && string.matches(regex);
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
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
