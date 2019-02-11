package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.Segment;
import com.example.jda8301.spellarhyme.model.SegmentedWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Unit2Activity extends AppCompatActivity {

    private ImageView exit;

    private ImageView[] word1 = new ImageView[3];
    private ImageView[] word2 = new ImageView[3];
    private ImageView[] word3 = new ImageView[3];

    private Button[] buttons = new Button[9];

    private EditText[] fields = new EditText[9];

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    List<List<SegmentedWord>> segmentedWords = helper.getSegmentedWords();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit2);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 2");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        word1[0] = (ImageView) findViewById(R.id.imageViewTop0);
        word1[1] = (ImageView) findViewById(R.id.imageViewTop1);
        word1[2] = (ImageView) findViewById(R.id.imageViewTop2);

        word2[0] = (ImageView) findViewById(R.id.imageViewTop3);
        word2[1] = (ImageView) findViewById(R.id.imageViewTop4);
        word2[2] = (ImageView) findViewById(R.id.imageViewTop5);

        word3[0] = (ImageView) findViewById(R.id.imageViewTop6);
        word3[1] = (ImageView) findViewById(R.id.imageViewTop7);
        word3[2] = (ImageView) findViewById(R.id.imageViewTop8);

        fields[0] = (EditText) findViewById(R.id.editLetter1);
        fields[1] = (EditText) findViewById(R.id.editLetter2);
        fields[2] = (EditText) findViewById(R.id.editLetter3);
        fields[3] = (EditText) findViewById(R.id.editLetter1);
        fields[4] = (EditText) findViewById(R.id.editLetter2);
        fields[5] = (EditText) findViewById(R.id.editLetter3);
        fields[6] = (EditText) findViewById(R.id.editLetter1);
        fields[7] = (EditText) findViewById(R.id.editLetter2);
        fields[8] = (EditText) findViewById(R.id.editLetter3);

        // Initialize buttons
        buttons[0] = (Button) findViewById(R.id.unit2b1);
        buttons[1] = (Button) findViewById(R.id.unit2b2);
        buttons[2] = (Button) findViewById(R.id.unit2b3);
        buttons[3] = (Button) findViewById(R.id.unit2b4);
        buttons[4] = (Button) findViewById(R.id.unit2b5);
        buttons[5] = (Button) findViewById(R.id.unit2b6);
        buttons[6] = (Button) findViewById(R.id.unit2b7);
        buttons[7] = (Button) findViewById(R.id.unit2b8);
        buttons[8] = (Button) findViewById(R.id.unit2b9);


        int index = getIntent().getIntExtra("Index", 0);

        List<SegmentedWord> selectedWordSet = segmentedWords.get(index);


        // This get the segmented words for unit 1
        // For each segmented word, we get the sound files for all 3 words
        // We add those soudn files to the phonemeCode array
        ArrayList<Integer> phonemeCode = new ArrayList<>();
        final ArrayList<SegmentedWord> wordList = new ArrayList<>();

        for (SegmentedWord segmentedWord : selectedWordSet) {
            wordList.add(segmentedWord);
            for (Segment segment : segmentedWord.getSegmentInfo()) {
                phonemeCode.add(segment.getSoundFile());
            }
        }

        for (Button button : buttons) {
            // Get random index for phonemeLetters
            Random rand = new Random();
            int randomInt = rand.nextInt(phonemeCode.size());

            button.setText(helper.getPhonemeLetters().get(phonemeCode.get(randomInt)));
            Util.playSoundOnClick(button, helper.getSoundFiles().get(phonemeCode.get(randomInt)));

            phonemeCode.remove(randomInt);
        }

//        for (int i = 0; i < fields.length; i++) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
//                fields[i].setShowSoftInputOnFocus(false);
//            } else { // API 11-20
//                fields[i].setTextIsSelectable(true);
//            }
//        }
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit2SelectionActivity.class);
        startActivity(intent);
    }
}
