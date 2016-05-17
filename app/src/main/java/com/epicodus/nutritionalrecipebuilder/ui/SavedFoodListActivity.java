package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.util.OnFoodSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class SavedFoodListActivity extends AppCompatActivity implements OnFoodSelectedListener {
    private Integer mPosition;
    private ArrayList<Food> mFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mFoods = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_FOODS));
                if (mPosition != null && mFoods != null) {
                    Intent intent = new Intent(this, FoodDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition.toString());
                    intent.putExtra(Constants.EXTRA_KEY_FOODS, Parcels.wrap(mFoods));
                    startActivity(intent);
                }
            }
        }
        setContentView(R.layout.activity_saved_food_list);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mPosition != null && mFoods != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, Integer.valueOf(mPosition));
            outState.putParcelable(Constants.EXTRA_KEY_FOODS, Parcels.wrap(mFoods));
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void OnFoodSelected(Integer position, ArrayList<Food> foods) {
        mPosition = position;
        mFoods = foods;
    }
}
