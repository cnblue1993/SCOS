package es.source.code.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ustc.scos.R;

import es.source.code.activity.MainScreen.ItemClickListener;
import es.source.code.activity.MainScreen.MyAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FoodView extends FragmentActivity {

	private GridView navigationView;
	private List<Map<String, Object>> navigation_list;
	
	private int []icon = {R.drawable.order, R.drawable.form, R.drawable.login, R.drawable.help};
	private String []iconName =  {"点菜", "查看订单", "登录/注册", "系统帮助"};
	
	private boolean navigationState = true;
	
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
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_view);
   
		
		navigationView = (GridView) findViewById(R.id.navigation);
		
		setNavigation();
		
		navigationView.setOnItemClickListener(new ItemClickListener());
		
		mAdapter = new FragmentAdapter(getSupportFragmentManager());
		
		//foodview
        findById();
        
        rb_cold.setChecked(true);
		
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


	        //重写ViewPager页面切换的处理方法
	        @Override
	        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	        }

	        @Override
	        public void onPageSelected(int position) {
	        }

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
			switch (icon[arg2]) {
			case R.drawable.order:
				
				break;
			case R.drawable.form:
				
				break;
			case R.drawable.login:
				Intent login_intent = new Intent(FoodView.this,LoginOrRegister.class);
                startActivity(login_intent);
				break;
			case R.drawable.help:
				
				break;
			default:
				break;
			}
		}
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
