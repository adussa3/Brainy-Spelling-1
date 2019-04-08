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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.utils.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ToysBankActivity extends AppCompatActivity {

    private static String category = "toys";
    private ImageView exit;
    private HorizontalScrollView sv;
    private ImageButton leftScroll;
    private ImageButton rightScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys_bank);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Toys Word Bank");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        leftScroll = (ImageButton) findViewById(R.id.leftArrow);
        rightScroll = (ImageButton) findViewById(R.id.rightArrow);
        sv = (HorizontalScrollView) findViewById(R.id.toysHorizontalSV);

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

        updateImages(category);
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

        Map<String, Integer> bank = Bank.getUserBank(Bank.user);
        String level = "";
        List<ImageButton> imageButtons = getImageButtons();
        for (ImageButton button : imageButtons) {
            String word = (String) button.getContentDescription();
            for (String key : bank.keySet()) {
                if (key.contains(word)) {
                    level = Bank.parseLevel(key);
                }
            }
            if (!word.contains("arrow") && Bank.getSpellCount("default", level, word, cat) < 3) {
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
