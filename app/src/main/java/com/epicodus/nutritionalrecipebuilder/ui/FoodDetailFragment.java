package com.epicodus.nutritionalrecipebuilder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends Fragment {
    @Bind(R.id.foodNameTextView) TextView mFoodView;
    @Bind(R.id.foodMeasureTextView) TextView mMeasureView;
    @Bind(R.id.foodNutrientsTextView) TextView mNutrientsView;
    @Bind(R.id.saveFoodButton) Button mSaveFoodButton;

    private Food mFood;

    public static FoodDetailFragment newInstance(Food food) {
        FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("food", Parcels.wrap(food));
        foodDetailFragment.setArguments(args);
        return foodDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFood = Parcels.unwrap(getArguments().getParcelable("food"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        ButterKnife.bind(this, view);
        mFoodView.setText(mFood.getName());
        mMeasureView.setText(mFood.getMeasure());
        mNutrientsView.setText(android.text.TextUtils.join(", ", mFood.getNutrient()));
        return view;
    }

}
