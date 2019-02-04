package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BankActivity extends AppCompatActivity {

    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_level_selection);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Word Bank Level");


        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        //update pictures to display which ones are learned
        updateLearnedWords();
    }

    public void onClickSegmentedBank(View view) {
        Intent intent = new Intent(getApplicationContext(), SegmentedWordBankActivity.class);
        startActivity(intent);
    }

    public void onClickVowelsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), VowelsWordBankActivity.class);
        startActivity(intent);
    }

    public void onClickConsonantsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), ConsonantsWordBankActivity.class);
        startActivity(intent);
    }

    private void updateLearnedWords() {
        //parse through the JSON
        //if increment >= 3, find the image based on the string name + .png or something
        //update the corresponding section to include that image
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

}
