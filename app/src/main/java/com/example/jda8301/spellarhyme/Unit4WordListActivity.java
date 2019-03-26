package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Unit4WordListActivity extends AppCompatActivity {

    private ImageView exit;
    HorizontalScrollView sv;
    ImageButton leftScroll;
    ImageButton rightScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit4_word_list);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 4 Word List");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        leftScroll = (ImageButton) findViewById(R.id.leftArrow);
        rightScroll = (ImageButton) findViewById(R.id.rightArrow);
        sv = (HorizontalScrollView) findViewById(R.id.unit4HorizontalSV);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        // Set listeners for scrolling with left and right arrow buttons
        leftScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (leftScroll.isPressed()) {
                            sv.setScrollX(sv.getScrollX() - 20);
                        } else
                            timer.cancel();
                    }
                }, 0, 10);

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
                        if (rightScroll.isPressed()) {
                            sv.setScrollX(sv.getScrollX() + 20);
                        } else
                            timer.cancel();
                    }
                }, 0, 10);

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
}
