package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.utils.Bank;

public class ConsonantsWordBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consonants_word_bank);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Consonants Word Bank");

        //update pictures to display which ones are learned
        //TODO: replace with actual user name that isn't hard coded
        Bank.updateLearnedWords("default", "consonants", this);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), BankActivity.class);
        startActivity(intent);
    }
}
