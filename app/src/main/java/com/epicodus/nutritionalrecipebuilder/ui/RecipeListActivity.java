package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;
import com.epicodus.nutritionalrecipebuilder.services.RecipeService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {
    public static final String TAG = RecipeListActivity.class.getSimpleName();
    private SharedPreferences mSP;
    private String mRecipeSearch;
    public ArrayList<Recipe> mRecipes;
    @Bind(R.id.listView) ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        mSP = PreferenceManager.getDefaultSharedPreferences(this);
        mRecipeSearch = mSP.getString(Constants.PREFERENCES_INGREDIENT_LIST, null);
        Log.d("made it", "I'm about to get the recipes!");
        getRecipes(mRecipeSearch);
    }

    private void getRecipes(String searchTerms) {
        final RecipeService recipeService = new RecipeService();

        recipeService.findRecipes(searchTerms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mRecipes = recipeService.processResults(response);

                RecipeListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] recipeNames = new String[mRecipes.size()];
                        for (int i = 0; i < recipeNames.length; i++) {
                            recipeNames[i] = mRecipes.get(i).getRecipeName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(RecipeListActivity.this, android.R.layout.simple_list_item_1, recipeNames);
                        mListView.setAdapter(adapter);

                        for (Recipe recipe : mRecipes) {
                            Log.d("made it", "Name: " + recipe.getRecipeName());
                            Log.d("made it", "Image Source: " + recipe.getSmallImageUrl());
                        }
                    }
                });
            }
        });
    }
}
