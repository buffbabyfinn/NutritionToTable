package com.epicodus.nutritionalrecipebuilder.services;

import android.util.Log;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.models.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 4/29/16.
 */
public class NutrientService {
    public static void findFoods(String nutrient, Callback callback) {
        String CONSUMER_KEY = Constants.NUTRITION_CONSUMER_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NUTRITION_BASE_URL).newBuilder();
        String urlFront = urlBuilder.toString();
        String urlComplete = urlFront + CONSUMER_KEY + Constants.NUTRIENT_MAX_PARAMETER + Constants.NUTRITION_NUTRIENT_PARAMETER + nutrient + Constants.NUTRIENT_FOODGROUP_FILTER + Constants.NUTRITION_NUTRIENT_SORT_PARAMETER;

        Request request = new Request.Builder()
                .url(urlComplete)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Food> processResults(Response response) {
        ArrayList<Food> foods = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject json = new JSONObject(jsonData);
                JSONObject reportJSON = json.getJSONObject("report");
                JSONArray foodsJSON = reportJSON.getJSONArray("foods");

                for (int i = 0; i < foodsJSON.length(); i++) {
                    JSONObject nutritionJSON = foodsJSON.getJSONObject(i);
                    String name = nutritionJSON.getString("name");
                    String measure = nutritionJSON.getString("measure");

                    ArrayList<String> nutrients = new ArrayList<>();
                    JSONArray nutrientsJSON = nutritionJSON.getJSONArray("nutrients");
                    for (int y = 0; y < nutrientsJSON.length(); y++) {
                        String nutrientName = nutrientsJSON.getJSONObject(y).get("nutrient").toString();
                        nutrients.add(nutrientName);
                    }

                    ArrayList<String> units = new ArrayList<>();
                    for (int j = 0; j < nutrientsJSON.length(); j++) {
                        String unitMeasure = nutrientsJSON.getJSONObject(j).get("unit").toString();
                        units.add(unitMeasure);
                    }
                    Food food = new Food(name, measure, nutrients, units);
                    foods.add(food);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }

}
