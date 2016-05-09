package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.adapters.FirebaseFoodsListAdapter;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFoodListActivity extends AppCompatActivity {
    private Query mQuery;
    private Firebase mFirebaseFoodsRef;
    private FirebaseFoodsListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_results);
        ButterKnife.bind(this);

        mFirebaseFoodsRef = new Firebase(Constants.FIREBASE_URL_FOODS);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String food = mFirebaseFoodsRef.child(userUid).toString();
        mQuery = new Firebase(food);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseFoodsListAdapter(mQuery, Food.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
