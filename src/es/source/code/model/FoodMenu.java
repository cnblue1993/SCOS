package es.source.code.model;

import java.util.ArrayList;

import com.ustc.scos.R;

public class FoodMenu {
	private ArrayList<Food> coldFoods;
	private ArrayList<Food> hotFoods;
	private ArrayList<Food> seaFoods;
	private ArrayList<Food> drinkFoods;
	
	public FoodMenu(){
		init();
	}
	
	private void init(){
		if(coldFoods == null)
			coldFoods = new ArrayList<Food>();
		if(hotFoods == null)
			hotFoods = new ArrayList<Food>();
		if(seaFoods == null)
			seaFoods = new ArrayList<Food>();
		if(drinkFoods == null)
			drinkFoods = new ArrayList<Food>();
		
		
		//冷菜
		coldFoods.add(new Food(0, R.drawable.cold0, "凉拌土豆丝", "8"));
		coldFoods.add(new Food(0, R.drawable.cold1, "虫草花拌耳丝", "26"));
		coldFoods.add(new Food(0, R.drawable.cold2, "凉拌银条", "10"));
		coldFoods.add(new Food(0, R.drawable.cold3, "香椿苗拌豆腐丝", "12"));
		coldFoods.add(new Food(0, R.drawable.cold4, "黄金泡菜", "8"));
		coldFoods.add(new Food(0, R.drawable.cold5, "沙拉汁凉拌黄瓜卷", "10"));
		coldFoods.add(new Food(0, R.drawable.cold6, "凉拌穿心莲", "6"));
		coldFoods.add(new Food(0, R.drawable.cold7, "凉拌藕片", "8"));
		coldFoods.add(new Food(0, R.drawable.cold8, "凉拌海带丝", "8"));
		coldFoods.add(new Food(0, R.drawable.cold9, "酸辣凉粉", "10"));
		
		//热菜
		hotFoods.add(new Food(1, R.drawable.hot0, "鱼香肉丝", "18"));
		hotFoods.add(new Food(1, R.drawable.hot1, "土豆炖牛肉", "28"));
		hotFoods.add(new Food(1, R.drawable.hot2, "宫保鸡丁", "26"));
		hotFoods.add(new Food(1, R.drawable.hot3, "红烧肉", "30"));
		hotFoods.add(new Food(1, R.drawable.hot4, "蒜香排骨", "38"));
		hotFoods.add(new Food(1, R.drawable.hot5, "酱焖豆腐", "16"));
		hotFoods.add(new Food(1, R.drawable.hot6, "青笋炒木耳", "18"));
		hotFoods.add(new Food(1, R.drawable.hot7, "蚕豆炒雪菜", "20"));
		hotFoods.add(new Food(1, R.drawable.hot8, "干煸大头菜", "12"));
		
		//海鲜
		seaFoods.add(new Food(2, R.drawable.sea0, "芝麻椒盐小河鱼", "30"));
		seaFoods.add(new Food(2, R.drawable.sea1, "酸菜鱼", "38"));
		seaFoods.add(new Food(2, R.drawable.sea2, "香辣花菇蟹", "48"));
		seaFoods.add(new Food(2, R.drawable.sea3, "爆炒螺丝肉", "28"));
		seaFoods.add(new Food(2, R.drawable.sea4, "油爆大虾", "48"));
		seaFoods.add(new Food(2, R.drawable.sea5, "什锦海鲜烩", "38"));
		seaFoods.add(new Food(2, R.drawable.sea6, "鲍鱼海鲜乌鸡汤", "52"));
		seaFoods.add(new Food(2, R.drawable.sea7, "海鲜麻辣香锅", "46"));
		seaFoods.add(new Food(2, R.drawable.sea8, "海鲜双吃", "30"));
		
		//酒水
		drinkFoods.add(new Food(3, R.drawable.drink0, "可乐", "10"));
		drinkFoods.add(new Food(3, R.drawable.drink1, "雪碧", "10"));
		drinkFoods.add(new Food(3, R.drawable.drink2, "啤酒", "15"));
		drinkFoods.add(new Food(3, R.drawable.drink3, "豆奶", "15"));
		drinkFoods.add(new Food(3, R.drawable.drink4, "果粒橙", "10"));
		drinkFoods.add(new Food(3, R.drawable.drink5, "红酒", "100"));
		drinkFoods.add(new Food(3, R.drawable.drink6, "白酒", "500"));
		drinkFoods.add(new Food(3, R.drawable.drink7, "果汁", "10"));

	}

	public ArrayList<Food> getColdFoods() {
		return coldFoods;
	}

	public ArrayList<Food> getHotFoods() {
		return hotFoods;
	}

	public ArrayList<Food> getSeaFoods() {
		return seaFoods;
	}

	public ArrayList<Food> getDrinkFoods() {
		return drinkFoods;
	}
	

	

}
