package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.utils.Bank;

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
        buttons[2] = (ImageButton) findViewById(R.id.leftArrow);
        buttons[3] = (ImageButton) findViewById(R.id.rightArrow);

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
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        boolean a = Bank.isMastered("default", Bank.segmented, "cat", Bank.animals);
        boolean b = Bank.isMastered("default", Bank.segmented, "dog", Bank.animals);
        boolean c = Bank.isMastered("default", Bank.segmented, "hen", Bank.birds);
        boolean d = Bank.isMastered("default", Bank.segmented, "fox", Bank.animals);
        boolean e = Bank.isMastered("default", Bank.segmented, "cub", Bank.animals);
        boolean f = Bank.isMastered("default", Bank.segmented, "ram", Bank.animals);
        if (!(a && b && c)) {
            ImageButton catdoghen = findViewById(R.id.imageButton6);
            catdoghen.setColorFilter(filter);
        }
        if (!(d && e && f)) {
            ImageButton foxcubram = findViewById(R.id.imageButton7);
            foxcubram.setColorFilter(filter);
        }
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSelectWord(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit2Activity.class);
        startActivity(intent);
    }
}
