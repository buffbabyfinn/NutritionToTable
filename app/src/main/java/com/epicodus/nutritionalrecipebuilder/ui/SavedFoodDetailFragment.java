package com.epicodus.nutritionalrecipebuilder.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
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


    @Bind(R.id.foodNameTextView) TextView mFoodView;
    @Bind(R.id.foodMeasureTextView) TextView mMeasureView;
    @Bind(R.id.foodNutrientsTextView) TextView mNutrientsView;
    @Bind(R.id.saveRecipeFoodButton) Button mSaveFoodButton;
    @Bind(R.id.clearIngredientsButton) Button mClearButton;

    private Food mFood;
    private Integer mPosition;
    private ArrayList<Food> mFoods;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mRecipePrefEditor;
    private String mIngredients;
    private String mFormattedIngredients;
    private ArrayList<String> mRecipeList = new ArrayList<>();

    public static SavedFoodDetailFragment newInstance(ArrayList<Food> foods, Integer position) {
        SavedFoodDetailFragment savedFoodDetailFragment = new SavedFoodDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_FOODS, Parcels.wrap(foods));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        savedFoodDetailFragment.setArguments(args);
        return savedFoodDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoods = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_FOODS));
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mFood = mFoods.get(mPosition);
        mRecipePrefEditor = mSharedPreferences.edit();
        mIngredients = mSharedPreferences.getString(Constants.PREFERENCES_INGREDIENT_LIST, "");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_food_detail, container, false);
        ButterKnife.bind(this, view);
        mSaveFoodButton.setOnClickListener(this);
        mClearButton.setOnClickListener(this);
        mFoodView.setText(mFood.getName());
        mMeasureView.setText(mFood.getMeasure());
        mNutrientsView.setText(android.text.TextUtils.join(", ", mFood.getNutrient()));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveRecipeFoodButton:
                formatIngredients();
                addIngredientToSharedPreferences(mFormattedIngredients);
                Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearIngredientsButton:
                removeFromSharedPreferences();
                break;
            default:
                break;
        }
    }

    public void removeFromSharedPreferences() {
        mRecipePrefEditor.remove(Constants.PREFERENCES_INGREDIENT_LIST).commit();
        Toast.makeText(getContext(), "Cleared!", Toast.LENGTH_SHORT).show();
    }

    public void formatIngredients() {
        String spaces = "[ ]";
        String ingredientName = mFood.getName();
        String[] ingredient = ingredientName.split(",");
        ingredient[0] = " " + ingredient[0];

        mRecipeList.add(ingredient[0]);
        if (ingredient.length >= 2) {
            mRecipeList.add(ingredient[1]);
        }
        if (ingredient.length >= 3) {
            mRecipeList.add(ingredient[2]);
        }

        for (String s : mRecipeList) {
            mIngredients +=  s ;
        }
        mFormattedIngredients = mIngredients.replaceAll(spaces, "+");
    }

}
