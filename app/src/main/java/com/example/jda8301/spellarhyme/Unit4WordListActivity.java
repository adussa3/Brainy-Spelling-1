package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.ConsonantWord;
import com.example.jda8301.spellarhyme.utils.Bank;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Unit4WordListActivity extends AppCompatActivity {

    private ImageView exit;

    HorizontalScrollView sv;

    ImageButton leftScroll;
    ImageButton rightScroll;

    int index;

    LinearLayout imageLayout;

    AppPreferencesHelper helper = new AppPreferencesHelper();
    Map<String, List<ConsonantWord>> wordMap = helper.getConsonants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit4_word_list);

        initializeComponents(savedInstanceState);

        leftScroll = (ImageButton) findViewById(R.id.leftArrow);
        rightScroll = (ImageButton) findViewById(R.id.rightArrow);

        sv = (HorizontalScrollView) findViewById(R.id.unit4HorizontalSV);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 4 Word List");


        Log.e("index", "" + index);

        imageLayout.removeAllViews();

        String consonant = "";
        int count = 0;
        for (String key: wordMap.keySet()) {
            if (count == index) {
                consonant = key;
                break;
            }
            count++;
        }

        List<ConsonantWord> allConsonants = wordMap.get(consonant);
        int index = 0;

        for (ConsonantWord consonantWord : allConsonants) {
            ImageView myImage = new ImageView(MyApplication.getAppContext());

            myImage.setImageResource(getResources().getIdentifier(consonantWord.getStringName(),"drawable", MyApplication.getAppContext().getPackageName()));

            if (Bank.getSpellCount("default",Bank.consonants, consonantWord.getStringName(), consonantWord.getCategory()) < consonantWord.getSound().length - consonantWord.getSilentLetters().length) {
                // Initialize all colors depending on if mastered or not
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

                myImage.getDrawable().setColorFilter(filter);
            }

            imageLayout.addView(myImage);

            final int extraIndex = index;
            final String stringName = consonant;
            myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Unit4GameActivity.class);

                    intent.putExtra("stringName", stringName);
                    intent.putExtra("wordIndex", extraIndex);

                    startActivity(intent);
                }
            });

            index++;

        }




        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        leftScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(leftScroll.isPressed()) {
                            sv.setScrollX(sv.getScrollX() - 20);
                        }
                        else
                            timer.cancel();
                    }
                },0,10);

                return false;
            }
        });

        rightScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(rightScroll.isPressed()) {
                            sv.setScrollX(sv.getScrollX() + 20);
                        }
                        else
                            timer.cancel();
                    }
                },0,10);

                return false;
            }
        });

    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit4ConsonantListActivity.class);
        startActivity(intent);
    }

    public void onClickSelectWord(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit4GameActivity.class);
        startActivity(intent);
    }

    private void initializeComponents(Bundle savedInstanceState) {
        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                index = 0;
            } else {
                index = extras.getInt("index");
            }
        } else {
            index = savedInstanceState.getInt("index");
        }


        imageLayout = (LinearLayout) findViewById(R.id.imageLayout);

    }
}
