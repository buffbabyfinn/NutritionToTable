package com.epicodus.nutritionalrecipebuilder.services;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
    //public static String processIngredients(){}

    public static void findRecipes(String searchTerms, Callback callback) {
        String RECIPE_CONSUMER_KEY = Constants.RECIPE_CONSUMER_KEY;
        String RECIPE_APP_ID = Constants.RECIPE_APP_ID;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.RECIPE_BASE_URL).newBuilder();
        String urlFront = urlBuilder.toString();
        String urlComplete = urlFront + Constants.RECIPE_ID_PARAMETER + RECIPE_APP_ID + Constants.RECIPE_KEY_PARAMETER + RECIPE_CONSUMER_KEY + "&" + searchTerms;

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
            if (response.isSuccessful()) {
                JSONObject json = new JSONObject(jsonData);
                JSONArray matchesJSON = json.getJSONArray("matches");

                for (int i = 0; i < matchesJSON.length(); i++) {
                    JSONObject recipesJSON = matchesJSON.getJSONObject(i);
                    String recipeName = recipesJSON.getString("recipeName");
                    String sourceDisplayName = recipesJSON.getString("sourceDisplayName");
                    double rating = recipesJSON.getDouble("rating");
                    String smallImageUrl = recipesJSON.getJSONArray("smallImageUrls").toString();

                    JSONObject flavorsJSON = recipesJSON.getJSONObject("flavors");
                    double sweet = flavorsJSON.getDouble("sweet");
                    double piquant = flavorsJSON.getDouble("piquant");
                    double sour = flavorsJSON.getDouble("sour");
                    double umami = flavorsJSON.getDouble("meaty");
                    double bitter = flavorsJSON.getDouble("bitter");

                    ArrayList<String> ingredients = new ArrayList<>();
                    JSONArray ingredientsJSON = recipesJSON.getJSONArray("ingredients");
                    for (int y = 0; y < ingredientsJSON.length(); y++) {
                        String ingredientName = ingredientsJSON.getJSONObject(y).get("ingredient").toString();
                        ingredients.add(ingredientName);
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }

}
