package com.epicodus.nutritionalrecipebuilder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Guest on 5/19/16.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recipeNameTextView) TextView mRecipeNameView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        @Bind(R.id.sourceNameTextView) TextView mSourceNameTextView;
        @Bind(R.id.recipeImageView) ImageView mImageView;
        private Context mContext;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
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
            Picasso.with(mContext).load(recipe.getSmallImageUrl()).into(mImageView);
            mRecipeNameView.setText(recipe.getRecipeName());
            mSourceNameTextView.setText(recipe.getSourceDisplayName());
            mRatingTextView.setText("Rating: " + recipe.getRating() + "/5");
        }
    }
}
