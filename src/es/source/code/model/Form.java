package es.source.code.model;

import java.util.ArrayList;
import java.util.Date;

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
		this.foods = (ArrayList<Food>) foods.clone();
	}
	
	public ArrayList<Food> getFoods() {
		return foods;
	}
	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public int getFoodSum() {
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
