package es.source.code.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class OrderFragmentAdapter extends FragmentPagerAdapter {

	private final int PAGER_COUNT = 2;
	private OrderedFrgment orderedFrgment = null;
	private UnorderFragment unorderFragment = null;
	
	public OrderFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		orderedFrgment = new OrderedFrgment();
		unorderFragment = new UnorderFragment();
	}

	@Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case FoodOrderView.PAGE_ONE:
                fragment = unorderFragment;
                break;
            case FoodOrderView.PAGE_TWO:
                fragment = orderedFrgment;
                break;

        }
        return fragment;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGER_COUNT;
	}
	@Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
        //notifyDataSetChanged();
    }
    
    public UnorderFragment getUnorderFragment(){
    	return unorderFragment;
    }
    public OrderedFrgment getOrderFragment(){
		return orderedFrgment;
	}

}
