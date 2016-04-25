package com.epicodus.nutritionalrecipebuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignInActivity.class.getSimpleName();
    @Bind(R.id.signInUsername) EditText mSignInUsername;
    @Bind(R.id.signInPassword) EditText mSignInPassword;
    @Bind(R.id.logOnButton) Button mLogOnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        mLogOnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logOnButton:
                //if verified user
                String username = mSignInUsername.getText().toString();
                String password = mSignInPassword.getText().toString();
                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
