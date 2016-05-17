package com.epicodus.nutritionalrecipebuilder.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.firebase.client.Firebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mSharedPreferencesEditor;
    public Firebase mFirebaseRef;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }

    public void addFoodToSharedPreferences(String nutrients) {
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_FOODS_KEY, nutrients).apply();
    }

    public void addIngredientToSharedPreferences(String ingredients) {
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_INGREDIENT_LIST, ingredients).apply();
    }

    public void logout() {
        mFirebaseRef.unauth();
        Intent intent = new Intent(getActivity(), SignInActivity.class);
    }
}
