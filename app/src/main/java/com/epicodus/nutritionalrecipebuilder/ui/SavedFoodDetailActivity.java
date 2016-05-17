package com.epicodus.nutritionalrecipebuilder.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.adapters.FoodPagerAdapter;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.util.ScaleAndFadePageTransformer;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFoodDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private FoodPagerAdapter adapterViewPager;
    ArrayList<Food> mFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_food_detail);
        ButterKnife.bind(this);
        mFoods = Parcels.unwrap(getIntent().getParcelableExtra("foods"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new FoodPagerAdapter(getSupportFragmentManager(), mFoods);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
        mViewPager.setPageTransformer(true, new ScaleAndFadePageTransformer());
    }
}
