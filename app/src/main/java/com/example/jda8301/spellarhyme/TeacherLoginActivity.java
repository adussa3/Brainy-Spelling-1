package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TeacherLoginActivity extends AppCompatActivity {

    private ImageView exit;
    private TextView forgotPassword;
    private Button signUp;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText(getString(R.string.teacher_login));

        // Initialize Variables
        exit = (ImageView) findViewById(R.id.exitButton);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        signUp = (Button) findViewById(R.id.signUpButton);
        signIn = (Button) findViewById(R.id.signInButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

//    public void onClickForgotPassword(View view) {
//        Intent intent = new Intent(getApplicationContext(), .class);
//        startActivity(intent);
//    }

    public void onClickSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateTeacherAccountActivity.class);
        startActivity(intent);
    }

    public void onClickSignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
        startActivity(intent);
    }
}
