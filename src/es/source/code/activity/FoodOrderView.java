package es.source.code.activity;

import java.util.ArrayList;
import java.util.Iterator;

import com.ustc.scos.R;

import es.source.code.activity.FoodView.ItemClickListener;
import es.source.code.model.Food;
import es.source.code.model.Form;
import es.source.code.model.User;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class FoodOrderView extends FragmentActivity {
	//几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    
    //ui
    private RadioGroup rg_food_order_bar;
    private RadioButton rb_food_ordered;
    private RadioButton rb_food_unorder;
    private ViewPager vPager_order;
    
    private Button btn_submit;
    private TextView tv_message;
    
    private OrderFragmentAdapter adapter;
    
    private static Form tempForm;
    private static Form unpayForm;
    private static ArrayList<Form> forms;
    
    private User user;
    
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_order_view);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//btn_submit = (Button) findViewById(R.id.order_submit);
		//tv_message = (TextView) findViewById(R.id.order_message);
		
		//btn_submit.setOnClickListener(new submitListener());
		
		adapter = new OrderFragmentAdapter(getSupportFragmentManager());
		
		tempForm = MainScreen.getTempForm();
		forms = MainScreen.getForm();
		unpayForm = MainScreen.getUnpayForm();
		//form = 
		
		//foodview
        findById();
        
        Intent intent = getIntent();
        user=(User)intent.getSerializableExtra("user");
        if(user != null)
        	Toast.makeText(this, user.getUserName(), Toast.LENGTH_SHORT).show();
        
        if(intent.getBooleanExtra("isorder", false))
        	rb_food_ordered.setChecked(true);
        else
        	rb_food_unorder.setChecked(true);
        //tv_message.setText("总计："+UnorderFragment.getTempForm().getFoodCount()+" 道菜 "+"，一共："+UnorderFragment.getTempForm().getFoodSum()+"元");
	
	}
    
    private void findById(){
		rg_food_order_bar = (RadioGroup) findViewById(R.id.rg_food_order_bar);
		
		rb_food_unorder = (RadioButton) findViewById(R.id.rb_unorder);
		rb_food_ordered = (RadioButton) findViewById(R.id.rb_ordered);
		
		rg_food_order_bar.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
		            case R.id.rb_unorder:
		                vPager_order.setCurrentItem(PAGE_ONE);
		                break;
		            case R.id.rb_ordered:
		                vPager_order.setCurrentItem(PAGE_TWO);
		                break;
				}
				
			}
		});
		
		vPager_order = (ViewPager) findViewById(R.id.vpager_order);
		vPager_order.setAdapter(adapter);
		
	    vPager_order.setCurrentItem(0);
	    vPager_order.addOnPageChangeListener(new OnPageChangeListener(){


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
	                switch (vPager_order.getCurrentItem()) {
	                    case PAGE_ONE:
	                        rb_food_unorder.setChecked(true);                        
	                      
	                        break;
	                        
	                    case PAGE_TWO:
	                        rb_food_ordered.setChecked(true);  
	                        break;
	                }
	            }
	        }
	            
		});
		
	}

    
    public static Form getUnpayForm(){
    	return unpayForm;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    
    
}
