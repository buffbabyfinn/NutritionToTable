package com.epicodus.nutritionalrecipebuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    private String[] things = new String[] {"Build a library of recipes based on your custom nutritional requirements", "create a shopping list of items for your recipes", "get real healthy!"};
    @Bind(R.id.infoView) ListView mInfoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, things);
        mInfoView.setAdapter(adapter);
    }
}
