package com.epicodus.nutritionalrecipebuilder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.ui.SavedFoodDetailActivity;
import com.epicodus.nutritionalrecipebuilder.util.ItemTouchHelperViewHolder;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/17/16.
 */
public class SavedFoodViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    @Bind(R.id.foodName)
    TextView mNameView;
    @Bind(R.id.foodMeasure) TextView mMeasureView;
    @Bind(R.id.foodNutrients) TextView mNutrientView;
    private Context mContext;
    private ArrayList<Food> mFoods = new ArrayList<>();

    public SavedFoodViewHolder(View itemView, ArrayList<Food> foods) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mFoods = foods;
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, SavedFoodDetailActivity.class);
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

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}