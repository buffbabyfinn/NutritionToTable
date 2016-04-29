package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.epicodus.nutritionalrecipebuilder.R;

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

    private int proteinCode;
    private int waterCode;
    private int fatCode;
    private int unsatFatCode;
    private int vitCCode;
    private int calciumCode;
    private int carbCode;
    private int fiberCode;
    private int sugarCode;
    private int ironCode;
    private int potassiumCode;
    private int cholesterolCode;

    private Integer mNutrient1;
    private Integer mNutrient2;
    private Integer mNutrient3;

    private ArrayList<Integer> mNutrientList = new ArrayList<>();


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
                    proteinCode = 203;
                }
                if(mWater.isChecked() == true) {
                    waterCode = 255;
                }
                if(mFat.isChecked() == true) {
                    fatCode = 207;
                }
                if(mUnsatFat.isChecked() == true) {
                    unsatFatCode = 646;
                }
                if(mVitC.isChecked() == true) {
                    vitCCode = 401;
                }
                if(mCalcium.isChecked() == true) {
                    calciumCode = 301;
                }
                if(mCarbs.isChecked() == true) {
                    carbCode = 205;
                }
                if(mFiber.isChecked() == true) {
                    fiberCode = 291;
                }
                if(mSugar.isChecked() == true) {
                    sugarCode = 269;
                }
                if(mIron.isChecked() == true) {
                    ironCode = 291;
                }
                if(mPotassium.isChecked() == true) {
                    potassiumCode = 306;
                }
                if(mCholesterol.isChecked() == true) {
                    cholesterolCode = 601;
                }

                if(proteinCode > 0) {
                    mNutrientList.add(proteinCode);
                }
                if(waterCode > 0) {
                    mNutrientList.add(waterCode);
                }
                if(fatCode > 0) {
                    mNutrientList.add(fatCode);
                }
                if(unsatFatCode > 0) {
                    mNutrientList.add(unsatFatCode);
                }
                if(vitCCode > 0) {
                    mNutrientList.add(vitCCode);
                }
                if(calciumCode > 0) {
                    mNutrientList.add(calciumCode);
                }
                if(carbCode > 0) {
                    mNutrientList.add(carbCode);
                }
                if(fiberCode > 0) {
                    mNutrientList.add(fiberCode);
                }
                if(sugarCode > 0) {
                    mNutrientList.add(sugarCode);
                }
                if(ironCode > 0) {
                    mNutrientList.add(ironCode);
                }
                if(potassiumCode > 0) {
                    mNutrientList.add(potassiumCode);
                }
                if(cholesterolCode > 0) {
                    mNutrientList.add(cholesterolCode);
                }

                mNutrient1 = mNutrientList.get(0);
                mNutrient2 = mNutrientList.get(1);
                mNutrient3 = mNutrientList.get(2);

                Intent intent = new Intent(NutritionPickActivity.this, FoodResultsActivity.class);
                intent.putExtra("nutrient1", mNutrient1);
                intent.putExtra("nutrient2", mNutrient2);
                intent.putExtra("nutrient3", mNutrient3);
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
