package com.epicodus.nutritionalrecipebuilder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.ui.FoodDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/6/16.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.foodName) TextView mNameView;
    @Bind(R.id.foodMeasure) TextView mMeasureView;
    @Bind(R.id.foodNutrients) TextView mNutrientView;
    private Context mContext;
    private ArrayList<Food> mFoods = new ArrayList<>();

    public FoodViewHolder(View itemView, ArrayList<Food> foods) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mFoods = foods;
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, FoodDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("foods", Parcels.wrap(mFoods));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindFood(Food food) {
        mNameView.setText(food.getName());
        mMeasureView.setText(food.getMeasure());
        mNutrientView.setText(food.getNutrient().get(0));
    }
}

