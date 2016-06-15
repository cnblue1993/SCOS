package es.source.code.activity;

import java.util.ArrayList;

import com.ustc.scos.R;

import es.source.code.model.Food;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodDetailed extends Activity {

	private ImageView iv_img;
	private TextView tv_foodname;
	private TextView tv_foodprice;
	private EditText et_foodremark;
	private Button btn;
	
	private ArrayList<Food> foods;
	private int position;
	private int maxIndex;
	
	//手指坐标
	float x1=0,x2=0,y1=0,y2=0;
	private static final String TAG = "detail";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_detailed);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		initUI();
		
		Intent get_intent = getIntent();
		foods = (ArrayList<Food>) get_intent.getSerializableExtra("foods");
		
		position = get_intent.getIntExtra("position", 0);
		maxIndex = foods.size() - 1;
		
		updateUI(foods.get(position));
		
	}
	
	public void initUI(){
		iv_img = (ImageView) findViewById(R.id.detail_img);
		tv_foodname = (TextView) findViewById(R.id.detail_name);
		tv_foodprice = (TextView) findViewById(R.id.detail_price);
		et_foodremark = (EditText) findViewById(R.id.detail_remark);
		btn = (Button) findViewById(R.id.detail_btn);
	}
	
	public void updateUI(Food food){
		iv_img.setImageResource(food.getImg());
		tv_foodname.setText("菜名为："+food.getName());
		tv_foodprice.setText("单价为："+food.getPrice()+"元 / 份");
		et_foodremark.setText(food.getRemark());
		if(food.isOredered())
			btn.setText("退点");
		else {
			btn.setText("添加");
		}
	}
	
	@Override
	 public boolean onTouchEvent(MotionEvent event) {
	 //继承了Activity的onTouchEvent方法，直接监听点击事件
		
		 if(event.getAction() == MotionEvent.ACTION_DOWN) {
			 //当手指按下的时候
			 x1 = event.getX();
			 y1 = event.getY();
		 }
		 if(event.getAction() == MotionEvent.ACTION_UP) {
			  //当手指离开的时候
			  x2 = event.getX();
			  y2 = event.getY();
			  
			  if(x1 - x2 > 50) {
				  //left
				  if(position > 0)
					  updateUI(foods.get(--position));
				  else 
					  Toast.makeText(this, "当前为最前页", Toast.LENGTH_SHORT).show();
				  
			  }else if(x2 - x1 > 50) {		//right
				  if(position < maxIndex)
					  updateUI(foods.get(++position));
				  else 
					  Toast.makeText(this, "当前为最后页", Toast.LENGTH_SHORT).show();
			  } 
			  else if(y2 - y1 > 50) {		//down
				  Log.i(TAG, "down");
			  }else if(y1 - y2 > 50) {	//up
				  Log.i(TAG, "up");  
			  }
		 }
		 return super.onTouchEvent(event);
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
