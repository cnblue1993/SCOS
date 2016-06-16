package es.source.code.activity;

import java.util.ArrayList;
import java.util.Iterator;

import com.ustc.scos.R;

import es.source.code.model.Food;
import es.source.code.model.Form;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UnorderFragment extends ListFragment{
	
	private ArrayList<Food> foods;
	private ListView listView;
	private FoodOrderListAdapter adapter;
	private static Form tempFrom;
	private static Form unpayForm;
	
	private Button btn_submit;
	private static TextView unorder_message;
	
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View unorderView = inflater.inflate(R.layout.food_unorder, container,false);  
        
        if(listView == null){
			listView = (ListView) unorderView.findViewById(android.R.id.list);
        }
        
        btn_submit = (Button) unorderView.findViewById(R.id.unorder_btn);
        unorder_message = (TextView) unorderView.findViewById(R.id.unorder_message);
        
        unorder_message.setText("总计："+tempFrom.getFoodCount()+" 道菜 "+"，一共："+tempFrom.getFoodSum()+"元");
        btn_submit.setOnClickListener(new submitListener());
        
        return unorderView;      
    }  
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);  
        
		initData();
		
		if(adapter == null){
			adapter = new FoodOrderListAdapter(tempFrom, getActivity().getApplicationContext(), false);
		}
		setListAdapter(adapter);
		
		
		
	}
	
	private void initData(){
		tempFrom = MainScreen.getTempForm();
		foods = tempFrom.getFoods();
		
		unpayForm = MainScreen.getUnpayForm();
		
	}

	@Override  
    public void onListItemClick(ListView l, View v, int position, long id) {  //listview 存在button等，会使该方法失效
        super.onListItemClick(l, v, position, id);  
        System.out.println(position);         
    }   

	@Override  
    public void onActivityCreated(Bundle savedInstanceState){  
        super.onActivityCreated(savedInstanceState);  
    }
	
	public ArrayList<Food> getFoods(){
		return foods;
	}
	public FoodOrderListAdapter getUnorderAdapter(){
		return adapter;
	}
	public static Form getTempForm(){
		return tempFrom;
	}
  class submitListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			unpayForm.setFoodCount(tempFrom.getFoodCount());
			unpayForm.setFoods(tempFrom.getFoods());
			unpayForm.setFoodSum(tempFrom.getFoodSum());
			
			tempFrom.setFoods(new ArrayList<Food>());
			tempFrom.setFoodCount(0);
			tempFrom.setFoodSum(0);
			
			//未及时刷新数据
			setListAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}
  
  	public static void updata(){
  		unorder_message.setText("总计："+tempFrom.getFoodCount()+" 道菜 "+"，一共："+tempFrom.getFoodSum()+"元");
  	}
}
