package es.source.code.activity;

import java.util.ArrayList;

import com.ustc.scos.R;

import es.source.code.model.Food;
import es.source.code.model.Form;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
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
	
	private Form tempForm;
	
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
		
		tempForm = MainScreen.getTempForm();
		
		initUI();
		
		
		//updateUI(foods.get(position));
	
		btn = (Button) findViewById(R.id.detail_btn);
		btn.setOnClickListener(new btnListener());
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
		tv_foodname.setText("菜名："+food.getName());
		tv_foodprice.setText("单价："+food.getPrice()+"元 / 份");
		et_foodremark.setText(food.getRemark());
		Editable text = et_foodremark.getText();
		Selection.setSelection(text, text.length());
		if(food.isOredered())
			btn.setText("退点");
		else {
			btn.setText("添加");
		}
	}
	
	class btnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Food food = foods.get(position);
			if(btn.getText() == "退点"){
				
				//System.out.println(tempForm.getFoods().indexOf(food));
				food.setState(0);
				food.setOredered(false);
				food.setCount(food.getCount() - 1);
				MainScreen.getTempForm().getFoods().remove(food);
				btn.setText("添加");
			}else{
				food.setState(1);
				food.setOredered(true);
				food.setCount(food.getCount() + 1);
				btn.setText("退点");				
				tempForm.getFoods().add(food);				
			}
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
		String remark = et_foodremark.getText().toString();
		foods.get(position).setRemark(remark);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Intent get_intent = getIntent();
		
		foods = MainScreen.getfoods(get_intent.getIntExtra("from", 0));
		position = get_intent.getIntExtra("position", 0);
		
		maxIndex = foods.size() - 1;

		updateUI(foods.get(position));
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//System.out.println("onstart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//System.out.println("onstop");
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
