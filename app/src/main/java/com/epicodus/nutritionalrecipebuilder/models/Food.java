package com.epicodus.nutritionalrecipebuilder.models;

import java.util.ArrayList;
import org.parceler.Parcel;

/**
 * Created by Guest on 4/29/16.
 */
@Parcel
public class Food {
    private String name;
    private String measure;
    private ArrayList<String> nutrient = new ArrayList<>();
    private ArrayList<String> unit = new ArrayList<>();

    public Food() {}

    public Food(String name, String measure, ArrayList<String> nutrient, ArrayList<String> unit) {
        this.name = name;
        this.measure = measure;
        this.nutrient = nutrient;
        this.unit = unit;
    }


    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public ArrayList<String> getNutrient() {
        return nutrient;
    }

    public ArrayList<String> getUnit() {
        return unit;
    }
}
