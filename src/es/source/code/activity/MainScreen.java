package es.source.code.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Templates;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ustc.scos.R;

import es.source.code.model.Food;
import es.source.code.model.FoodMenu;
import es.source.code.model.Form;
import es.source.code.model.User;

public class MainScreen extends FragmentActivity {
	private GridView navigationView;
	private List<Map<String, Object>> navigation_list;
	
	private int []icon = {R.drawable.order, R.drawable.form, R.drawable.login, R.drawable.help};
	private String []iconName =  {"点菜", "查看订单", "登录/注册", "系统帮助"};
	
	private boolean navigationState = true;
	
	private User user = null;
	
	private static FoodMenu foodMenu;
	
	private static ArrayList<Form> forms;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		Intent intent =getIntent();
		String entryReturn = intent.getStringExtra("entryReturn");
        String loginReturn = intent.getStringExtra("loginReturn");
        
        if(!("FromEntry".equals(entryReturn) || "LoginSuccess".equals(loginReturn) || "RegisterSuccess".equals(loginReturn))){
        	navigationState = false;
        }else{
        	user=(User)intent.getSerializableExtra("user");
        	//存在问题，未在导航栏加载后执行
        	if(null != user && !user.getOldUser()){
        		Toast.makeText(this, "欢迎您成为 SCOS 新用户", Toast.LENGTH_SHORT).show();
        	}
        	
        	navigationState = true;
        }
		
		navigationView = (GridView) findViewById(R.id.navigation);
		
		setNavigation();
		
		navigationView.setOnItemClickListener(new ItemClickListener());
		
		foodMenu = new FoodMenu(); 
		forms = new ArrayList<Form>();
		Form tempForm = new Form();
		forms.add(tempForm);
		Form unpayForm = new Form(1,0,0,new ArrayList<Food>());
		forms.add(unpayForm);
	}
 
	
 	public void setNavigation(){
		navigation_list = new ArrayList<Map<String, Object>>();
		getData();
		String []from = {"image","text"};
		int []to = {R.id.icon,R.id.iconName};
		SimpleAdapter adapter = new MyAdapter(this, navigation_list, R.layout.navigation, from, to);
		System.out.println(adapter);
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
	
	
	class MyAdapter extends SimpleAdapter{

		public MyAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView =  super.getView(position, convertView, parent);
			
			if(!navigationState && (position == 0 || position == 1)){
				convertView.setVisibility(View.GONE);
			}
			
			return convertView;
			
		}
		
		
	}
	
	//The AdapterView where the click happened ,The view within the AdapterView that was clicked 
	//The position of the view in the adapter ,The row id of the item that was clicked 
	class  ItemClickListener implements OnItemClickListener{     
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {     
			Bundle bundle = new Bundle();
        	bundle.putSerializable("user",user);
			switch (icon[arg2]) {
			case R.drawable.order:
				Intent order_intent = new Intent(MainScreen.this, FoodView.class);
				
            	order_intent.putExtras(bundle);
				startActivity(order_intent);
				break;
			case R.drawable.form:
				Intent form_intent = new Intent(MainScreen.this, FoodOrderView.class);
            	form_intent.putExtras(bundle);
				startActivity(form_intent);
				break;
			case R.drawable.login:
				Intent login_intent = new Intent(MainScreen.this,LoginOrRegister.class);
                startActivity(login_intent);
				break;
			case R.drawable.help:
				
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
	
	public static ArrayList<Food> getColdFoods() {
		return foodMenu.getColdFoods();
	}
	public static ArrayList<Food> getHotFoods() {
		return foodMenu.getHotFoods();
	}
	public static ArrayList<Food> getSeaFoods() {
		return foodMenu.getSeaFoods();
	}
	public static ArrayList<Food> getDrinkFoods() {
		return foodMenu.getDrinkFoods();
	}
	
	public static Form getTempForm(){
		return forms.get(0);
	}
	public static Form getUnpayForm(){
		return forms.get(1);
	}
	public static  void addForm(Form form){
		forms.add(form);
	}
	public static  ArrayList<Form> getForm(int state){
		ArrayList<Form> temp = new ArrayList<Form>();
		Iterator<Form> iterator = forms.iterator();  
		while(iterator.hasNext()){  
		    Form e = iterator.next();  
		    if(e.getState() == state){  
		    	temp.add(e);
		    }  
		}
		return temp;
	}
	public static  ArrayList<Form> getForm(){
		return forms;
	}
}
