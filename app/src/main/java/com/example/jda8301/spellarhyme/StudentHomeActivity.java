package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentHomeActivity extends AppCompatActivity {

    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), UserProfileSelectionActivity.class);
        startActivity(intent);
    }

    public void onClickUnit1(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit1Activity.class);
        startActivity(intent);
    }

    public void onClickUnit2(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit2SelectionActivity.class);
        startActivity(intent);
    }

    public void onClickUnit3(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit3VowelListActivity.class);
        startActivity(intent);
    }

    public void onClickUnit4(View view) {
        Intent intent = new Intent(getApplicationContext(), Unit4ConsonantListActivity.class);
        startActivity(intent);
    }

    public void onClickBank(View view) {
        Intent intent = new Intent(getApplicationContext(), BankActivity.class);
        startActivity(intent);
    }
}
