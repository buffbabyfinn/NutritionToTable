package com.epicodus.nutritionalrecipebuilder.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.epicodus.nutritionalrecipebuilder.Constants;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends BaseFragment {
    private FoodsListAdapter mAdapter;
    public ArrayList<Food> mFoods = new ArrayList<>();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.progressbar_view) LinearLayout mLayout;


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
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);
        ButterKnife.bind(this, view);
        String nutrient = mSharedPreferences.getString(Constants.PREFERENCES_FOODS_KEY, null);
        if (nutrient != null) {
            getFoods(nutrient);
        }
        return view;
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

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new FoodsListAdapter(getActivity(), mFoods);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);


                    }
                });

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Switch statement added to include both search and logout functionality in menu:
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            default:
                break;
        }
        return false;
    }

}
