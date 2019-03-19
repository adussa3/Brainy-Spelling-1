package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.VowelWord;

import java.util.List;
import java.util.Map;

public class Unit3VowelListActivity extends AppCompatActivity {

    private ImageView exit;

    private TableLayout allRows;

    private Button[] buttons = new Button[6];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit3_vowel_list);

        initializeComponents();

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 3 Vowel List");


//        Log.e("wordMap", wordMap.keySet().toString());
//        Log.e("wordMap", wordMap.get("a").get(0).getStringName());


        //        allRows.removeAllViews();

//
//        for (int i = 0; i < allRows.getChildCount(); i++) {
//
////            View subView = allRows.getChildAt(i);
//
//
//
////            if (subView instanceof ImageView) {
////                //manipulate the imageView
////
////            }
//        }


        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Unit3WordListActivity.class);

                    intent.putExtra("stringName", button.getText().toString().toLowerCase());

                    startActivity(intent);
                }
            });
        }


        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

    public void onClickSelectVowel(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit3WordListActivity.class);
        startActivity(intent);
    }

    private void initializeComponents() {
        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        allRows = (TableLayout) findViewById(R.id.allRows);


        buttons[0] = (Button) findViewById(R.id.button0);
        buttons[1] = (Button) findViewById(R.id.button1);
        buttons[2] = (Button) findViewById(R.id.button2);
        buttons[3] = (Button) findViewById(R.id.button3);
        buttons[4] = (Button) findViewById(R.id.button4);
        buttons[5] = (Button) findViewById(R.id.button5);

    }

}
