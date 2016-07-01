package es.source.code.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ustc.scos.R;
import com.ustc.scos.R.string;

import es.source.code.model.Food;
import es.source.code.model.User;

public class FoodView extends FragmentActivity {

	private GridView navigationView;
	private List<Map<String, Object>> navigation_list;
	
	private int []icon = {R.drawable.order, R.drawable.form, R.drawable.login, R.drawable.help};
	private String []iconName =  {"点菜", "查看订单", "登录/注册", "系统帮助"};
	
	private boolean navigationState = true;
	private boolean isStartService = true;

	
	private User user;
	
	//-----------------------------------------------------------------------------------------
	//几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    
  //UI Objects
    private RadioGroup rg_food_bar;
    private RadioButton rb_cold;
    private RadioButton rb_hot;
    private RadioButton rb_sea;
    private RadioButton rb_drink;
    private ViewPager vpager;

    private FragmentAdapter mAdapter;
	
  //-----------------------------------------------------------------------------------------
    private final Messenger aMessenger = new Messenger(new sMessageHandle());
	
	private Messenger sMessenger;
	private boolean isBound = false;
	public static ArrayList<Food> stock;
	
	private ServiceConnection sc = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			sMessenger = null;
			isBound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			sMessenger = new Messenger(service);
			isBound = true;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_view);
   
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		navigationView = (GridView) findViewById(R.id.navigation);
		
		setNavigation();
		
		navigationView.setOnItemClickListener(new ItemClickListener());
		
		mAdapter = new FragmentAdapter(getSupportFragmentManager());
		
		//foodview
        findById();
        
        rb_cold.setChecked(true);
		
        Intent intent = getIntent();
        user=(User)intent.getSerializableExtra("user");

	}
	
	private void findById(){
		rg_food_bar = (RadioGroup) findViewById(R.id.rg_food_bar);
		
		rb_cold = (RadioButton) findViewById(R.id.rb_cold);
		rb_hot = (RadioButton) findViewById(R.id.rb_hot);
		rb_sea = (RadioButton) findViewById(R.id.rb_sea);
		rb_drink = (RadioButton) findViewById(R.id.rb_drink);
		
		rg_food_bar.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
	            case R.id.rb_cold:
	                vpager.setCurrentItem(PAGE_ONE);
	                break;
	            case R.id.rb_hot:
	                vpager.setCurrentItem(PAGE_TWO);
	                break;
	            case R.id.rb_sea:
	                vpager.setCurrentItem(PAGE_THREE);
	                break;
	            case R.id.rb_drink:
	                vpager.setCurrentItem(PAGE_FOUR);
	                break;
	        }
				
			}
		});
		
		vpager = (ViewPager) findViewById(R.id.vpager);
		vpager.setAdapter(mAdapter);
	    vpager.setCurrentItem(0);
	    vpager.addOnPageChangeListener(new OnPageChangeListener(){
	        @Override
	        public void onPageScrollStateChanged(int state) {
	            //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
	            if (state == 2) {
	                switch (vpager.getCurrentItem()) {
	                    case PAGE_ONE:
	                        rb_cold.setChecked(true);
	                        
	                        break;
	                    case PAGE_TWO:
	                        rb_hot.setChecked(true);
	                        break;
	                    case PAGE_THREE:
	                        rb_sea.setChecked(true);
	                        break;
	                    case PAGE_FOUR:
	                        rb_drink.setChecked(true);
	                        break;
	                }
	            }
	        }
	        
	        //重写ViewPager页面切换的处理方法
	        @Override
	        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	        }

	        @Override
	        public void onPageSelected(int position) {
	        }
		});
		
	}
	
	
	
 	public void setNavigation(){
		navigation_list = new ArrayList<Map<String, Object>>();
		getData();
		String []from = {"image","text"};
		int []to = {R.id.icon,R.id.iconName};
		SimpleAdapter adapter = new SimpleAdapter(this, navigation_list, R.layout.navigation, from, to);
		navigationView.setAdapter(adapter);		
	}
	
	public List<Map<String, Object>> getData(){
		for(int i = 0; i < icon.length; i++){
			Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            navigation_list.add(map);
		}
		return navigation_list;
	}
	
	
	//The AdapterView where the click happened ,The view within the AdapterView that was clicked 
	//The position of the view in the adapter ,The row id of the item that was clicked 
	class  ItemClickListener implements OnItemClickListener{     
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {     
			Bundle bundle = new Bundle();
        	bundle.putSerializable("user",user);
			switch (icon[arg2]) {
			case R.drawable.order:
				break;
			case R.drawable.form:
				Intent form_intent = new Intent(FoodView.this, FoodOrderView.class);
            	form_intent.putExtras(bundle);
				startActivity(form_intent);
				break;
			case R.drawable.login:
				Intent login_intent = new Intent(FoodView.this,LoginOrRegister.class);
                startActivity(login_intent);
				break;
			case R.drawable.help:
				
				break;
			}
		}
	}     



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SERVICE");
		bindService(intent, sc, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(isBound){
			unbindService(sc);
			isBound = false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.order, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putSerializable("user",user);
		switch (item.getItemId()) {
			case R.id.order_food:
				Intent food_order_intent = new Intent(FoodView.this, FoodOrderView.class);
	        	food_order_intent.putExtras(bundle);
	        	food_order_intent.putExtra("isorder", false);
				startActivity(food_order_intent);
				break;
			case R.id.order_form:
				Intent order_form_intent = new Intent(FoodView.this, FoodOrderView.class);
	        	order_form_intent.putExtras(bundle);
	        	order_form_intent.putExtra("isorder", true);
				startActivity(order_form_intent);
				break;
			case R.id.order_call:
				Toast.makeText(this, "order call", Toast.LENGTH_SHORT).show();
				break;
			case R.id.order_service:
				Message msg;
				if(isStartService){
					msg = Message.obtain(null, 1);
					msg.replyTo=aMessenger;
					isStartService = false;
					item.setTitle(string.stop_service);
				}else{
					msg = Message.obtain(null, 0);
					isStartService = true;
					item.setTitle(string.order_service);
				}
				if(isBound){
					try {
						sMessenger.send(msg);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case android.R.id.home:
				this.finish();
		
		}
		return super.onOptionsItemSelected(item);
	}
	class sMessageHandle extends Handler{

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
				case 10:
					stock = (ArrayList<Food>) msg.getData().getSerializable("stock");
					System.out.println("click msg.what:10" + stock);
					break;
			}
			
			super.handleMessage(msg);
		}
		
	}
	
}
