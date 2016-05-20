package com.epicodus.nutritionalrecipebuilder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.adapters.FirebaseRecipeListAdater;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipeListActivity extends AppCompatActivity {

    private Query mQuery;
    private Firebase mFirebaseRecipesRef;
    private FirebaseRecipeListAdater mAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        mFirebaseRecipesRef = new Firebase(Constants.FIREBASE_RECIPE_URL);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseRecipesRef.toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseRecipeListAdater(mQuery, Recipe.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
