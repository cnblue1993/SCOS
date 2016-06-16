package es.source.code.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import android.R.integer;

public class Form {
	private ArrayList<Food> foods;
	private int foodCount;
	private int foodSum;
	private int state; //0-临时	1-代付	2-已付
	
	public Form(){
		foods = new ArrayList<Food>();
		foodCount = 0;
		foodSum = 0;
		state = 0;
	}
	public Form(int state, int count, int sum, ArrayList<Food> foods){
		this.state = state;
		this.foodCount = count;
		this.foodSum = sum;
		//this.foods = (ArrayList<Food>) foods.clone();
		this.foods = foods;
	}
	
	public ArrayList<Food> getFoods() {
		return foods;
	}
	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}
	public int getFoodCount() {
		setFoodCount(foods.size());
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public int getFoodSum() {
		Iterator<Food> iterator = foods.iterator(); 
		int sum = 0;
		while(iterator.hasNext()){  
		    Food e = iterator.next(); 
		    sum += Integer.parseInt(e.getPrice());
		}
		setFoodSum(sum);
		return foodSum;
	}
	public void setFoodSum(int foodSum) {
		this.foodSum = foodSum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	
	
}
