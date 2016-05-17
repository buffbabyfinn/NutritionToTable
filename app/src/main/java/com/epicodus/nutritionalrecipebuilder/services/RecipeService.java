package com.epicodus.nutritionalrecipebuilder.services;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;

import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.HttpUrl;

/**
 * Created by Guest on 5/16/16.
 */
public class RecipeService {
    //public static String processIngredients(){}

    public static void findRecipes(String searchTerms, Callback callback) {
        SharedPreferences mPrefs;
        String RECIPE_CONSUMER_KEY = Constants.RECIPE_CONSUMER_KEY;
        String RECIPE_APP_ID = Constants.RECIPE_APP_ID;

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.RECIPE_BASE_URL).newBuilder();
        String urlFront = urlBuilder.toString();
        //String urlComplete = urlFront +
    }

}
