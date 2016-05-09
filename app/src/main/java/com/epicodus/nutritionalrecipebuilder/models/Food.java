package com.epicodus.nutritionalrecipebuilder.models;

import java.util.ArrayList;
import java.util.List;

import org.parceler.Parcel;

/**
 * Created by Guest on 4/29/16.
 */
@Parcel
public class Food {
    private String name;
    private String measure;
    private List<String> nutrient = new ArrayList<>();
    private List<String> unit = new ArrayList<>();
    private String pushId;

    public Food() {}

    public Food(String name, String measure, List<String> nutrient, List<String> unit) {
        this.name = name;
        this.measure = measure;
        this.nutrient = nutrient;
        this.unit = unit;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getPushId() {
        return pushId;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public List<String> getNutrient() {
        return nutrient;
    }

    public List<String> getUnit() {
        return unit;
    }
}
