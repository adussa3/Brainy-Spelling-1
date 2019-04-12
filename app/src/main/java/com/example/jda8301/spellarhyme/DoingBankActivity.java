package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.utils.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DoingBankActivity extends AppCompatActivity {

    private static String category = "doing";

    HorizontalScrollView sv;

    ImageButton leftScroll;
    ImageButton rightScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_bank);

        leftScroll = (ImageButton) findViewById(R.id.leftArrow);
        rightScroll = (ImageButton) findViewById(R.id.rightArrow);


        sv = (HorizontalScrollView) findViewById(R.id.doingHorizontalSV);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Doing Word Bank");

        updateImages(category);
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

    public void updateImages(String cat) {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        List<ImageButton> imageButtons = getImageButtons();
        for (ImageButton button : imageButtons) {
            String word = (String) button.getContentDescription();
            if (!word.contains("arrow") && Bank.getSpellCount("default", Bank.getLevel(word), word, cat) < 3) {
                button.getDrawable().setColorFilter(filter);
            }
        }
    }
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), BankActivity.class);
        startActivity(intent);
        finish();
    }
}
