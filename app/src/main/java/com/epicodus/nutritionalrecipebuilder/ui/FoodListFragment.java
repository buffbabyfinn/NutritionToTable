package com.epicodus.nutritionalrecipebuilder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.adapters.FoodsListAdapter;
import com.epicodus.nutritionalrecipebuilder.models.Food;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends BaseFragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FoodsListAdapter mAdapter;
    public ArrayList<Food> mFoods = new ArrayList<>();


    public FoodListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false);
    }

}
