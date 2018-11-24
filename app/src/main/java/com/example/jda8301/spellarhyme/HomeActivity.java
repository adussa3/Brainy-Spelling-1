package com.example.jda8301.spellarhyme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    private ImageView joinClass;
    private ImageView playerLogin;
    private LinearLayout teacherLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize variables
        joinClass = (ImageView) findViewById(R.id.joinAClass);
        playerLogin = (ImageView) findViewById(R.id.freePlay);
        teacherLogin = (LinearLayout) findViewById(R.id.imATeacher);

        // Add touch animation to buttons
        Util.scaleOnTouch(joinClass);
        Util.scaleOnTouch(playerLogin);
        Util.scaleOnTouch(teacherLogin);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickJoinClass(View view) {
        Intent intent = new Intent(getApplicationContext(), EnterClassroomCodeActivity.class);
        startActivity(intent);
    }

    public void onClickPlayerLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), PlayerLoginActivity.class);
        startActivity(intent);
    }

    public void onClickTeacherLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), TeacherLoginActivity.class);
        startActivity(intent);
    }
}
