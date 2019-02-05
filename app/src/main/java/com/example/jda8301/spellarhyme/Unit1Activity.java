package com.example.jda8301.spellarhyme;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.data.ObservableInteger;
import com.example.jda8301.spellarhyme.model.Segment;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Unit1Activity extends AppCompatActivity {

    private ImageView exit;

    private ImageView[] word1 = new ImageView[3];
    private ImageView[] word2 = new ImageView[3];
    private ImageView[] word3 = new ImageView[3];
    private ImageView[] selectedWord = new ImageView[3];
    private Button[] buttons = new Button[9];
    private EditText[] fields = new EditText[3];

    private ObservableInteger selectedWordState = new ObservableInteger(0);
    private ObservableInteger currentField = new ObservableInteger(0);

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

        // This get the segmented words for unit 1
        // For each segmented word, we get the sound files for all 3 words
        // We add those soudn files to the phonemeCode array
        ArrayList<Integer> phonemeCode = new ArrayList<>();
        final ArrayList<SegmentedWord> wordList = new ArrayList<>();

        for (SegmentedWord segmentedWord : segmentedWords.get(0)) {
            wordList.add(segmentedWord);
            for (Segment segment : segmentedWord.getSegmentInfo()) {
                phonemeCode.add(segment.getSoundFile());
            }
        }

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

        selectedWord[0] = (ImageView) findViewById(R.id.imageViewTest1);
        selectedWord[1] = (ImageView) findViewById(R.id.imageViewTest2);
        selectedWord[2] = (ImageView) findViewById(R.id.imageViewTest3);

        fields[0] = (EditText) findViewById(R.id.editLetter1);
        fields[1] = (EditText) findViewById(R.id.editLetter2);
        fields[2] = (EditText) findViewById(R.id.editLetter3);

        // Set correct images based on JSON
        for (int i = 0; i < 3; i++) {
            word1[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            word2[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            word3[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            selectedWord[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
        }


        // Initialize buttons
        buttons[0] = (Button) findViewById(R.id.letter_0);
        buttons[1] = (Button) findViewById(R.id.letter_1);
        buttons[2] = (Button) findViewById(R.id.letter_2);
        buttons[3] = (Button) findViewById(R.id.letter_3);
        buttons[4] = (Button) findViewById(R.id.letter_4);
        buttons[5] = (Button) findViewById(R.id.letter_5);
        buttons[6] = (Button) findViewById(R.id.letter_6);
        buttons[7] = (Button) findViewById(R.id.letter_7);
        buttons[8] = (Button) findViewById(R.id.letter_8);

        for (Button button : buttons) {
            // Get random index for phonemeLetters
            Random rand = new Random();
            int randomInt = rand.nextInt(phonemeCode.size());

            button.setText(helper.getPhonemeLetters().get(phonemeCode.get(randomInt)));
            Util.playSoundOnClick(button, helper.getSoundFiles().get(phonemeCode.get(randomInt)));

            phonemeCode.remove(randomInt);
        }

        for (int i = 0; i < 3; i++) {
            word1[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedWordState.getValue() != 0) {
                        selectedWordState.setValue(0);
                    }
                }
            });
            word2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedWordState.getValue() != 1) {
                        selectedWordState.setValue(1);
                    }
                }
            });
            word3[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedWordState.getValue() != 2) {
                        selectedWordState.setValue(2);
                    }
                }
            });
        }

        for (int i = 0; i < fields.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                fields[i].setShowSoftInputOnFocus(false);
            } else { // API 11-20
                fields[i].setTextIsSelectable(true);
            }
        }

        // Play sound when new EditText is selected, set variable to current selected field
        fields[0].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(0);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(wordList.get(selectedWordState.getValue()).getSegmentInfo()[0].getSoundFile()));
                }
            }
        });
        fields[1].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(1);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(wordList.get(selectedWordState.getValue()).getSegmentInfo()[1].getSoundFile()));
                }
            }
        });
        fields[2].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(2);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(wordList.get(selectedWordState.getValue()).getSegmentInfo()[2].getSoundFile()));
                }
            }
        });


        // Update EditText when correct letter button is pressed
        for (Button button: buttons) {
            final Button thisButton = button;
            button.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch (View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Log.e("thisButton", thisButton.getText().toString());
                        Log.e("thisButton", Character.toString(wordList.get(selectedWordState.getValue()).getDisplayString().charAt(currentField.getValue())));

                        if (thisButton.getText().toString().equals(Character.toString(wordList.get(selectedWordState.getValue()).getDisplayString().charAt(currentField.getValue())))) {
                            fields[currentField.getValue()].setText(thisButton.getText());
                            thisButton.setText("");
                        }
                    }
                    return false;
                }
            });
        }


        // Initialize sounds and animation for segments of selected word
        for (int i = 0; i < selectedWord.length; i++) {
            Util.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(segmentedWords.get(0).get(0).getSegmentInfo()[i].getSoundFile()));
            Util.scaleOnTouch(selectedWord[i]);
        }

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);


        // State machine for currently selected image
        Observer stateChange = new Observer() {
            @Override
            public void update(Observable o, Object newValue) {
                if ((int) newValue == 0) {
                    Log.e("State 0", Config.AUDIO_WORDS_PATH + wordList.get(0).getDisplayString());
                    selectedWord[0].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[0].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[1].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[1].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[2].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[2].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));

                    AudioPlayerHelper.getInstance().playAudio(Config.AUDIO_WORDS_PATH + wordList.get(0).getDisplayString());

                    for (int i = 0; i < selectedWord.length; i++) {
                        Util.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(wordList.get(0).getSegmentInfo()[i].getSoundFile()));
                    }


                } else if ((int) newValue == 1) {
                    Log.e("State 1", Config.AUDIO_WORDS_PATH + wordList.get(1).getDisplayString());
                    selectedWord[0].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[0].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[1].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[1].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[2].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[2].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));

                    AudioPlayerHelper.getInstance().playAudio(Config.AUDIO_WORDS_PATH + wordList.get(1).getDisplayString());

                    for (int i = 0; i < selectedWord.length; i++) {
                        Util.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(wordList.get(1).getSegmentInfo()[i].getSoundFile()));
                    }

                } else if ((int) newValue == 2) {
                    Log.e("State 2", Config.AUDIO_WORDS_PATH + wordList.get(2).getDisplayString());
                    selectedWord[0].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[0].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[1].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[1].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[2].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[2].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));

                    AudioPlayerHelper.getInstance().playAudio(Config.AUDIO_WORDS_PATH + wordList.get(2).getDisplayString());

                    for (int i = 0; i < selectedWord.length; i++) {
                        Util.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(wordList.get(2).getSegmentInfo()[i].getSoundFile()));
                    }
                }
            }
        };

        selectedWordState.addObserver(stateChange);
    }


    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }
}
