package com.epicodus.nutritionalrecipebuilder;



import com.firebase.client.Firebase;

import android.app.Application;

/**
 * Created by Guest on 5/6/16.
 */
public class NutritionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}


