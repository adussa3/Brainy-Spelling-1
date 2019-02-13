package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jda8301.spellarhyme.utils.Bank;

import java.util.ArrayList;
import java.util.List;


public class SegmentedWordBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segmented_word_bank);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Segmented Words Word Bank");

        //update pictures to display which ones are learned
        //TODO: replace with actual user name that isn't hard coded
        updateImages();
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
            if (!word.contains("arrow") && Bank.getSpellCount("default", "segmented", word) < 3) {
                button.getDrawable().setColorFilter(filter);
            }
        }
    }
}
