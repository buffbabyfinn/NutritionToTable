package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.services.NutrientService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FoodResultsActivity extends AppCompatActivity {
    public static final String TAG = FoodResultsActivity.class.getSimpleName();
    public ArrayList<Food> mFoods = new ArrayList<>();
    @Bind(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String nutrient1 = intent.getStringExtra("nutrient1");
        String nutrient2 = intent.getStringExtra("nutrient2");
        String nutrient3 = intent.getStringExtra("nutrient3");
        getFoods(nutrient1, nutrient2, nutrient3);
    }

    private void getFoods(String nutrient1, String nutrient2, String nutrient3) {
        final NutrientService nutrientService = new NutrientService();

        nutrientService.findFoods(nutrient1, nutrient2, nutrient3, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mFoods = nutrientService.processResults(response);

                FoodResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] foodNames = new String[mFoods.size()];
                        for(int i = 0; i < foodNames.length; i++) {
                            foodNames[i] = mFoods.get(i).getName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(FoodResultsActivity.this, android.R.layout.simple_list_item_2, foodNames);
                        mListView.setAdapter(adapter);

                        for(Food foodStuff : mFoods) {
                            Log.d(TAG, "Name: " + foodStuff.getName());
                            Log.d(TAG, "Measure: " + foodStuff.getMeasure());
                            Log.d(TAG, "Nutrients: " + android.text.TextUtils.join(", ", foodStuff.getNutrient()));
                            Log.d(TAG, "Units: " + foodStuff.getUnit().toString());
                        }
                    }
                });

            }
        });
    }
}
