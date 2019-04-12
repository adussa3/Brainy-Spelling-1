package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.VowelWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Bank;
import com.example.jda8301.spellarhyme.utils.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Unit3GameActivity extends AppCompatActivity {

    private ImageView exit;

//    private Button[] buttons = new Button[6];

    private List<Button> buttons = new ArrayList<>();

    private LinearLayout fieldLayout;
    private LinearLayout wordPhoto;


    private String stringName;
    private int wordIndex;

    private int levelState;

    private VowelWord currentWord;

    private int currentField;

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    Map<String, List<VowelWord>> wordMap = helper.getVowels();

    private int[] buttonSoundID = new int[6];

    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit3_game);

        initializeComponents(savedInstanceState);

        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 3 Game");


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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                newField.setShowSoftInputOnFocus(false);
            } else { // API 11-20
                newField.setTextIsSelectable(true);
            }

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

        if (levelState <= 0) {
            for (int i = 0; i < soundTextFields.size(); i++) {
                if (currentWord.getSound()[i] != 0) {
                    soundTextFields.get(i).setText(helper.getPhonemeLetters().get(currentWord.getSound()[i]));
                    soundTextFields.get(i).setFocusable(false);
                }
            }

            stringTextFields.get(currentWord.getTargetVowel()).setText("");
            stringTextFields.get(currentWord.getTargetVowel()).setFocusableInTouchMode(true);

        } else if (levelState > 0) {

            for (int i = 0; i < soundTextFields.size(); i++) {
                if (currentWord.getSound()[i] != 0) {
                    soundTextFields.get(i).setText(helper.getPhonemeLetters().get(currentWord.getSound()[i]));
                    soundTextFields.get(i).setFocusable(false);
                }
            }

            stringTextFields.get(currentWord.getTargetVowel()).setText("");
            stringTextFields.get(currentWord.getTargetVowel()).setFocusableInTouchMode(true);

            phonemes.add(currentWord.getSound()[soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetVowel()))]);

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
                        if (currentWord.getSound()[soundTextFields.indexOf(shuffleFields.get(0))] == 0 || soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetVowel())) == soundTextFields.indexOf(shuffleFields.get(0))) {
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
            bannedPhonemes.add(currentWord.getSound()[soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetVowel()))]);
            bannedPhonemes.addAll(helper.getSimilarPhonemes().get(currentWord.getSound()[soundTextFields.indexOf(stringTextFields.get(currentWord.getTargetVowel()))]));

            Collections.shuffle(phonemes);


            int index = 0;
            for (Button currButton : buttons) {
                if (phonemes.size() > 0) {
                    int thisPhoneme = phonemes.remove(0);

                    if (thisPhoneme != 0) {
                        currButton.setText(helper.getPhonemeLetters().get(thisPhoneme).toLowerCase());
                        buttonSoundID[index] = thisPhoneme;
                    } else {
                        int randomInt = ((int) (Math.random() * (helper.getPhonemeLetters().size()-1)) + 1);


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
                    int randomInt = ((int) (Math.random() * (helper.getPhonemeLetters().size()-1)) + 1);


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
                        AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "correct");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        soundTextFields.get(currentField).setText(helper.getPhonemeLetters().get(currentWord.getSound()[currentField]));
                        currButton.setText("");

                        // Update button with new answer
                        if (phonemes.size() > 0) {
                            int thisPhoneme = phonemes.remove(0);
                            if (thisPhoneme != 0) {
                                currButton.setText(helper.getPhonemeLetters().get(thisPhoneme).toLowerCase());
                                buttonSoundID[buttons.indexOf(currButton)] = thisPhoneme;
                            } else {
                                int randomInt = ((int) (Math.random() * (helper.getPhonemeLetters().size()-1)) + 1);


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

                    } else {
                        AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "incorrect");
                        if (levelState < currentWord.getSound().length) {
                            Bank.setSpellCount("default", Bank.vowels, currentWord.getStringName(), currentWord.getCategory(), -1);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }


                    // Check to see if word is fully spelled
                    boolean incomplete = false;
                    for (int i = 0; i < currentWord.getSound().length; i++) {
                        if (currentWord.getSound()[i] != 0 && !helper.getPhonemeLetters().get(currentWord.getSound()[i]).equals(soundTextFields.get(i).getText().toString())) {
                            incomplete = true;
                        }
                    }

                    //random praise audio
                    int randomIndex = (int) (Math.random() * 42);

                    // If fully spelled, increment spell count and reset activity (for now)
                    if (!incomplete) {
                        Bank.incrementSpellCount("default",Bank.vowels,currentWord.getStringName(), currentWord.getCategory());
                        if (Bank.getSpellCount("default",Bank.vowels,currentWord.getStringName(), currentWord.getCategory()) == 1) {
                            AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "spell letters missing full");
                        } else {
                            AudioPlayerHelper.getInstance().playAudio(Config.MISC_PATH + "do it again 1");
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                        }
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

        buttons.add((Button) findViewById(R.id.button0));
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));

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


        levelState = Bank.getSpellCount("default", Bank.vowels,currentWord.getStringName(), currentWord.getCategory());
//        Log.e("LEVEL STATE", Integer.toString(levelState));

        if (levelState < 0) {
            levelState = 0;
        }

    }

    private void recreateActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
