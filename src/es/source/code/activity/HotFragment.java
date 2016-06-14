package es.source.code.activity;

import java.util.ArrayList;
import java.util.Iterator;

import com.ustc.scos.R;

import es.source.code.model.Food;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HotFragment extends ListFragment {
	private ArrayList<Food> foods;
	private ListView listView;
	private FoodListAdapter adapter;
	
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View hotView = inflater.inflate(R.layout.food_hot, container,false);  
        
        if(listView == null){
			listView = (ListView) hotView.findViewById(android.R.id.list);
        }
        
        return hotView;      
    }  
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);  
        
		initData();
		
		if(adapter == null){
			adapter = new FoodListAdapter(foods, getActivity().getApplicationContext());
		}
		setListAdapter(adapter);
		
	}
	
	private void initData(){
		foods = MainScreen.getHotFoods(); 
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
}
