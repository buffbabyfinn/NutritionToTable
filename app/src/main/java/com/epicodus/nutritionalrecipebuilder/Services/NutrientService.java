package com.epicodus.nutritionalrecipebuilder.services;

import com.epicodus.nutritionalrecipebuilder.Constants;

import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * Created by Guest on 4/29/16.
 */
public class NutrientService {
    public static void findFoods(String nutrient1, String nutrient2, String nutrient3, Callback callback) {
        String CONSUMER_KEY = Constants.NUTRITION_CONSUMER_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();
    }

}
