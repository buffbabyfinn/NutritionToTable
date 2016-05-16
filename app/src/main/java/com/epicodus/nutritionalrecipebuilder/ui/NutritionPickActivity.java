package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

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
    @Bind(R.id.phosph_305) CheckBox mPhosphorus;
    @Bind(R.id.alcohol_221) CheckBox mAlcohol;
    @Bind(R.id.caffeine) CheckBox mCaffeine;
    @Bind(R.id.magnesium) CheckBox mMagnesium;
    @Bind(R.id.zinc) CheckBox mZinc;
    @Bind(R.id.manganese) CheckBox mManganese;
    @Bind(R.id.vitA) CheckBox mVitA;
    @Bind(R.id.betaCarotene) CheckBox mBetaCartoene;
    @Bind(R.id.vitE) CheckBox mVitE;
    @Bind(R.id.vitD) CheckBox mVitD;
    @Bind(R.id.folicAcid) CheckBox mFolicAcid;
    @Bind(R.id.vitB) CheckBox mVitB;
    @Bind(R.id.niacin) CheckBox mNiacin;
    @Bind(R.id.findFoodsButton) Button mFindFoods;

    private String mNutrientCode;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

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
                if (mPhosphorus.isChecked() == true) {
                    mNutrientCode = "305";
                }
                if (mAlcohol.isChecked() == true) {
                    mNutrientCode = "221";
                }
                if (mCaffeine.isChecked() == true) {
                    mNutrientCode = "262";
                }
                if (mMagnesium.isChecked() == true) {
                    mNutrientCode = "304";
                }
                if (mZinc.isChecked() == true) {
                    mNutrientCode = "309";
                }
                if (mManganese.isChecked() == true) {
                    mNutrientCode = "315";
                }
                if (mVitA.isChecked() == true) {
                    mNutrientCode = "318";
                }
                if (mBetaCartoene.isChecked() == true) {
                    mNutrientCode = "321";
                }
                if (mVitE.isChecked() == true) {
                    mNutrientCode = "323";
                }
                if (mVitD.isChecked() == true) {
                    mNutrientCode = "324";
                }
                if (mFolicAcid.isChecked() == true) {
                    mNutrientCode = "431";
                }
                if (mVitB.isChecked() == true) {
                    mNutrientCode = "418";
                }
                if (mNiacin.isChecked() == true) {
                    mNutrientCode = "406";
                }

                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                mSharedPreferencesEditor = mSharedPreferences.edit();
                addToSharedPreferences(mNutrientCode);

                Intent intent = new Intent(NutritionPickActivity.this, FoodListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void addToSharedPreferences(String nutrient) {
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_FOODS_KEY, nutrient).apply();
    }
}
