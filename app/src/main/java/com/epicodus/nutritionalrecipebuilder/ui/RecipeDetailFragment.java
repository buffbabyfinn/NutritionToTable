package com.epicodus.nutritionalrecipebuilder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends BaseFragment {
    @Bind(R.id.recipeNameTextView) TextView mRecipeNameTextView;
    @Bind(R.id.imageView) ImageView mRecipeImage;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    @Bind(R.id.sourceNameTextView) TextView mSourceNameTextView;
    @Bind(R.id.ingredientView)
    TextView mIngredientScrollView;
    @Bind(R.id.attributionImageView) ImageView mSourceImageView;
    @Bind(R.id.attributionTextView) TextView mSourceTextView;
    @Bind(R.id.attributionUrlView) TextView mSourceUrlView;
    @Bind(R.id.saveRecipeButton) Button mSaveRecipeButton;

    private Recipe mRecipe;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mRecipe.getSmallImageUrl()).into(mRecipeImage);
        Picasso.with(view.getContext()).load(mRecipe.getAttributionImageUrl()).into(mSourceImageView);
        mRecipeNameTextView.setText(mRecipe.getRecipeName());
        mRatingTextView.setText(Double.toString(mRecipe.getRating()) + "/5");
        mSourceNameTextView.setText(mRecipe.getSourceDisplayName());
        mIngredientScrollView.setText(android.text.TextUtils.join(", ", mRecipe.getIngredients()));
        mSourceTextView.setText(mRecipe.getAttributionText());
        mSourceUrlView.setText(mRecipe.getAttributionUrl());
        return view;
    }

}
