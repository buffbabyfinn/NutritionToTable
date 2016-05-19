package com.epicodus.nutritionalrecipebuilder.models;

import java.util.ArrayList;

/**
 * Created by Megan on 5/16/2016.
 */
public class Recipe {
    private String recipeName;
    private double rating;
    private String smallImageUrls;
    private double sweet;
    private double sour;
    private double piquant;
    private double bitter;
    private double umami;
    private ArrayList<String> ingredients;
    private String pushId;

    public Recipe(String recipeName, double rating, String smallImageUrls, double sweet, double sour, double piquant, double umami, double bitter, ArrayList<String> ingredients) {
        this.recipeName = recipeName;
        this.rating = rating;
        this.smallImageUrls = smallImageUrls;
        this.sweet = sweet;
        this.sour = sour;
        this.piquant = piquant;
        this.umami = umami;
        this.bitter = bitter;
        this.ingredients = ingredients;
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

    public Double getRating() {
        return rating;
    }

    public double getSweet() {
        return sweet;
    }

    public double getSour() {
        return sour;
    }

    public double getPiquant() {
        return piquant;
    }

    public double getBitter() {
        return bitter;
    }

    public double getUmami() {
        return umami;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
