package com.epicodus.nutritionalrecipebuilder.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
/**
 * Created by Megan on 5/16/2016.
 */
public class Recipe {
    private String recipeName;
    private double rating;

    private String sourceDisplayName;

    private String smallImageUrl;
    private double sweet;
    private double sour;
    private double piquant;
    private double bitter;
    private double umami;
    private ArrayList<String> ingredients;
    private String attributionUrl;
    private String attributionText;
    private String attributionImageUrl;
    private String pushId;
    private Integer index;
    public Recipe() {}

    public Recipe(String recipeName, double rating, String sourceDisplayName, String smallImageUrl, double sweet, double sour, double piquant, double umami, double bitter, ArrayList<String> ingredients, String attributionUrl, String attributionText, String attributionImageUrl) {
        this.recipeName = recipeName;
        this.rating = rating;
        this.sourceDisplayName = sourceDisplayName;
        this.smallImageUrl  = smallImageUrl;
        this.sweet = sweet;
        this.sour = sour;
        this.piquant = piquant;
        this.umami = umami;
        this.bitter = bitter;
        this.ingredients = ingredients;
        this.attributionUrl = attributionUrl;
        this.attributionText = attributionText;
        this.attributionImageUrl = attributionImageUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
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

    public String getAttributionUrl() {
        return attributionUrl;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getAttributionImageUrl() {
        return attributionImageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
