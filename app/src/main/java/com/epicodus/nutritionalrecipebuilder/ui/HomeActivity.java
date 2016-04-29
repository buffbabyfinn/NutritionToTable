package com.epicodus.nutritionalrecipebuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.nutritionalrecipebuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.usernameTextView) TextView mUsernameTextView;
    @Bind(R.id.continueButton) Button mContinueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mContinueButton.setOnClickListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mUsernameTextView.setText("Welcome " + username + "!");
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.continueButton:
               Intent intention = new Intent(HomeActivity.this, NutritionPickActivity.class);
               startActivity(intention);
               break;
           default:
               break;
       }
    }
}
