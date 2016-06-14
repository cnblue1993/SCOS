package es.source.code.model;

import android.R.integer;

public class Food {
	private int type;	//	0-cold	1-hot	2-sea	3-drink
	private String name;
	private String remark;
	private String price;
	private int count;
	private int img;
	private boolean oredered;
	private int state; //0-未点	1-已点未下单	2-已点已下单 3-已结账菜
	
	public Food(int food_type,int food_img, String food_name, String food_price) {  
        super();  
        this.type = food_type;
        this.img = food_img;  
        this.name = food_name;  
        this.price = food_price; 
        this.count = 0;
        this.oredered = false;
    }  
  
    public Food() {  
        super();  
    }  
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}

	public boolean isOredered() {
		return oredered;
	}

	public void setOredered(boolean oredered) {
		this.oredered = oredered;
	}
	
	
	
}
