package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.ScaleDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.ConsonantWord;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.model.VowelWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Bank;
import com.example.jda8301.spellarhyme.utils.Config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Unit4GameActivity extends AppCompatActivity {

    private ImageView exit;

//    private Button[] buttons = new Button[6];

    private List<Button> buttons = new ArrayList<>();

    private LinearLayout fieldLayout;
    private LinearLayout wordPhoto;


    private String stringName;
    private int wordIndex;

    private int levelState;

    private ConsonantWord currentWord;

    private int currentField;

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    Map<String, List<ConsonantWord>> wordMap = helper.getConsonants();

    private int[] buttonSoundID = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit4_game);

        initializeComponents(savedInstanceState);

        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 4 Game");


        wordPhoto.removeAllViews();


        ImageView newPhoto = new ImageView(MyApplication.getAppContext());
        newPhoto.setImageResource(getResources().getIdentifier(currentWord.getStringName(),"drawable", MyApplication.getAppContext().getPackageName()));

        ColorMatrix matrix = new ColorMatrix();

        if (levelState < currentWord.getSound().length - currentWord.getSilentLetters().length) {
            // Initialize all colors depending on if mastered or not
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            newPhoto.getDrawable().setColorFilter(filter);
        } else {
            matrix.setSaturation(1);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            newPhoto.getDrawable().setColorFilter(filter);
        }

        newPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        newPhoto.setAdjustViewBounds(true);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(2000, ViewGroup.LayoutParams.MATCH_PARENT);
        newPhoto.setLayoutParams(layoutParams);

        newPhoto.requestLayout();

        wordPhoto.addView(newPhoto);

        wordPhoto.setGravity(Gravity.CENTER_HORIZONTAL);

        fieldLayout.removeAllViews();

        fieldLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        fieldLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Text field references for each character in word's string
        ArrayList<EditText> stringTextFields = new ArrayList<>();
        // Text field reference for each phoneme
        final ArrayList<EditText> soundTextFields = new ArrayList<>();

        final ArrayList<Integer> phonemes = new ArrayList<>();

        for (int sound : currentWord.getSound()) {

            final EditText newField = new EditText(MyApplication.getAppContext());

            newField.setEms(4);

            newField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            if (sound != 0) {
                for (int i = 0; i < helper.getPhonemeLetters().get(sound).length(); i++) {
                    stringTextFields.add(newField);
                }

            } else {
                stringTextFields.add(newField);
                newField.setFocusable(false);
                newField.setTextColor(Color.rgb(0,255,0));
            }
            soundTextFields.add(newField);
            newField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        currentField = soundTextFields.indexOf(newField);
                        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(currentWord.getSound()[currentField]));
                    }
                }
            });
            newField.setCursorVisible(false);

            fieldLayout.addView(newField);
        }

        for (int silentLetterIndex : currentWord.getSilentLetters()) {
            stringTextFields.get(silentLetterIndex).setText(Character.toString(currentWord.getStringName().charAt(silentLetterIndex)));
        }

        Collections.shuffle(buttons);

        ArrayList<Integer> bannedPhonemes = new ArrayList<>();

        for (int soundCode : currentWord.getSound()) {
            if (soundCode != 0) {
                bannedPhonemes.add(soundCode);
                bannedPhonemes.addAll(helper.getSimilarPhonemes().get(soundCode));
            }
        }

        if (levelState == 0) {
            for (int i = 0; i < soundTextFields.size(); i++) {
                if (currentWord.getSound()[i] != 0) {
                    soundTextFields.get(i).setText(helper.getPhonemeLetters().get(currentWord.getSound()[i]));
                    soundTextFields.get(i).setFocusable(false);
                }
            }

            stringTextFields.get(currentWord.getTargetConsonant()).setText("");
            stringTextFields.get(currentWord.getTargetConsonant()).setFocusableInTouchMode(true);

        } else if (levelState > 0) {

            for (int i = 0; i < soundTextFields.size(); i++) {
                if (currentWord.getSound()[i] != 0) {
                    soundTextFields.get(i).setText(helper.getPhonemeLetters().get(currentWord.getSound()[i]));
                    soundTextFields.get(i).setFocusable(false);
                }
            }

            stringTextFields.get(currentWord.getTargetConsonant()).setText("");
            stringTextFields.get(currentWord.getTargetConsonant()).setFocusableInTouchMode(true);

            phonemes.add(currentWord.getSound()[soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetConsonant()))]);

            List<EditText> shuffleFields = new ArrayList<>(soundTextFields);
            Collections.shuffle(shuffleFields);


            for (int i = 0; i < levelState; i++) {
                EditText current = null;

                ArrayList<Integer> silentList = new ArrayList<>();

                for (int silentLetter : currentWord.getSilentLetters()) {
                    silentList.add(silentLetter);
                }

                while (current == null) {
                    if (shuffleFields.size() > 0) {
                        if (currentWord.getSound()[soundTextFields.indexOf(shuffleFields.get(0))] == 0 || soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetConsonant())) == soundTextFields.indexOf(shuffleFields.get(0))) {
                            shuffleFields.remove(0);
                        } else {
                            current = shuffleFields.remove(0);
                        }
                    } else {
                        break;
                    }
                }
                if (current != null) {
                    phonemes.add(currentWord.getSound()[soundTextFields.indexOf(current)]);
                    current.setText("");
                    current.setFocusableInTouchMode(true);
                }
            }

            // Clears text for buttons
            for (Button currButton : buttons) {
                currButton.setText("");
            }

            bannedPhonemes.addAll(phonemes);
            bannedPhonemes.add(currentWord.getSound()[soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetConsonant()))]);
            bannedPhonemes.addAll(helper.getSimilarPhonemes().get(currentWord.getSound()[soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetConsonant()))]));

            Collections.shuffle(phonemes);


            int index = 0;
            for (Button currButton : buttons) {
                if (phonemes.size() > 0) {
                    int thisPhoneme = phonemes.remove(0);

                    if (thisPhoneme != 0) {
                        currButton.setText(helper.getPhonemeLetters().get(thisPhoneme).toLowerCase());
                        buttonSoundID[index] = thisPhoneme;
                    } else {
                        int randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());


                        for (int i = 0; i < 1; i++) {
                            if (bannedPhonemes.contains(randomInt) || randomInt == 0) {
                                randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());
                                i--;
                            }
                        }
                        bannedPhonemes.add(randomInt);
                        bannedPhonemes.addAll(helper.getSimilarPhonemes().get(randomInt));
                        currButton.setText(helper.getPhonemeLetters().get(randomInt).toLowerCase());
                        buttonSoundID[index] = randomInt;
                    }
                } else {
                    int randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());


                    for (int i = 0; i < 1; i++) {
                        if (bannedPhonemes.contains(randomInt) || randomInt == 0) {
                            randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());
                            i--;
                        }
                    }
                    bannedPhonemes.add(randomInt);
                    bannedPhonemes.addAll(helper.getSimilarPhonemes().get(randomInt));
                    currButton.setText(helper.getPhonemeLetters().get(randomInt).toLowerCase());
                    buttonSoundID[index] = randomInt;
                }
                index++;
            }


        } else {
            int index = 0;
            for (Button currButton : buttons) {
                if (phonemes.size() > 0) {
                    int thisPhoneme = phonemes.remove(0);

                    boolean found = false;
                    for (Button otherButton : buttons) {
                        if (otherButton.getText().equals(helper.getPhonemeLetters().get(thisPhoneme).toLowerCase())) {
                            found = true;
                        }
                    }

                    if (!found && thisPhoneme != 0) {
                        currButton.setText(helper.getPhonemeLetters().get(thisPhoneme).toLowerCase());
                        buttonSoundID[index] = thisPhoneme;
                    } else {
                        int randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());


                        for (int i = 0; i < 1; i++) {
                            if (bannedPhonemes.contains(randomInt) || randomInt == 0) {
                                randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());
                                i--;
                            }
                        }
                        bannedPhonemes.add(randomInt);
                        bannedPhonemes.addAll(helper.getSimilarPhonemes().get(randomInt));
                        currButton.setText(helper.getPhonemeLetters().get(randomInt).toLowerCase());
                        buttonSoundID[index] = randomInt;
                    }
                } else {
                    int randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());


                    for (int i = 0; i < 1; i++) {
                        if (bannedPhonemes.contains(randomInt) || randomInt == 0) {
                            randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());
                            i--;
                        }
                    }
                    bannedPhonemes.add(randomInt);
                    bannedPhonemes.addAll(helper.getSimilarPhonemes().get(randomInt));
                    currButton.setText(helper.getPhonemeLetters().get(randomInt).toLowerCase());
                    buttonSoundID[index] = randomInt;
                }
                index++;
            }
        }

        final ArrayList<Integer> finalBannedPhonemes = new ArrayList<>(bannedPhonemes);


        for (final Button currButton : buttons) {

            currButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + helper.getSoundFiles().get(buttonSoundID[buttons.indexOf(currButton)]));

                    if (currButton.getText().toString().equals(helper.getPhonemeLetters().get(currentWord.getSound()[currentField]))) {
                        soundTextFields.get(currentField).setText(helper.getPhonemeLetters().get(currentWord.getSound()[currentField]));
                        currButton.setText("");

                        // Update button with new answer
                        if (phonemes.size() > 0) {
                            int thisPhoneme = phonemes.remove(0);
                            if (thisPhoneme != 0) {
                                currButton.setText(helper.getPhonemeLetters().get(thisPhoneme).toLowerCase());
                                buttonSoundID[buttons.indexOf(currButton)] = thisPhoneme;
                            } else {
                                int randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());


                                for (int i = 0; i < 1; i++) {
                                    if (finalBannedPhonemes.contains(randomInt) || randomInt == 0) {
                                        randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());
                                        i--;
                                    }
                                }
                                finalBannedPhonemes.add(randomInt);
                                finalBannedPhonemes.addAll(helper.getSimilarPhonemes().get(randomInt));
                                currButton.setText(helper.getPhonemeLetters().get(randomInt).toLowerCase());
                                buttonSoundID[buttons.indexOf(currButton)] = randomInt;
                            }
                        } else {
                            int randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());


                            for (int i = 0; i < 1; i++) {
                                if (finalBannedPhonemes.contains(randomInt) || randomInt == 0) {
                                    randomInt = (int) (Math.random() * helper.getPhonemeLetters().size());
                                    i--;
                                }
                            }
                            finalBannedPhonemes.add(randomInt);
                            finalBannedPhonemes.addAll(helper.getSimilarPhonemes().get(randomInt));
                            currButton.setText(helper.getPhonemeLetters().get(randomInt).toLowerCase());
                            buttonSoundID[buttons.indexOf(currButton)] = randomInt;

                        }

                    }


                    // Check to see if word is fully spelled
                    boolean incomplete = false;
                    for (int i = 0; i < currentWord.getSound().length; i++) {
                        if (currentWord.getSound()[i] != 0 && !helper.getPhonemeLetters().get(currentWord.getSound()[i]).equals(soundTextFields.get(i).getText().toString())) {
                            incomplete = true;
                        }
                    }


                    // If fully spelled, increment spell count and reset activity (for now)
                    if (!incomplete) {
                        Bank.incrementSpellCount("default",Bank.consonants,currentWord.getStringName(), currentWord.getCategory());
                        recreateActivity();
                    }


                }
            });
        }


        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        finish();
    }



    private void initializeComponents(Bundle savedInstanceState) {
        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        fieldLayout = (LinearLayout) findViewById(R.id.fieldLayout);
        wordPhoto = (LinearLayout) findViewById(R.id.wordPhoto);

        Bundle extras = getIntent().getExtras();
        if (savedInstanceState == null) {
            if(extras == null) {
                stringName = null;
            } else {
                stringName = extras.getString("stringName");
                wordIndex = extras.getInt("wordIndex");

            }
        } else {
            stringName = (String) savedInstanceState.getSerializable("stringName");
            wordIndex = (int) savedInstanceState.getSerializable("wordIndex");
        }


        currentWord = wordMap.get(stringName).get(wordIndex);

        buttons.add((Button) findViewById(R.id.button11));
        buttons.add((Button) findViewById(R.id.button22));
        buttons.add((Button) findViewById(R.id.button33));
        buttons.add((Button) findViewById(R.id.button44));
        buttons.add((Button) findViewById(R.id.button55));
        buttons.add((Button) findViewById(R.id.button66));

        int correctIndex = (int) (Math.random() * 5);

        List<String> phonemes = helper.getPhonemeLetters();

        for (int i = 0; i < buttons.size(); i++) {
            String word = currentWord.getStringName();
            int targetIndex = currentWord.getTargetConsonant();
            char targetConsonant = word.charAt(targetIndex);
            if (i == correctIndex) {
                Button b = buttons.get(i);
                b.setText("" + targetConsonant);
            } else {
                int randIndex = (int) (Math.random() * (phonemes.size() - 1));
                buttons.get(i).setText(phonemes.get(randIndex));
            }
        }



        levelState = Bank.getSpellCount("default", Bank.consonants,currentWord.getStringName(), currentWord.getCategory());
//        Log.e("LEVEL STATE", Integer.toString(levelState));

    }

    private void recreateActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
