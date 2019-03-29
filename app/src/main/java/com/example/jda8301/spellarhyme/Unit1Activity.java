package com.example.jda8301.spellarhyme;

import android.app.Application;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
import com.example.jda8301.spellarhyme.utils.Bank;
import com.example.jda8301.spellarhyme.utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Unit1Activity extends AppCompatActivity {

    private ImageView exit;

    // Each smaller word at the top
    private ImageView[] word1 = new ImageView[3];
    private ImageView[] word2 = new ImageView[3];
    private ImageView[] word3 = new ImageView[3];

    // Currently selected word to spell
    private ImageView[] selectedWord = new ImageView[3];

    // Buttons for answers
    private Button[] buttons = new Button[9];

    // Input field lines to choose phoneme to spell
    private EditText[] fields = new EditText[3];

    // Observable Integers can have listeners. Used for changing the selected word
    private ObservableInteger selectedWordState = new ObservableInteger(0);
    private ObservableInteger currentField = new ObservableInteger(0);

    // Stores the phoneme String of the word when a phoneme is spelled
    private String[] spellingProgress1 = new String[3];
    private String[] spellingProgress2 = new String[3];
    private String[] spellingProgress3 = new String[3];

    // Placeholders for if a word is learned:
    private boolean[] learned = new boolean[3];

    // Current three words for easy access
    private ArrayList<SegmentedWord> wordList = new ArrayList<>();

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    List<List<SegmentedWord>> segmentedWords = helper.getSegmentedWords();

    Random rand = new Random();

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
        // We add those sound files to the phonemeCode array
        ArrayList<Integer> phonemeCode = new ArrayList<>();


        // Make arrays for the three words and for the phoneme segment sound files
        for (SegmentedWord segmentedWord : segmentedWords.get(0)) {
            // Easy access to words
            wordList.add(segmentedWord);

            for (Segment segment : segmentedWord.getSegmentInfo()) {
                // Get phoneme code for letter options
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


        // Check to see if word is mastered and set learned to true if they are learned
        for (int i = 0; i < 3; i++) {
            if (Bank.isMastered("default", Bank.segmented, wordList.get(i).getDisplayString(),  wordList.get(i).getCategory())) {
                learned[i] = true;
            }
        }


        // Initialize all colors to grayscale
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);


        // If the word is not learned, set the image to grayscale
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

        // This loop randomizes the letter buttons
        for (Button button : buttons) {
            // Get random index for phonemeLetters
            Random rand = new Random();
            int randomInt = rand.nextInt(phonemeCode.size());

            button.setText(helper.getPhonemeLetters().get(phonemeCode.get(randomInt)));
            button.setPrivateImeOptions(helper.getSoundFiles().get(phonemeCode.get(randomInt)));
//            Util.playSoundOnClick(button, helper.getSoundFiles().get(phonemeCode.get(randomInt)));

            phonemeCode.remove(randomInt);
        }


        // Sets the state for the selected word when one of the three smaller pictures is touched
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

        // Disables keyboard when touching the EditText
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


        // Update Activity when correct letter button is selected for the selected word based on EditText selected
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

                        // Check to see if the button text equals the phoneme text of the currently selected segment
                        if (thisButton.getText().toString().equals(Character.toString(wordList.get(selectedWordState.getValue()).getDisplayString().charAt(currentField.getValue())))) {
                            AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "correct");
                            fields[currentField.getValue()].setText(thisButton.getText());
                            if (selectedWordState.getValue() == 0) {
                                spellingProgress1[currentField.getValue()] = thisButton.getText().toString();
                            } else if (selectedWordState.getValue() == 1) {
                                spellingProgress2[currentField.getValue()] = thisButton.getText().toString();
                            } else if (selectedWordState.getValue() == 2) {
                                spellingProgress3[currentField.getValue()] = thisButton.getText().toString();
                            }

                            // Make the button invisible when the letter is used
                            thisButton.setVisibility(View.INVISIBLE);

                            // Recolor the image segment when it becomes correct
                            ColorMatrix matrix = new ColorMatrix();
                            matrix.setSaturation(1);
                            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                            selectedWord[currentField.getValue()].getDrawable().setColorFilter(filter);

                        } else {
                            AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "incorrect");
                        }

                        // Helper method for updating the learned words
                        updateLearnedWords();

                    }
                    return false;
                }
            });
        }


        // Initialize sounds and animation for segments of selected word
        for (int i = 0; i < selectedWord.length; i++) {
            this.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(segmentedWords.get(0).get(0).getSegmentInfo()[i].getSoundFile()), i);
            Util.scaleOnTouch(selectedWord[i]);
        }

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);


        // State machine for currently selected image, changes images and sounds based on chosen word
        Observer stateChange = new Observer() {


            public void playSoundOnClick(View button, final String sound, final int i) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + sound);

                        EditText et;
                        switch(i) {
                            case 0:
                                et = (EditText) findViewById(R.id.editLetter1);
                                et.requestFocus();
                                break;
                            case 1:
                                et = (EditText) findViewById(R.id.editLetter2);
                                et.requestFocus();
                                break;
                            case 2:
                                et = (EditText) findViewById(R.id.editLetter3);
                                et.requestFocus();
                                break;
                        }
                    }
                });
            }

            @Override
            public void update(Observable o, Object newValue) {
                if ((int) newValue == 0) {
                    Log.e("State 0", Config.AUDIO_WORDS_PATH + wordList.get(0).getDisplayString());
                    selectedWord[0].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[0].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[1].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[1].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[2].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(0).getSegmentInfo()[2].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));

                    AudioPlayerHelper.getInstance().playAudio(Config.AUDIO_WORDS_PATH + wordList.get(0).getDisplayString());

                    for (int i = 0; i < selectedWord.length; i++) {
                        this.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(wordList.get(0).getSegmentInfo()[i].getSoundFile()), i);
                        fields[i].setText(spellingProgress1[i]);
                    }


                } else if ((int) newValue == 1) {
                    Log.e("State 1", Config.AUDIO_WORDS_PATH + wordList.get(1).getDisplayString());
                    selectedWord[0].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[0].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[1].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[1].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[2].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(1).getSegmentInfo()[2].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));

                    AudioPlayerHelper.getInstance().playAudio(Config.AUDIO_WORDS_PATH + wordList.get(1).getDisplayString());

                    for (int i = 0; i < selectedWord.length; i++) {
                        this.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(wordList.get(1).getSegmentInfo()[i].getSoundFile()), i);
                        fields[i].setText(spellingProgress2[i]);
                    }

                } else if ((int) newValue == 2) {
                    Log.e("State 2", Config.AUDIO_WORDS_PATH + wordList.get(2).getDisplayString());
                    selectedWord[0].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[0].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[1].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[1].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));
                    selectedWord[2].setImageResource(MyApplication.getAppContext().getResources().getIdentifier(wordList.get(2).getSegmentInfo()[2].getImageFile(), "drawable", MyApplication.getAppContext().getPackageName()));

                    AudioPlayerHelper.getInstance().playAudio(Config.AUDIO_WORDS_PATH + wordList.get(2).getDisplayString());

                    for (int i = 0; i < selectedWord.length; i++) {
                        this.playSoundOnClick(selectedWord[i], helper.getSoundFiles().get(wordList.get(2).getSegmentInfo()[i].getSoundFile()), i);
                        fields[i].setText(spellingProgress3[i]);
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

    // Update learned words to be colored
    private void updateLearnedWords() {

        int randomIndex = rand.nextInt(42);

        // Check to see if word1 is mastered
        boolean notComplete = false;
        for (String word : spellingProgress1) {
            if (word == null) {
                notComplete = true;
            }
        }

        // If word1 is mastered, update bank
        if (!notComplete) {
            learned[0] = true;
            Bank.setMastered("default", Bank.segmented, wordList.get(0).getDisplayString(), wordList.get(0).getCategory());
            String str = Config.PRAISE_AUDIO_PATH + Config.praiseAudios[randomIndex];
            AudioPlayerHelper.getInstance().playAudio(Config.PRAISE_AUDIO_PATH + Config.praiseAudios[randomIndex]);
        }

        // Check to see if word2 is mastered
        notComplete = false;
        for (String word : spellingProgress2) {
            if (word == null) {
                notComplete = true;
            }
        }

        // If word2 is mastered, update bank
        if (!notComplete) {
            learned[1] = true;
            Bank.setMastered("default", Bank.segmented, wordList.get(1).getDisplayString(), wordList.get(1).getCategory());
            AudioPlayerHelper.getInstance().playAudio(Config.PRAISE_AUDIO_PATH + Config.praiseAudios[randomIndex]);
        }

        // Check to see if word3 is mastered
        notComplete = false;
        for (String word : spellingProgress3) {
            if (word == null) {
                notComplete = true;
            }
        }

        // If word3 is mastered, update bank
        if (!notComplete) {
            learned[2] = true;
            Bank.setMastered("default", Bank.segmented, wordList.get(2).getDisplayString(), wordList.get(2).getCategory());
            AudioPlayerHelper.getInstance().playAudio(Config.PRAISE_AUDIO_PATH + Config.praiseAudios[randomIndex]);
        }


        // Recolor the images if the word is learned
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

    public void playSoundOnClick(View button, final String sound, final int i) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + sound);

                EditText et;
                switch(i) {
                    case 0:
                        et = (EditText) findViewById(R.id.editLetter1);
                        et.requestFocus();
                        break;
                    case 1:
                        et = (EditText) findViewById(R.id.editLetter2);
                        et.requestFocus();
                        break;
                    case 2:
                        et = (EditText) findViewById(R.id.editLetter3);
                        et.requestFocus();
                        break;
                }
            }
        });
    }
}
