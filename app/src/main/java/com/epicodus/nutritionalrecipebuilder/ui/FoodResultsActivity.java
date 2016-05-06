package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.adapters.FoodsListAdapter;
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
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.progressbar_view) LinearLayout mLayout;
    private FoodsListAdapter mAdapter;
    public ArrayList<Food> mFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_results);
        ButterKnife.bind(this);

        new Task().execute();
    }

    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            mLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            super.onPreExecute();
            Intent intent = getIntent();
            String nutrient = intent.getStringExtra("nutrient").toString();
            getFoods(nutrient);
        }

        protected void onPostExecute(Boolean result) {
            mLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            super.onPostExecute(result);
        }




    }

    private void getFoods(String nutrient) {
        final NutrientService nutrientService = new NutrientService();

        nutrientService.findFoods(nutrient, new Callback() {
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
                        mAdapter = new FoodsListAdapter(getApplicationContext(), mFoods);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(FoodResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);


                    }
                });

            }
        });
    }
}
