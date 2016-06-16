package es.source.code.activity;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.style.UpdateAppearance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ustc.scos.R;

import es.source.code.activity.UnorderFragment.submitListener;
import es.source.code.model.Food;
import es.source.code.model.Form;

public class OrderedFrgment extends ListFragment {
	private ArrayList<Food> foods;
	private ListView listView;
	private FoodOrderListAdapter adapter;
	
	private static Form unpayForm;
	private static ArrayList<Form> forms;
	
	private Button btn_pay;
	private static TextView ordered_message;
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View unorderView = inflater.inflate(R.layout.food_ordered, container,false);  
        
        if(listView == null){
			listView = (ListView) unorderView.findViewById(android.R.id.list);
        }
        
        btn_pay = (Button) unorderView.findViewById(R.id.ordered_btn);
        ordered_message = (TextView) unorderView.findViewById(R.id.ordered_message);
        
        ordered_message.setText("总计："+unpayForm.getFoodCount()+" 道菜 "+"，一共："+unpayForm.getFoodSum()+"元");
        btn_pay.setOnClickListener(new payListener());
        
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
		unpayForm = MainScreen.getUnpayForm();
		foods = unpayForm.getFoods();

		forms = MainScreen.getForm();
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
	class payListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			forms.add(new Form(2, unpayForm.getFoodCount(), unpayForm.getFoodSum(), unpayForm.getFoods()));
			
			unpayForm.setFoodCount(0);
			unpayForm.setFoods(new ArrayList<Food>());
			unpayForm.setFoodSum(0);
			
			//未及时刷新数据
			setListAdapter(adapter);
		}
	}
	public static void updata(){
		ordered_message.setText("总计："+unpayForm.getFoodCount()+" 道菜 "+"，一共："+unpayForm.getFoodSum()+"元");
	}
}
