package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NutritionPickActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.protein_203) CheckBox mProtein;
    @Bind(R.id.water_255) CheckBox mWater;
    @Bind(R.id.fat_207) CheckBox mFat;
    @Bind(R.id.unsatFat_646) CheckBox mUnsatFat;
    @Bind(R.id.c_401) CheckBox mVitC;
    @Bind(R.id.calcium_301) CheckBox mCalcium;
    @Bind(R.id.carbs_205) CheckBox mCarbs;
    @Bind(R.id.fiber_291) CheckBox mFiber;
    @Bind(R.id.sugar_269) CheckBox mSugar;
    @Bind(R.id.iron_303) CheckBox mIron;
    @Bind(R.id.potassium_306) CheckBox mPotassium;
    @Bind(R.id.cholesterol_601) CheckBox mCholesterol;
    @Bind(R.id.findFoodsButton) Button mFindFoods;

    private Firebase mSearchedNutrientRef;
    private ValueEventListener mSearchedNutrientRefListener;
    private String mNutrientCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_pick);
        ButterKnife.bind(this);

        mFindFoods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findFoodsButton:
                if(mProtein.isChecked() == true) {
                    mNutrientCode = "203";
                }
                if(mWater.isChecked() == true) {
                    mNutrientCode = "255";
                }
                if(mFat.isChecked() == true) {
                    mNutrientCode = "204";
                }
                if(mUnsatFat.isChecked() == true) {
                    mNutrientCode = "646";
                }
                if(mVitC.isChecked() == true) {
                    mNutrientCode = "401";
                }
                if(mCalcium.isChecked() == true) {
                    mNutrientCode = "301";
                }
                if(mCarbs.isChecked() == true) {
                    mNutrientCode = "205";
                }
                if(mFiber.isChecked() == true) {
                    mNutrientCode = "291";
                }
                if(mSugar.isChecked() == true) {
                    mNutrientCode = "269";
                }
                if(mIron.isChecked() == true) {
                    mNutrientCode = "303";
                }
                if(mPotassium.isChecked() == true) {
                    mNutrientCode = "306";
                }
                if(mCholesterol.isChecked() == true) {
                    mNutrientCode = "601";
                }


                Intent intent = new Intent(NutritionPickActivity.this, FoodResultsActivity.class);
                intent.putExtra("nutrient", mNutrientCode.toString());
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
