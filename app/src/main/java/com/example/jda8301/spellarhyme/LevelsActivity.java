package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelsActivity extends AppCompatActivity {

    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText(getString(R.string.one_word_at_a_time));

        // Initialize Variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

    public void onClickLevelOne(View view) {
        Intent intent = new Intent(getApplicationContext(), OneWordAtATimeListActivity.class);
        startActivity(intent);
    }

    public void onClickLevelTwo(View view) {
        Intent intent = new Intent(getApplicationContext(), ThreeWordsAtATimeActivity.class);
        startActivity(intent);
    }
}