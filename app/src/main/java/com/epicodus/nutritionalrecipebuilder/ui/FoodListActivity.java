package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.util.OnFoodSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodListActivity extends AppCompatActivity implements OnFoodSelectedListener {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.progressbar_view) LinearLayout mLayout;
    private Integer mPosition;
    private ArrayList<Food> mFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mFoods = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_FOODS));
                if (mPosition != null && mFoods != null) {
                    Intent intent = new Intent(this, FoodDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_FOODS, Parcels.wrap(mFoods));
                    startActivity(intent);
                }
            }
        }
        setContentView(R.layout.activity_food_list);
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
        }

        protected void onPostExecute(Boolean result) {
            mLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            super.onPostExecute(result);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mPosition != null && mFoods != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_FOODS, Parcels.wrap(mFoods));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void OnFoodSelected(Integer position, ArrayList<Food> foods) {
        mPosition = position;
        mFoods = foods;
    }
}
