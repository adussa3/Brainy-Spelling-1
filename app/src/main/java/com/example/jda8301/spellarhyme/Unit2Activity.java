package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.data.ObservableInteger;
import com.example.jda8301.spellarhyme.model.Segment;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Bank;
import com.example.jda8301.spellarhyme.utils.Config;

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

    private String[] spellingProgress1 = new String[3];
    private String[] spellingProgress2 = new String[3];
    private String[] spellingProgress3 = new String[3];

    private ObservableInteger selectedWordState = new ObservableInteger(0);
    private ObservableInteger currentField = new ObservableInteger(0);

    // Placeholders for if a word is learned:
    private boolean[] learned = new boolean[3];

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    List<List<SegmentedWord>> segmentedWords = helper.getSegmentedWords();

    int index = 0;

    List<SegmentedWord> selectedWordSet = segmentedWords.get(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit2);

        index = getIntent().getIntExtra("Index", 0);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 2");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        word1[0] = (ImageView) findViewById(R.id.firstWord1);
        word1[1] = (ImageView) findViewById(R.id.firstWord2);
        word1[2] = (ImageView) findViewById(R.id.firstWord3);

        word2[0] = (ImageView) findViewById(R.id.secondWord1);
        word2[1] = (ImageView) findViewById(R.id.secondWord2);
        word2[2] = (ImageView) findViewById(R.id.secondWord3);

        word3[0] = (ImageView) findViewById(R.id.thirdWord1);
        word3[1] = (ImageView) findViewById(R.id.thirdWord2);
        word3[2] = (ImageView) findViewById(R.id.thirdWord3);

        fields[0] = (EditText) findViewById(R.id.unit2t1);
        fields[1] = (EditText) findViewById(R.id.unit2t2);
        fields[2] = (EditText) findViewById(R.id.unit2t3);
        fields[3] = (EditText) findViewById(R.id.unit2t4);
        fields[4] = (EditText) findViewById(R.id.unit2t5);
        fields[5] = (EditText) findViewById(R.id.unit2t6);
        fields[6] = (EditText) findViewById(R.id.unit2t7);
        fields[7] = (EditText) findViewById(R.id.unit2t8);
        fields[8] = (EditText) findViewById(R.id.unit2t9);

        setEditTextListeners();

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

        for (int i = 0; i < fields.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                fields[i].setShowSoftInputOnFocus(false);
            } else { // API 11-20
                fields[i].setTextIsSelectable(true);
            }
        }

//      Set correct images based on JSON
        for (int i = 0; i < 3; i++) {
            word1[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(selectedWordSet.get(0).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            word2[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(selectedWordSet.get(1).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            word3[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(selectedWordSet.get(2).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
        }


        // Initialize all colors to greyscale
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        for (int i = 0; i < 3; i++) {
            if (!learned[0]) {
                word1[i].getDrawable().setColorFilter(filter);
            }
            if (!learned[1]) {
                word2[i].getDrawable().setColorFilter(filter);
            }
            if (!learned[2]) {
                word3[i].getDrawable().setColorFilter(filter);
            }
        }

        // Update EditText when correct letter button is pressed
        for (Button button: buttons) {
            final Button thisButton = button;
            button.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch (View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        int index = currentField.getValue() % 3;

                        Log.e("thisButton", thisButton.getText().toString());
                        Log.e("thisButton", Character.toString(selectedWordSet.get(selectedWordState.getValue()).getDisplayString().charAt(index)));

                        if (thisButton.getText().toString().equals(Character.toString(selectedWordSet.get(selectedWordState.getValue()).getDisplayString().charAt(index)))) {
                            fields[currentField.getValue()].setText(thisButton.getText());
                            if (selectedWordState.getValue() == 0) {
                                spellingProgress1[index] = thisButton.getText().toString();
                            } else if (selectedWordState.getValue() == 1) {
                                spellingProgress2[index] = thisButton.getText().toString();
                            } else if (selectedWordState.getValue() == 2) {
                                spellingProgress3[index] = thisButton.getText().toString();
                            }
                            thisButton.setVisibility(View.INVISIBLE);
                        }
                        ColorMatrix matrix = new ColorMatrix();
                        matrix.setSaturation(1);

                        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

                        if (selectedWordState.getValue() == 0) {
                            word1[index].getDrawable().setColorFilter(filter);
                        } else if (selectedWordState.getValue() == 1) {
                            word2[index].getDrawable().setColorFilter(filter);
                        } else {
                            word3[index].getDrawable().setColorFilter(filter);
                        }
                        updateLearnedWords();

                    }
                    return false;
                }
            });
        }
    }

    public void setEditTextListeners() {
        // Play sound when new EditText is selected, set variable to current selected field
        fields[0].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(0);
                    selectedWordState.setValue(0);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(0).getSegmentInfo()[0].getSoundFile()));
                }
            }
        });
        fields[1].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(1);
                    selectedWordState.setValue(0);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(0).getSegmentInfo()[1].getSoundFile()));
                }
            }
        });
        fields[2].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(2);
                    selectedWordState.setValue(0);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(0).getSegmentInfo()[2].getSoundFile()));
                }
            }
        });
        fields[3].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(3);
                    selectedWordState.setValue(1);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(1).getSegmentInfo()[0].getSoundFile()));
                }
            }
        });
        fields[4].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(4);
                    selectedWordState.setValue(1);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(1).getSegmentInfo()[1].getSoundFile()));
                }
            }
        });
        fields[5].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(5);
                    selectedWordState.setValue(1);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(1).getSegmentInfo()[2].getSoundFile()));
                }
            }
        });
        fields[6].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(6);
                    selectedWordState.setValue(2);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(2).getSegmentInfo()[0].getSoundFile()));
                }
            }
        });
        fields[7].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(7);
                    selectedWordState.setValue(2);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(2).getSegmentInfo()[1].getSoundFile()));
                }
            }
        });
        fields[8].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentField.setValue(8);
                    selectedWordState.setValue(2);
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(2).getSegmentInfo()[2].getSoundFile()));
                }
            }
        });
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit2SelectionActivity.class);
        startActivity(intent);
    }

    // Update learned words to be colored
    private void updateLearnedWords() {
        boolean notComplete = false;
        for (String word : spellingProgress1) {
            if (word == null) {
                notComplete = true;
            }
        }

        if (!notComplete) {
            learned[0] = true;
            Log.e("word1", selectedWordSet.get(0).getDisplayString());
             Bank.setMastered("default", Bank.segmented, selectedWordSet.get(0).getDisplayString());
        }

        notComplete = false;
        for (String word : spellingProgress2) {
            if (word == null) {
                notComplete = true;
            }
        }

        if (!notComplete) {
            learned[1] = true;
            Bank.setMastered(selectedWordSet.get(1).getDisplayString());
        }

        notComplete = false;
        for (String word : spellingProgress3) {
            if (word == null) {
                notComplete = true;
            }
        }

        if (!notComplete) {
            learned[2] = true;
            Bank.setMastered(selectedWordSet.get(2).getDisplayString());
        }

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(1);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);


        for (int i = 0; i < 3; i++) {
            if (learned[0]) {
                word1[i].getDrawable().setColorFilter(filter);
            }

            if (learned[1]) {
                word2[i].getDrawable().setColorFilter(filter);
            }

            if (learned[2]) {
                word3[i].getDrawable().setColorFilter(filter);
            }

        }

    }
}
