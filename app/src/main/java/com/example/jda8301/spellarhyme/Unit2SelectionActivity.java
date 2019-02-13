package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Config;

import java.util.List;

public class Unit2SelectionActivity extends AppCompatActivity {

    private ImageView exit;

    // Initializes AppPreferencesHelper to read JSON files
    AppPreferencesHelper helper = new AppPreferencesHelper();
    List<List<SegmentedWord>> segmentedWords = helper.getSegmentedWords();

    private ImageButton[] buttons = new ImageButton[4];


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


        buttons[0] = (ImageButton) findViewById(R.id.imageButton6);
        buttons[1] = (ImageButton) findViewById(R.id.imageButton7);
        buttons[2] = (ImageButton) findViewById(R.id.imageButton);
        buttons[3] = (ImageButton) findViewById(R.id.imageButton2);

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
