package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class oneWordLevelsActivity extends AppCompatActivity {

    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word_levels);

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
//    public void onClickExit(View view) {
//        Intent intent = new Intent(getApplicationContext(), [INSERT ACTIVITY NAME HERE].class);
//        startActivity(intent);
//    }

    public void onClickLevel(View view) {
        Intent intent = new Intent(getApplicationContext(), oneWordListActivity.class);
        startActivity(intent);
    }
}