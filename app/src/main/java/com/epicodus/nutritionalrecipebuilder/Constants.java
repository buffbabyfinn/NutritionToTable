package com.epicodus.nutritionalrecipebuilder;

/**
 * Created by Guest on 4/29/16.
 */
public class Constants {
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String NUTRITION_CONSUMER_KEY = BuildConfig.NUTRITION_CONSUMER_KEY;

    public static final String NUTRITION_BASE_URL = "http://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=";
    public static final String NUTRITION_NUTRIENT_PARAMETER = "&nutrients=";
    public static final String NUTRITION_NUTRIENT_SORT_PARAMETER = "&sort=c";
    public static final String NUTRIENT_MAX_PARAMETER = "&max=100";
    public static final String NUTRIENT_FOODGROUP_FILTER = "&fg=0100&fg=2000&fg=1500&fg=1100&fg=0900&fg=1200&fg=1000&fg=1300&fg=0200&fg=0500";

    public static final String FIREBASE_NUTRIENT_FOODS = "foods";
    public static final String FIREBASE_URL_FOODS = FIREBASE_URL + "/" + FIREBASE_NUTRIENT_FOODS;

    public static final String FIREBASE_NUTRIENT_USERS = "users";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_NUTRIENT_USERS;


}
