package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Unit4ConsonantListActivity extends AppCompatActivity {

    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit4_consonant_list);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 4 Consonant List");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit4WordListActivity.class);
        startActivity(intent);
    }

    public void onClickSelectConsonant(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit4GameActivity.class);
        startActivity(intent);
    }
}
