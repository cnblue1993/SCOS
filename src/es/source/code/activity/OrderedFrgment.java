package es.source.code.activity;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ustc.scos.R;

import es.source.code.model.Food;
import es.source.code.model.Form;

public class OrderedFrgment extends ListFragment {
	private ArrayList<Food> foods;
	private ListView listView;
	private FoodOrderListAdapter adapter;
	
	private static Form unpayForm;
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View unorderView = inflater.inflate(R.layout.food_ordered, container,false);  
        
        if(listView == null){
			listView = (ListView) unorderView.findViewById(android.R.id.list);
        }
        
        return unorderView;      
    }  
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);  
        
		initData();
		
		if(adapter == null){
			adapter = new FoodOrderListAdapter(unpayForm, getActivity().getApplicationContext(), true);
		}
		
		setListAdapter(adapter);
		
	}
	
	private void initData(){
		unpayForm = FoodOrderView.getUnpayForm();
		foods = unpayForm.getFoods();
		/*
		if(foods == null)
			foods = new ArrayList<Food>();
		int sum = 0;
		Iterator<Food> iterator = MainScreen.getColdFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 2){  
		    	foods.add(e);
		    	sum += Integer.parseInt(e.getPrice());
		    }  
		} 
		iterator = MainScreen.getHotFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 2){  
		    	foods.add(e);
		    	sum += Integer.parseInt(e.getPrice());
		    }  
		} 
		iterator = MainScreen.getSeaFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 2){  
		    	foods.add(e);
		    	sum += Integer.parseInt(e.getPrice());
		    }  
		} 
		iterator = MainScreen.getDrinkFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 2){  
		    	foods.add(e);
		    	sum += Integer.parseInt(e.getPrice());
		    }  
		} 
		form = new Form();
		form.setFoods(foods);
		form.setFoodCount(foods.size());
		form.setFoodSum(sum);
		*/
		
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
	public FoodOrderListAdapter getOrderAdapter(){
		return adapter;
	}
}
