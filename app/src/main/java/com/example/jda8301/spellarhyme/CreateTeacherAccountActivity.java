package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateTeacherAccountActivity extends AppCompatActivity {

    private ImageView exit;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher_account);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText(getString(R.string.create_teacher_account));
        
        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), TeacherLoginActivity.class);
        startActivity(intent);
    }

//    public void onClickCreateAccount(View view) {
//        Intent intent = new Intent(getApplicationContext(), TeacherLoginActivity.class);
//        startActivity(intent);
//    }
}
