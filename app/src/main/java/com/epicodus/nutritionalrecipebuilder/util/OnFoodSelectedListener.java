package com.epicodus.nutritionalrecipebuilder.util;

import com.epicodus.nutritionalrecipebuilder.models.Food;

import java.util.ArrayList;

/**
 * Created by Guest on 5/17/16.
 */
public interface OnFoodSelectedListener {
    public void OnFoodSelected(Integer position, ArrayList<Food> foods);
}
