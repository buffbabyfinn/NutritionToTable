package com.epicodus.nutritionalrecipebuilder.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.ui.FoodDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 5/3/16.
 */
public class FoodPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Food> mFoods;

    public FoodPagerAdapter(FragmentManager fm, ArrayList<Food> foods) {
        super(fm);
        mFoods = foods;
    }

    @Override
    public Fragment getItem(int position) {
        return FoodDetailFragment.newInstance(mFoods.get(position));
    }

    @Override
    public int getCount() {
        return mFoods.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFoods.get(position).getName();
    }
}
