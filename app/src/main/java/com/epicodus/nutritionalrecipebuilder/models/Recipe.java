package com.epicodus.nutritionalrecipebuilder.models;

import java.util.ArrayList;

/**
 * Created by Megan on 5/16/2016.
 */
public class Recipe {
    private String recipeName;
    private ArrayList<String> ingredients;
    private String smallImageUrls;
    private ArrayList<Double> flavours;


    private Double rating;
    private String pushId;

    public Recipe(String recipeName, ArrayList<String> ingredients, String smallImageUrls, ArrayList<Double> flavours, Double rating) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.smallImageUrls = smallImageUrls;
        this.flavours = flavours;
        this.rating = rating;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getSmallImageUrls() {
        return smallImageUrls;
    }

    public ArrayList<Double> getFlavours() {
        return flavours;
    }

    public Double getRating() {
        return rating;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
