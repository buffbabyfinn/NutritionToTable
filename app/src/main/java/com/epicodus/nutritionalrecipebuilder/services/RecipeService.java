package com.epicodus.nutritionalrecipebuilder.services;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;

import java.util.ArrayList;

import okhttp3.Callback;

/**
 * Created by Guest on 5/16/16.
 */
public class RecipeService {

    //public static String processIngredients(){}

    public static void findRecipes(ArrayList<Recipe> ingredientSearch, Callback callback) {
        String RECIPE_CONSUMER_KEY = Constants.RECIPE_CONSUMER_KEY;
        String RECIPE_APP_ID = Constants.RECIPE_APP_ID;
    }

}
