package es.source.code.activity;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAdapter extends FragmentPagerAdapter {
	private final int PAGER_COUNT = 4;
    private ColdFragment myFragment1 = null;
    private HotFragment myFragment2 = null;
    private SeaFragment myFragment3 = null;
    private DrinkFragment myFragment4 = null;


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new ColdFragment();
        myFragment2 = new HotFragment();
        myFragment3 = new SeaFragment();
        myFragment4 = new DrinkFragment();
    }


    @Override
    public int getCount() {
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
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case FoodView.PAGE_ONE:
                fragment = myFragment1;
                break;
            case FoodView.PAGE_TWO:
                fragment = myFragment2;
                break;
            case FoodView.PAGE_THREE:
                fragment = myFragment3;
                break;
            case FoodView.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

}
