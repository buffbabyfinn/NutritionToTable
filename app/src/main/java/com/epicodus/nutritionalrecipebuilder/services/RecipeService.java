package com.epicodus.nutritionalrecipebuilder.services;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 5/16/16.
 */
public class RecipeService {

    public static void findRecipes(String searchTerms, Callback callback) {
        String RECIPE_CONSUMER_KEY = Constants.RECIPE_CONSUMER_KEY;
        String RECIPE_APP_ID = Constants.RECIPE_APP_ID;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.RECIPE_BASE_URL).newBuilder();
        String urlFront = urlBuilder.toString();
        String urlComplete = urlFront + Constants.RECIPE_ID_PARAMETER + RECIPE_APP_ID + Constants.RECIPE_KEY_PARAMETER + RECIPE_CONSUMER_KEY + Constants.RECIPE_SEARCH_PARAMETER + searchTerms;

        Log.d("URL", urlComplete);

        Request request = new Request.Builder()
                .url(urlComplete)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipe> processResults(Response response) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.d("JSONdATA", jsonData);
            if (response.isSuccessful()) {
                JSONObject json = new JSONObject(jsonData);
                JSONArray matchesJSON = json.getJSONArray("matches");

                for (int i = 0; i < matchesJSON.length(); i++) {
                    JSONObject recipesJSON = matchesJSON.getJSONObject(i);
                    String recipeName = recipesJSON.getString("recipeName");
                    String sourceDisplayName = recipesJSON.getString("sourceDisplayName");
                    double rating = recipesJSON.getDouble("rating");
                    String smallImageUrl = recipesJSON.getJSONArray("smallImageUrls").toString();

                    ArrayList<String> ingredients = new ArrayList<>();
                    JSONArray ingredientsJSON = recipesJSON.getJSONArray("ingredients");
                    for (int y = 0; y < ingredientsJSON.length(); y++) {
                        String ingredientName = ingredientsJSON.get(y).toString();
                        ingredients.add(ingredientName);
                    }

                    String attributionUrl = json.getJSONObject("attribution").getString("url");
                    String attributionText = json.getJSONObject("attribution").getString("text");
                    String attributionImageUrl = json.getJSONObject("attribution").getString("logo");

                    Recipe recipe = new Recipe(recipeName, rating, sourceDisplayName, smallImageUrl, ingredients, attributionUrl, attributionText, attributionImageUrl);
                    recipes.add(recipe);
                }
                Log.d("made it", "log recipes" + recipes.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }

}
