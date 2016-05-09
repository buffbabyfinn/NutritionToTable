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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.foodNameTextView) TextView mFoodView;
    @Bind(R.id.foodMeasureTextView) TextView mMeasureView;
    @Bind(R.id.foodNutrientsTextView) TextView mNutrientsView;
    @Bind(R.id.saveFoodButton) Button mSaveFoodButton;
    private SharedPreferences mSharedPreferences;

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
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
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
            case R.id.saveFoodButton:
                String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
                Firebase userFoodFirebaseRef = new Firebase(Constants.FIREBASE_URL_FOODS).child(userUid);
                Firebase pushRef = userFoodFirebaseRef.push();
                String foodPushId = pushRef.getKey();
                mFood.setPushId(foodPushId);
                pushRef.setValue(mFood);
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
        }
    }

}
