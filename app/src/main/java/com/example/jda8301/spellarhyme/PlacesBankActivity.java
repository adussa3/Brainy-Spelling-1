package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.jda8301.spellarhyme.utils.Bank;

import java.util.ArrayList;
import java.util.List;

public class PlacesBankActivity extends AppCompatActivity {

    private static String category = "places";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_bank);

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

        List<ImageButton> imageButtons = getImageButtons();
        for (ImageButton button : imageButtons) {
            String word = (String) button.getContentDescription();
            if (!word.contains("arrow") && Bank.getSpellCount("default", "consonants", word, cat) < 3) {
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
