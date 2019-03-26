package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.utils.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SegmentedWordBankActivity extends AppCompatActivity {
    private ImageView exit;
    private HorizontalScrollView sv;
    private ImageButton leftScroll;
    private ImageButton rightScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segmented_word_bank);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Segmented Word Bank");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        leftScroll = (ImageButton) findViewById(R.id.leftArrow);
        rightScroll = (ImageButton) findViewById(R.id.rightArrow);
        sv = (HorizontalScrollView) findViewById(R.id.segmentedHorizontalSV);

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

        //update pictures to display which ones are learned
        //TODO: replace with actual user name that isn't hard coded
        //updateImages();
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), BankActivity.class);
        startActivity(intent);
    }

    private static void findImageButtons(ViewGroup viewGroup, ArrayList<ImageButton> views) {
        for (int i = 0, N = viewGroup.getChildCount(); i < N; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                findImageButtons((ViewGroup) child, views);
            } else if (child instanceof ImageButton) {
                views.add((ImageButton) child);
            }
        }
    }

    public ArrayList<ImageButton> getImageButtons() {
        ArrayList<ImageButton> buttonViews = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        findImageButtons(viewGroup, buttonViews);
        return buttonViews;
    }

    public void updateImages() {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        List<ImageButton> imageButtons = getImageButtons();
        for (ImageButton button : imageButtons) {
            String word = (String) button.getContentDescription();
            if (!word.contains("arrow") && Bank.getSpellCount("default", Bank.segmented, word, "segmented") < 3) {
                button.getDrawable().setColorFilter(filter);
            }
        }
    }
}
