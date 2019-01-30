package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class Unit1Activity extends AppCompatActivity {

    private ImageView exit;
    private ImageView[] word1 = new ImageView[3];
    private ImageView[] word2 = new ImageView[3];
    private ImageView[] word3 = new ImageView[3];
    private ImageView[] selectedWord = new ImageView[3];

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    List<List<SegmentedWord>> segmentedWords = helper.getSegmentedWords();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit1);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 1");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        word1[0] = (ImageView) findViewById(R.id.imageViewTop0);
        word1[1] = (ImageView) findViewById(R.id.imageViewTop1);
        word1[2] = (ImageView) findViewById(R.id.imageViewTop2);

        word2[0] = (ImageView) findViewById(R.id.imageViewTop3);
        word2[1] = (ImageView) findViewById(R.id.imageViewTop4);
        word2[2] = (ImageView) findViewById(R.id.imageViewTop5);

        word3[0] = (ImageView) findViewById(R.id.imageViewTop6);
        word3[1] = (ImageView) findViewById(R.id.imageViewTop7);
        word3[2] = (ImageView) findViewById(R.id.imageViewTop8);

        selectedWord[0] = (ImageView) findViewById(R.id.imageView1);
        selectedWord[1] = (ImageView) findViewById(R.id.imageView2);
        selectedWord[2] = (ImageView) findViewById(R.id.imageView3);

        // Initialize sounds and animation for segments of selected word
        for (int i = 0; i < selectedWord.length; i++) {
            Util.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(segmentedWords.get(0).get(0).getSegmentInfo()[0].getSoundFile()));
            Util.scaleOnTouch(selectedWord[i]);
        }

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }
}
