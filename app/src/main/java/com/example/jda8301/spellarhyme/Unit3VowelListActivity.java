package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class Unit3VowelListActivity extends AppCompatActivity {

    private ImageView exit;

    private TableLayout allRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit3_vowel_list);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 3 Vowel List");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        allRows = (TableLayout) findViewById(R.id.allRows);

        for (int i = 0; i < allRows.getChildCount(); i++) {

            View subView = allRows.getChildAt(i);
            if (subView instanceof ImageView) {
                //manipulate the imageView
            }
        }


        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

    public void onClickSelectVowel(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit3WordListActivity.class);
        startActivity(intent);
    }
}
