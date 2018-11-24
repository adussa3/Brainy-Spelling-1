package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OneWordAtATimeListActivity extends AppCompatActivity {

    private ImageView exit;
    private ImageView fox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word_at_a_time_list);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText(getString(R.string.one_word_at_a_time));

        // Initialize Variables
        exit = (ImageView) findViewById(R.id.exitButton);
        fox = (ImageView) findViewById(R.id.fox);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
        Util.scaleOnTouch(fox);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), LevelsActivity.class);
        startActivity(intent);
    }

    public void onClickFox(View view) {
        Intent intent = new Intent(getApplicationContext(), OneWordAtATimeGameActivity.class);
        startActivity(intent);
    }
}