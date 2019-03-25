package com.example.jda8301.spellarhyme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

    List<SegmentedWord> selectedWordSet;

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

        //Initialize ImageViews, EditTexts, and Buttons
        initializeComponents();

        //Set
        setEditTextListeners();

        //Get chosen three-words-set from previous activity
        index = getIntent().getIntExtra("Index", 0);
        selectedWordSet = segmentedWords.get(index);


        // This get the segmented words for unit 1
        // For each segmented word, we get the sound files for all 3 words
        // We add those sound files to the phonemeCode array
        ArrayList<Integer> phonemeCode = new ArrayList<>();
        final ArrayList<SegmentedWord> wordList = new ArrayList<>();

        for (SegmentedWord segmentedWord : selectedWordSet) {
            wordList.add(segmentedWord);
            for (Segment segment : segmentedWord.getSegmentInfo()) {
                phonemeCode.add(segment.getSoundFile());
            }
        }

        //Populate the buttons with answer choices randomly
        for (Button button : buttons) {
            Random rand = new Random();
            int randomInt = rand.nextInt(phonemeCode.size());
            button.setText(helper.getPhonemeLetters().get(phonemeCode.get(randomInt)));
            button.setPrivateImeOptions(helper.getSoundFiles().get(phonemeCode.get(randomInt)));
//            Util.playSoundOnClick(button, helper.getSoundFiles().get(phonemeCode.get(randomInt)));
            phonemeCode.remove(randomInt);
        }

        //Stop keyboard from showing upon pressing an EditText
        for (int i = 0; i < fields.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                fields[i].setShowSoftInputOnFocus(false);
            } else { // API 11-20
                fields[i].setTextIsSelectable(true);
            }
        }

        //Set correct images based on JSON
        for (int i = 0; i < 3; i++) {
            word1[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(selectedWordSet.get(0).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            word2[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(selectedWordSet.get(1).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
            word3[i].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(selectedWordSet.get(2).getSegmentInfo()[i].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
        }

        // Initialize all colors depending on if mastered or not
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        for (int i = 0; i < 3; i++) {
            if (!Bank.isMastered("default", Bank.segmented, selectedWordSet.get(0).getDisplayString(), selectedWordSet.get(i).getCategory())) {
                word1[i].getDrawable().setColorFilter(filter);
            }
            if (!Bank.isMastered("default", Bank.segmented, selectedWordSet.get(1).getDisplayString(), selectedWordSet.get(i).getCategory())) {
                word2[i].getDrawable().setColorFilter(filter);
            }
            if (!Bank.isMastered("default", Bank.segmented, selectedWordSet.get(2).getDisplayString(), selectedWordSet.get(i).getCategory())) {
                word3[i].getDrawable().setColorFilter(filter);
            }
        }

        Log.e("Word 1", Integer.toString(Bank.getSpellCount("default", Bank.segmented, selectedWordSet.get(0).getDisplayString(),selectedWordSet.get(0).getCategory())));
        Log.e("Word 2", Integer.toString(Bank.getSpellCount("default", Bank.segmented, selectedWordSet.get(1).getDisplayString(),selectedWordSet.get(1).getCategory())));
        Log.e("Word 3", Integer.toString(Bank.getSpellCount("default", Bank.segmented, selectedWordSet.get(2).getDisplayString(), selectedWordSet.get(2).getCategory())));


        // Update EditText when correct letter button is pressed
        for (final Button button: buttons) {
            final Button thisButton = button;
            button.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch (View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + button.getPrivateImeOptions());
                        try {
                            Thread.sleep(1000);
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        int index = currentField.getValue() % 3;

                        Log.e("thisButton", thisButton.getText().toString());
                        Log.e("thisButton", Character.toString(selectedWordSet.get(selectedWordState.getValue()).getDisplayString().charAt(index)));

                        ColorMatrix matrix = new ColorMatrix();
                        matrix.setSaturation(1);

                        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);


                        if (thisButton.getText().toString().equals(Character.toString(selectedWordSet.get(selectedWordState.getValue()).getDisplayString().charAt(index)))) {
                            AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "correct");
                            fields[currentField.getValue()].setText(thisButton.getText());
                            if (selectedWordState.getValue() == 0) {
                                spellingProgress1[index] = thisButton.getText().toString();
                                word1[index].getDrawable().setColorFilter(filter);
                            } else if (selectedWordState.getValue() == 1) {
                                spellingProgress2[index] = thisButton.getText().toString();
                                word2[index].getDrawable().setColorFilter(filter);
                            } else if (selectedWordState.getValue() == 2) {
                                spellingProgress3[index] = thisButton.getText().toString();
                                word3[index].getDrawable().setColorFilter(filter);
                            }
                            thisButton.setVisibility(View.INVISIBLE);
                        } else {
                            AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "incorrect");
                        }

//
//                        if (selectedWordState.getValue() == 0) {
//                            word1[index].getDrawable().setColorFilter(filter);
//                        } else if (selectedWordState.getValue() == 1) {
//                            word2[index].getDrawable().setColorFilter(filter);
//                        } else {
//                            word3[index].getDrawable().setColorFilter(filter);
//                        }

                        updateLearnedWords();

                    }
                    return false;
                }
            });
        }
    }

    public void setEditTextListeners() {
        // Play sound when new EditText is selected, set variable to current selected field
        int i = 0;
        while (i < 9) {
            final int index = i;
            fields[index].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        currentField.setValue(index);
                        selectedWordState.setValue(index / 3);
                        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(selectedWordSet.get(index / 3).getSegmentInfo()[index % 3].getSoundFile()));
                    }
                }
            });
            i++;
        }
    }

    // Update learned words to be colored, and update spellCounts
    private void updateLearnedWords() {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(1);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        String[] wordProgress = spellingProgress1;
        ImageView[] wordImage = word1;
        for (int i = 0; i < 3; i++) {

            wordProgress = i == 1 ? spellingProgress2 : wordProgress;
            wordProgress = i == 2 ? spellingProgress3 : wordProgress;

            wordImage = i == 1 ? word2 : wordImage;
            wordImage = i == 2 ? word3 : wordImage;

            boolean notComplete = false;
            for (String word : wordProgress) {
                if (word == null) {
                    notComplete = true;
                }
            }

            if (!notComplete && !learned[i]) {
                learned[i] = true;
                wordImage[i].getDrawable().setColorFilter(filter);
                Log.e("word" + (i + 1), selectedWordSet.get(i).getDisplayString());
                Bank.incrementSpellCount("default", Bank.segmented, selectedWordSet.get(i).getDisplayString(), selectedWordSet.get(i).getCategory());
                if (Bank.getSpellCount("default", Bank.segmented, selectedWordSet.get(i).getDisplayString(), selectedWordSet.get(i).getCategory()) >= 3) {
                    Bank.setMastered("default", Bank.segmented, selectedWordSet.get(i).getDisplayString(), selectedWordSet.get(i).getCategory());
                }
            }
        }

        boolean allSpelled = true;
        for (int i = 0; i < learned.length; i++) {
            if (!learned[i]) {
                allSpelled = false;
            }
        }

        if (allSpelled) {
            for(EditText field: fields) {
                field.setText("");
            }
            this.recreate();
        }
    }

    public void initializeComponents() {
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

        buttons[0] = (Button) findViewById(R.id.unit2b1);
        buttons[1] = (Button) findViewById(R.id.unit2b2);
        buttons[2] = (Button) findViewById(R.id.unit2b3);
        buttons[3] = (Button) findViewById(R.id.unit2b4);
        buttons[4] = (Button) findViewById(R.id.unit2b5);
        buttons[5] = (Button) findViewById(R.id.unit2b6);
        buttons[6] = (Button) findViewById(R.id.unit2b7);
        buttons[7] = (Button) findViewById(R.id.unit2b8);
        buttons[8] = (Button) findViewById(R.id.unit2b9);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit2SelectionActivity.class);
        startActivity(intent);
    }

    // Highlights the edit text when a segment is selected
    public static void onClickSelectSegment(View view) {
        EditText et = (EditText) ((ViewGroup) view).getChildAt(1);
        et.requestFocus();
    }
}
