package com.epicodus.nutritionalrecipebuilder.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.nutritionalrecipebuilder.Constants;
import com.epicodus.nutritionalrecipebuilder.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignInActivity.class.getSimpleName();
    @Bind(R.id.signInUsername) EditText mSignInUsername;
    @Bind(R.id.signInPassword) EditText mSignInPassword;
    @Bind(R.id.logOnButton) Button mLogOnButton;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    private Firebase mFirebaseRef;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        mLogOnButton.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(SignInActivity.this);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
        String signUpEmail = mSharedPreferences.getString(Constants.KEY_USER_EMAIL, null);
        if(signUpEmail != null) {
            mSignInUsername.setText(signUpEmail);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logOnButton:
                loginWithPassword();
                break;
            case R.id.registerTextView:
                Intent registerIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(registerIntent);
                finish();
            default:
                break;
        }
    }

    private void loginWithPassword() {
        final String email = mSignInUsername.getText().toString();
        String password = mSignInPassword.getText().toString();

        if (email.equals("")) {
            mSignInUsername.setError("Please enteryour email");
        }
        if (password.equals("")) {
            mSignInPassword.setError("Password cannot be blank");
        }

        mAuthProgressDialog.show();

        mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                mSharedPreferencesEditor.putString(Constants.KEY_USER_EMAIL, email).apply();
                mAuthProgressDialog.dismiss();
                if (authData != null) {
                    String userUid = authData.getUid();
                    mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                mAuthProgressDialog.dismiss();
                switch (firebaseError.getCode()) {
                    case FirebaseError.INVALID_EMAIL:
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        mSignInUsername.setError("Please check that you entered your email correctly");
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        mSignInUsername.setError(firebaseError.getMessage());
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

    private void showErrorToast(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();
    }
}