package com.epicodus.nutritionalrecipebuilder.models;

import org.parceler.Parcel;

import java.util.ArrayList;


/**
 * Created by Megan on 5/16/2016.
 */
@Parcel
public class Recipe {
     String recipeName;
    double rating;

    String sourceDisplayName;

    String smallImageUrl;
//    private double sweet;
//    private double sour;
//    private double piquant;
//    private double bitter;
//    private double umami;
    ArrayList<String> ingredients;
    String attributionUrl;
    String attributionText;
    String attributionImageUrl;
    String pushId;
    Integer index;

    public Recipe() {}

    public Recipe(String recipeName, double rating, String sourceDisplayName, String smallImageUrl, ArrayList<String> ingredients, String attributionUrl, String attributionText, String attributionImageUrl) {
        this.recipeName = recipeName;
        this.rating = rating;
        this.sourceDisplayName = sourceDisplayName;
        this.smallImageUrl  = smallImageUrl;
//        this.sweet = sweet;
//        this.sour = sour;
//        this.piquant = piquant;
//        this.umami = umami;
//        this.bitter = bitter;
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

//    public double getSweet() {
//        return sweet;
//    }
//
//    public double getSour() {
//        return sour;
//    }
//
//    public double getPiquant() {
//        return piquant;
//    }
//
//    public double getBitter() {
//        return bitter;
//    }
//
//    public double getUmami() {
//        return umami;
//    }

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
