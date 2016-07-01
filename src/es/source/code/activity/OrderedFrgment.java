package es.source.code.activity;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ustc.scos.R;

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
	private ProgressBar pgBar;
	private TextView tv_paying;
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View unorderView = inflater.inflate(R.layout.food_ordered, container,false);  
        
        if(listView == null){
			listView = (ListView) unorderView.findViewById(android.R.id.list);
        }
        
        btn_pay = (Button) unorderView.findViewById(R.id.ordered_btn);
        ordered_message = (TextView) unorderView.findViewById(R.id.ordered_message);
        pgBar = (ProgressBar) unorderView.findViewById(R.id.pgbar);
        tv_paying = (TextView) unorderView.findViewById(R.id.tv_paying);
        
        
        ordered_message.setText("总计："+unpayForm.getFoodCount()+" 道菜 "+"，一共："+unpayForm.getFoodSum()+"元");
        btn_pay.setClickable(true);
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
			btn_pay.setClickable(false);
			btn_pay.setBackgroundColor(Color.parseColor("#999999"));
			pgBar.setVisibility(View.VISIBLE);
			tv_paying.setVisibility(View.VISIBLE);
			
			MyAsyncTask myTask = new MyAsyncTask(tv_paying,pgBar,unpayForm.getFoodSum());  
            myTask.execute(1000);
			
			
			forms.add(new Form(2, unpayForm.getFoodCount(), unpayForm.getFoodSum(), unpayForm.getFoods()));
			
			unpayForm.setFoodCount(0);
			unpayForm.setFoods(new ArrayList<Food>());
			unpayForm.setFoodSum(0);
			
			//Toast.makeText(this, "您好,老顾客,本次你可享受7折优惠", Toast.LENGTH_LONG).show();
			//未及时刷新数据
			//setListAdapter(adapter);
		}
	}
	public static void updata(){
		ordered_message.setText("总计："+unpayForm.getFoodCount()+" 道菜 "+"，一共："+unpayForm.getFoodSum()+"元");
	}
	
	class MyAsyncTask extends AsyncTask<Integer,Integer,String>  
	{  
	    private TextView txt;  
	    private ProgressBar pgbar; 
	    private int foodSum;
	  
	    public MyAsyncTask(TextView txt,ProgressBar pgbar, int foodsum)  
	    {  
	        super();  
	        this.txt = txt;  
	        this.pgbar = pgbar;  
	        this.foodSum = foodsum;
	    }     
	    @Override  
	    protected String doInBackground(Integer... params) {  
	        DelayOperator dop = new DelayOperator();  
	        int i = 0;  
	        for (i = 20;i <= 100;i+=16)  
	        {  
	            dop.delay();  
	            publishProgress(i);  
	        }  
	        return  i + params[0].intValue() + "";  
	    }  
	  
	    //该方法运行在UI线程中,可对UI控件进行设置  
	    @Override  
	    protected void onPreExecute() {  
	        txt.setText("正在结账中。。。");  
	    }  
	  
	  
	    //在doBackground方法中,每次调用publishProgress方法都会触发该方法  
	    //运行在UI线程中,可对UI控件进行操作  
	  
	    @Override  
	    protected void onProgressUpdate(Integer... values) {  
	        int value = values[0];  
	        pgbar.setProgress(value);  
	        if(value == 100){
	        	pgBar.setVisibility(View.GONE);
				tv_paying.setVisibility(View.GONE);
				
				Toast.makeText(getActivity(), "一共支付"+foodSum+"元，增加"+foodSum+"积分", Toast.LENGTH_LONG).show();
	        }
	    }  
	}
	
	class DelayOperator {  
	    //延时操作,用来模拟下载  
	    public void delay()  
	    {  
	        try {  
	            Thread.sleep(1000);  
	        }catch (InterruptedException e){  
	            e.printStackTrace();;  
	        }  
	    }  
	}
}
