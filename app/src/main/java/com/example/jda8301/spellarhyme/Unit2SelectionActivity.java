package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Unit2SelectionActivity extends AppCompatActivity {

    private ImageView exit;

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    List<List<SegmentedWord>> segmentedWords = helper.getSegmentedWords();

    private ImageButton[] buttons = new ImageButton[4];

    private ImageButton leftScroll;
    private ImageButton rightScroll;

    HorizontalScrollView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit2_selection);


        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 2 Word List");

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

//        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linearLayout);


        buttons[0] = (ImageButton) findViewById(R.id.imageButton6);
        buttons[1] = (ImageButton) findViewById(R.id.imageButton7);
        buttons[2] = (ImageButton) findViewById(R.id.imageButton);
        buttons[3] = (ImageButton) findViewById(R.id.imageButton2);

        leftScroll = (ImageButton) findViewById(R.id.leftScroll);
        rightScroll = (ImageButton) findViewById(R.id.rightScroll);

        sv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);

        leftScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
                }
                return false;

            }
        });

        rightScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
                }
                return false;
            }
        });


//        mainLayout.removeAllViews();
//
//
//        for (List<SegmentedWord> segmentedWords : segmentedWords) {
//            mainLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//            LinearLayout currentLinear = new LinearLayout(MyApplication.getAppContext());
//
//            currentLinear.setOrientation(LinearLayout.VERTICAL);
//
//            for (SegmentedWord segmentedWord : segmentedWords) {
//                ImageView image = new ImageView(MyApplication.getAppContext());
//
//                image.setImageResource(getResources().getIdentifier(segmentedWord.getDisplayString(),"drawable", MyApplication.getAppContext().getPackageName()));
//
//                currentLinear.addView(image);
//            }
//
//            verticalLayouts.add(currentLinear);
//
//            mainLayout.addView(currentLinear);
//
//        }



        int start = 0;
        for (ImageButton button: buttons) {
            final int i = start;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Unit2Activity.class);
                    intent.putExtra("Index", i);
                    startActivity(intent);
                }
            });
            start++;
        }
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

//    public void onClickSelectWord(View view) {
//        Intent intent = new Intent(getApplicationContext(), Unit2Activity.class);
//        startActivity(intent);
//    }
}
