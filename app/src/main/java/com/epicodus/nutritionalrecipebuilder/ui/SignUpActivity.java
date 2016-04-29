package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.nutritionalrecipebuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignUpActivity.class.getSimpleName();
    @Bind(R.id.registerButton) Button mRegisterButton;
    @Bind(R.id.signUpUsername) EditText mSignUpUserName;
    @Bind(R.id.signUpPassword) EditText mSignUpPassword;
    @Bind(R.id.signUpReenterPassword) EditText mSignUpReenterPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                String username = mSignUpUserName.getText().toString();
                String password = mSignUpPassword.getText().toString();
                String verification = mSignUpReenterPassword.getText().toString();
                if(password.equals(verification)) {
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Please enter passwords that match!", Toast.LENGTH_LONG).show();
                }
        }

    }
}
