package com.epicodus.nutritionalrecipebuilder.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.firebase.client.Firebase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFoodDetailFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.foodNameTextView)
    TextView mFoodView;
    @Bind(R.id.foodMeasureTextView) TextView mMeasureView;
    @Bind(R.id.foodNutrientsTextView) TextView mNutrientsView;
    @Bind(R.id.saveRecipeFoodButton)
    Button mSaveFoodButton;

    private Food mFood;
    private Integer mPosition;
    private ArrayList<Food> mFoods;
    private SharedPreferences mRecipeSharedPreferences;
    private ArrayList<String> mRecipeList = new ArrayList<>();

    public static FoodDetailFragment newInstance(ArrayList<Food> foods, Integer position) {
        FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_FOODS, Parcels.wrap(foods));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        foodDetailFragment.setArguments(args);
        return foodDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoods = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_FOODS));
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mFood = mFoods.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        ButterKnife.bind(this, view);
        mSaveFoodButton.setOnClickListener(this);
        mFoodView.setText(mFood.getName());
        mMeasureView.setText(mFood.getMeasure());
        mNutrientsView.setText(android.text.TextUtils.join(", ", mFood.getNutrient()));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveRecipeFoodButton:
                mRecipeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String ingredientName = mFood.getName();
                String[] ingredient = ingredientName.split(",");
                mRecipeList.add(ingredient[0]);
                mRecipeList.add(ingredient[1]);
                mRecipeList.add(ingredient[2]);
                String ingredients = "";
                for (String s : mRecipeList) {
                    ingredients += s + " ";
                }
                addIngredientToSharedPreferences(ingredients);
                Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show();
        }
    }

}
