package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentHomeActivity extends AppCompatActivity {

    private ImageView exit;
    private ImageView spell;
    private ImageView rhyme;
    private ImageView learnedWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        spell = (ImageView) findViewById(R.id.spelling);
        rhyme = (ImageView) findViewById(R.id.rhymes);
        learnedWords = (ImageView) findViewById(R.id.learnedWords);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
        Util.scaleOnTouch(spell);
        Util.scaleOnTouch(rhyme);
        Util.scaleOnTouch(learnedWords);
    }

    // Intents - goes to a different activity when the button is clicked
//    public void onClickExit(View view) {
//        Intent intent = new Intent(getApplicationContext(), .class);
//        startActivity(intent);
//    }

    public void onClickSpell(View view) {
        Intent intent = new Intent(getApplicationContext(), LevelsActivity.class);
        startActivity(intent);
    }
}
