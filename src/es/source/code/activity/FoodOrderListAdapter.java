package es.source.code.activity;

import java.util.ArrayList;

import com.ustc.scos.R;

import es.source.code.activity.FoodListAdapter.ButtonListener;
import es.source.code.activity.FoodListAdapter.ViewHolder;
import es.source.code.model.Food;
import es.source.code.model.Form;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodOrderListAdapter extends BaseAdapter {
	
	private ArrayList<Food> foods;
	private Form form;
	private Context context;
	private boolean isordered;
	private final String TAG = "FoodOrderListAdapter";
	
	
	public FoodOrderListAdapter(Form form, Context context, boolean isordered) {
		super();
		if(foods == null){
			foods = new ArrayList<Food>();
		}
		this.form = form;
		this.foods = form.getFoods();
		this.context = context;
		this.isordered = isordered;
	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(foods != null){
			return foods.size();
		}
		return 0;
	}
	
//	public ArrayList<Food> getFoods(){
//		if(foods != null)
//			return foods;
//		return null;
//	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(foods != null){
			return foods.get(position);
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
			view = LayoutInflater.from(context).inflate(R.layout.food_order_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.order_name);
			holder.price = (TextView) view.findViewById(R.id.order_price);
			holder.count = (TextView) view.findViewById(R.id.order_count);
			holder.remark = (TextView) view.findViewById(R.id.order_remark);
			holder.del = (Button) view.findViewById(R.id.order_del);
			holder.del.setOnClickListener(new ButtonListener(position));
			
			if(isordered){
				holder.del.setVisibility(View.GONE);
			}
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		Food food = foods.get(position);
		
		if(food != null){
			holder.name.setText(food.getName());
			holder.price.setText(food.getPrice()+" 元/份");
			holder.count.setText(food.getCount()+"份");
			holder.remark.setText(food.getRemark());
		}
		//holder.order.setFocusable(false);
		return view;
	}
	
	class ViewHolder{
		TextView name, price,count,remark;
		Button del;
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
	        food.setOredered(false);
	        food.setState(0);
	        MainScreen.getTempForm().getFoods().remove(food);
	        
	        //未更新点菜界面button
	        
	        Toast.makeText(context, "退点成功", Toast.LENGTH_SHORT).show();
	        
	        notifyDataSetChanged();//---unorder listview 及时更新
	        UnorderFragment.updata();//更新总计信息
		}
	}
}
