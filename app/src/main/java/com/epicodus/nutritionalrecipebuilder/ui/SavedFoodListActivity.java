package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.adapters.FirebaseFoodsListAdapter;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.util.OnStartDragListener;
import com.epicodus.nutritionalrecipebuilder.util.SimpleItemTouchHelperCallback;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFoodListActivity extends AppCompatActivity implements OnStartDragListener {
    private Query mQuery;
    private Firebase mFirebaseFoodsRef;
    private FirebaseFoodsListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private ItemTouchHelper mItemTouchHelper;

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
        String uid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String food = mFirebaseFoodsRef.child(uid).toString();
        mQuery = new Firebase(food).orderByChild("index");
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseFoodsListAdapter(mQuery, Food.class, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String uid = mSharedPreferences.getString(Constants.KEY_UID, null);
        for (Food food : mAdapter.getItems()) {
            String pushId = food.getPushId();
            food.setIndex(Integer.toString(mAdapter.getItems().indexOf(food)));
            mFirebaseFoodsRef.child(uid)
                    .child(pushId)
                    .setValue(food);
        }
    }
}
