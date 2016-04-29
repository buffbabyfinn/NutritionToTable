package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.nutritionalrecipebuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.signUpButton) Button mSignUpButton;
    @Bind(R.id.signInButton) Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSignUpButton.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch(v.getId()) {
           case R.id.signUpButton:
               Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
               startActivity(intent);
               break;
           case R.id.signInButton:
               Intent intent2 = new Intent(MainActivity.this, SignInActivity.class);
               startActivity(intent2);
               break;
           default:
               break;
       }
    }
}
