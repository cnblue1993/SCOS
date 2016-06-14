package es.source.code.activity;

import java.util.ArrayList;

import com.ustc.scos.R;

import es.source.code.model.Food;
import es.source.code.model.Form;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.cardemulation.HostApduService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodListAdapter extends BaseAdapter {

	private ArrayList<Food> foods;
	private Context context;
	private final String TAG = "FoodListAdapter";
	private static Form tempForm;
	
	
	
	public FoodListAdapter(ArrayList<Food> foods, Context context) {
		super();
		if(foods == null){
			foods = new ArrayList<Food>();
		}
		this.foods = foods;
		this.context = context;
		this.tempForm = MainScreen.getTempForm();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(foods != null){
			return foods.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(foods != null){
			foods.get(position);
		}
		return null;
	}
	

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.food_item, null);
			holder = new ViewHolder();
			holder.img = (ImageView) view.findViewById(R.id.food_image);
			holder.name = (TextView) view.findViewById(R.id.food_name);
			holder.price = (TextView) view.findViewById(R.id.food_price);
			holder.order = (Button) view.findViewById(R.id.food_order);
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		Food food = foods.get(position);
		
		if(food != null){
			holder.img.setImageResource(food.getImg());
			holder.name.setText(food.getName());
			holder.price.setText(food.getPrice()+" 元/份");
			if(food.isOredered()){
				holder.order.setText("退点");
			}else{
				holder.order.setText("添加");
			}
				
		}
		//holder.order.setFocusable(false);
		holder.order.setOnClickListener(new ButtonListener(position));
		return view;
	}
	
	class ViewHolder{
		ImageView img;
		TextView name, price;
		Button order;
	}
	
	class ButtonListener implements OnClickListener{
		private int position;
		
		public ButtonListener(int pos) {
			// TODO Auto-generated constructor stub
			position = pos;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Food food = foods.get(position);
			Button btn_order = (Button) v.findViewById(R.id.food_order);
	        if(btn_order.getText().toString().equals("添加")){
	        	btn_order.setText("退点");
	        	food.setOredered(true);
	        	food.setState(1);
	        	food.setCount(food.getCount() + 1);
	        	
	        	tempForm.getFoods().add(food);
	        	
	        	Toast.makeText(context, "点菜成功", Toast.LENGTH_SHORT).show();
	        	notifyDataSetChanged();
	        }else{
	        	btn_order.setText("添加");
	        	food.setOredered(false);
	        	food.setState(0);
	        	food.setCount(food.getCount() - 1);
	        	tempForm.getFoods().remove(food);
	        	notifyDataSetChanged();
	        }
		}
	}
	public static Form getTempForm(){
		return tempForm;
	}
}
