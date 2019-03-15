package com.example.jda8301.spellarhyme;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AnimalBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_bank);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Animals Word Bank");

    }

}
