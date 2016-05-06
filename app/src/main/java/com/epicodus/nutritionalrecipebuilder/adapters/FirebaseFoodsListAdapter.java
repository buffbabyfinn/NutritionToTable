package com.epicodus.nutritionalrecipebuilder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;
import com.firebase.client.Firebase;

/**
 * Created by Guest on 5/6/16.
 */
public class FirebaseFoodsListAdapter extends FirebaseRecyclerAdapter<FoodViewHolder, Food> {

    public FirebaseFoodsListAdapter(Query query, Class<Food> itemClass) {
        super(query, itemClass);
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foods_list_item, parent, false);
        return new FoodViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.bindFood(getItem(position));
    }

    @Override
    protected void itemAdded(Food item, String key, int position) {

    }

    @Override
    protected void itemChanged(Food olfItem, Food newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Food item, String key, int position) {

    }

    @Override
    protected void itemMoved(Food item, String key, int oldPosition, int newPosition) {

    }
}
