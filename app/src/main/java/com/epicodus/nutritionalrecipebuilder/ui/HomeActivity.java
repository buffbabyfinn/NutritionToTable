package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = HomeActivity.class.getSimpleName();
    @Bind(R.id.continueButton) Button mContinueButton;
    @Bind(R.id.savedFoodButton) Button mSaveButton;
    @Bind(R.id.recipeButton) Button mRecipeButton;
    @Bind(R.id.userRecipeButton) Button mUserRecipeButton;
    private Firebase mFirebaseRef;
    private ValueEventListener mUserRefListener;
    private Firebase mUserRef;
    private String mUId;
    private SharedPreferences mSharedPreferences;
    @Bind(R.id.usernameTextView)TextView mWelcomeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUId = mSharedPreferences.getString(Constants.KEY_UID, null);
        mUserRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mUId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mContinueButton.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        mUserRefListener = mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mWelcomeTextView.setText("Welcome, " + user.getName() + "!");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Read failed");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.continueButton:
               Intent intention = new Intent(HomeActivity.this, NutritionPickActivity.class);
               startActivity(intention);
               break;
           case R.id.savedFoodButton:
               Intent intent = new Intent(HomeActivity.this, SavedFoodListActivity.class);
               startActivity(intent);
               break;
           case R.id.recipeButton:
               break;
           case R.id.userRecipeButton:
               Intent userIntent = new Intent(HomeActivity.this, SavedRecipeListActivity.class);
               startActivity(userIntent);
               break;
           default:
               break;
       }
    }

    protected void logout() {
        mFirebaseRef.unauth();
        takeUserToLoginScreenOnUnAuth();
    }

    private void takeUserToLoginScreenOnUnAuth() {
        Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
