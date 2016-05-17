package com.epicodus.nutritionalrecipebuilder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.nutritionalrecipebuilder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedRecipeDetailFragment extends BaseFragment {


    public SavedRecipeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_recipe_detail, container, false);
    }

}
