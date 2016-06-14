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
import android.view.ViewGroup;
import android.widget.ListView;

public class UnorderFragment extends ListFragment{
	
	private ArrayList<Food> foods;
	private ListView listView;
	private FoodOrderListAdapter adapter;
	private static Form tempFrom;
	
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View unorderView = inflater.inflate(R.layout.food_unorder, container,false);  
        
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
			adapter = new FoodOrderListAdapter(tempFrom, getActivity().getApplicationContext(), false);
		}
		setListAdapter(adapter);
		
	}
	
	private void initData(){
		tempFrom = MainScreen.getTempForm();
		foods = tempFrom.getFoods();
		tempFrom.setFoodCount(foods.size());
		
		int sum = 0;
		Iterator<Food> iterator = foods.iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next(); 
		    sum += Integer.parseInt(e.getPrice());
		}
		tempFrom.setFoodSum(sum);
		/*
		if(foods == null)
			foods = new ArrayList<Food>();
		Iterator<Food> iterator = MainScreen.getColdFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 1){  
		    	foods.add(e);
		    }  
		} 
		iterator = MainScreen.getHotFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 1){  
		    	foods.add(e);
		    }  
		} 
		iterator = MainScreen.getSeaFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 1){  
		    	foods.add(e);
		    }  
		} 
		iterator = MainScreen.getDrinkFoods().iterator();  
		while(iterator.hasNext()){  
		    Food e = iterator.next();  
		    if(e.getState() == 1){  
		    	foods.add(e);
		    }  
		}
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
	public FoodOrderListAdapter getUnorderAdapter(){
		return adapter;
	}
	public static Form getTempForm(){
		return tempFrom;
	}
}
