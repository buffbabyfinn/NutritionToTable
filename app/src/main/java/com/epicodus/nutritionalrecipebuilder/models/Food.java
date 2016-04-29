package com.epicodus.nutritionalrecipebuilder.models;

import java.util.ArrayList;

/**
 * Created by Guest on 4/29/16.
 */
public class Food {
    private String mName;
    private String mMeasure;
    private ArrayList<String> mNutrient = new ArrayList<>();
    private ArrayList<String> mUnit = new ArrayList<>();

    public Food(String mName, String mMeasure, ArrayList<String> mNutrient, ArrayList<String> mUnit) {
        this.mName = mName;
        this.mMeasure = mMeasure;
        this.mNutrient = mNutrient;
        this.mUnit = mUnit;
    }


    public String getName() {
        return mName;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public ArrayList<String> getNutrient() {
        return mNutrient;
    }

    public ArrayList<String> getUnit() {
        return mUnit;
    }
}
