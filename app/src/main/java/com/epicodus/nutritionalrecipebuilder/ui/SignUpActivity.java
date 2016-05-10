package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.epicodus.nutritionalrecipebuilder.models.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignUpActivity.class.getSimpleName();
    @Bind(R.id.registerButton) Button mRegisterButton;
    @Bind(R.id.signUpUsername) EditText mSignUpUserName;
    @Bind(R.id.signUpPassword) EditText mSignUpPassword;
    @Bind(R.id.signUpEmail) EditText mSignUpEmail;
    @Bind(R.id.signUpReenterPassword) EditText mSignUpReenterPassword;
    private Firebase mFirebaseRef;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mRegisterButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesEditor = mSharedPreferences.edit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                createNewUser();
//                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
//                startActivity(intent);
        }
    }

    public void createNewUser() {
        final String username = mSignUpUserName.getText().toString();
        final String email = mSignUpEmail.getText().toString();
        final String password = mSignUpPassword.getText().toString();
        final String verification = mSignUpReenterPassword.getText().toString();
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(username);
        boolean validPassword = isValidPassword(password, verification);
        if (!validEmail || !validName || !validPassword) return;

        mFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        if (authData != null) {
                            String userUid = authData.getUid();
                            createUserInFirebaseHelper(username, email, userUid);
                            mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.INVALID_EMAIL:
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                mSignUpUserName.setError("Please check that you entered your email correctly");
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                mSignUpUserName.setError(firebaseError.getMessage());
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                showErrorToast("There was a problem with the network connection");
                                break;
                            default:
                                showErrorToast(firebaseError.toString());
                        }
                    }
                });
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d(TAG, "error occured" + firebaseError);
            }
        });
    }

    private void createUserInFirebaseHelper(final String name, final String email, final String uid) {
        Log.d("user id", uid);
        final Firebase userLocation = new Firebase(Constants.FIREBASE_URL_USERS).child(uid);
        User newUser = new User(name, email);
        userLocation.setValue(newUser);
    }

    private void showErrorToast(String message) {
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mSignUpEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mSignUpUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mSignUpPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mSignUpPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}
