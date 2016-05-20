package com.epicodus.nutritionalrecipebuilder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.Recipe;
import com.epicodus.nutritionalrecipebuilder.ui.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/20/16.
 */

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    @Bind(R.id.recipeNameTextView)
    TextView mRecipeNameView;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    @Bind(R.id.sourceNameTextView) TextView mSourceNameTextView;
    @Bind(R.id.recipeImageView)
    ImageView mImageView;
    private Context mContext;
    ArrayList<Recipe> mRecipes = new ArrayList<>();

    public RecipeViewHolder(View itemView, ArrayList<Recipe> recipes) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        mRecipes = recipes;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("recipes", Parcels.wrap(mRecipes));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindRecipe(Recipe recipe) {
        Picasso.with(mContext).load(recipe.getSmallImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageView);
        mRecipeNameView.setText(recipe.getRecipeName());
        mSourceNameTextView.setText(recipe.getSourceDisplayName());
        mRatingTextView.setText("Rating: " + recipe.getRating() + "/5");
    }
}

