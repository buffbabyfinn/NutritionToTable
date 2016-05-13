package com.epicodus.nutritionalrecipebuilder.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Food;
import com.epicodus.nutritionalrecipebuilder.util.FirebaseRecyclerAdapter;
import com.epicodus.nutritionalrecipebuilder.util.ItemTouchHelperAdapter;
import com.epicodus.nutritionalrecipebuilder.util.OnStartDragListener;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.Collections;

import butterknife.Bind;


/**
 * Created by Guest on 5/6/16.
 */
public class FirebaseFoodsListAdapter extends FirebaseRecyclerAdapter<FoodViewHolder, Food> implements ItemTouchHelperAdapter {
    private final OnStartDragListener mDragStartListener;
    private Context mContext;


    public FirebaseFoodsListAdapter(Query query, Class<Food> itemClass, OnStartDragListener dragStartListener) {
        super(query, itemClass);
        mDragStartListener = dragStartListener;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_item_drag, parent, false);
        return new FoodViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, int position) {
        holder.bindFood(getItem(position));
        holder.mNameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getItems(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String uid = sharedPreferences.getString(Constants.KEY_UID, null);
        Firebase ref = new Firebase(Constants.FIREBASE_URL_FOODS).child(uid);
        String foodKey = getItem(position).getPushId();
        ref.child(foodKey).removeValue();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @Override
    protected void itemAdded(Food item, String key, int position) {

    }

    @Override
    protected void itemChanged(Food oldItem, Food newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Food item, String key, int position) {

    }

    @Override
    protected void itemMoved(Food item, String key, int oldPosition, int newPosition) {

    }
}
