package com.example.jda8301.spellarhyme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Unit1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit1);
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 1");
    }
}
