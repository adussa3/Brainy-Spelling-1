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

import java.util.List;
import java.util.Map;

public class Unit4ConsonantListActivity extends AppCompatActivity {

    private ImageView exit;

    private TableLayout allRows;

    private Button[] buttons = new Button[23];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit4_consonant_list);

        initializeComponents();

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Unit 4 Consonant List");


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

        int count = 0;
        for (final Button button : buttons) {
            final int count1 = count;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Unit4WordListActivity.class);

                    intent.putExtra("index", count1);

                    startActivity(intent);
                }
            });
            count++;
        }


        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

    public void onClickSelectConsonant(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit4WordListActivity.class);
        startActivity(intent);
    }

    private void initializeComponents() {
        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);
        allRows = (TableLayout) findViewById(R.id.allRows);


        buttons[0] = (Button) findViewById(R.id.buttonB);
        buttons[1] = (Button) findViewById(R.id.buttonC_hard);
        buttons[2] = (Button) findViewById(R.id.buttonC_soft);
        buttons[3] = (Button) findViewById(R.id.buttonD);
        buttons[4] = (Button) findViewById(R.id.buttonF);
        buttons[5] = (Button) findViewById(R.id.buttonG_soft);
        buttons[6] = (Button) findViewById(R.id.buttonG_hard);
        buttons[7] = (Button) findViewById(R.id.buttonH);
        buttons[8] = (Button) findViewById(R.id.buttonJ);
        buttons[9] = (Button) findViewById(R.id.buttonK);
        buttons[10] = (Button) findViewById(R.id.buttonL);
        buttons[11] = (Button) findViewById(R.id.buttonM);
        buttons[12] = (Button) findViewById(R.id.buttonN);
        buttons[13] = (Button) findViewById(R.id.buttonP);
        buttons[14] = (Button) findViewById(R.id.buttonQU);
        buttons[15] = (Button) findViewById(R.id.buttonR);
        buttons[16] = (Button) findViewById(R.id.buttonS);
        buttons[17] = (Button) findViewById(R.id.buttonT);
        buttons[18] = (Button) findViewById(R.id.buttonV);
        buttons[19] = (Button) findViewById(R.id.buttonW);
        buttons[20] = (Button) findViewById(R.id.buttonX);
        buttons[21] = (Button) findViewById(R.id.buttonY);
        buttons[22] = (Button) findViewById(R.id.buttonZ);


    }

}
