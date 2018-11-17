package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UserProfileSelectionActivity extends AppCompatActivity {

    private ImageView exit;

    private ImageView backward;
    private ImageView forward;

    private View profile1;
    private View profile2;
    private View profile3;

    private LinearLayout creatProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_selection);

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        backward = (ImageView) findViewById(R.id.backButton);
        forward = (ImageView) findViewById(R.id.forwardButton);

        profile1 = (LinearLayout) findViewById(R.id.profile1);
        profile2 = (LinearLayout) findViewById(R.id.profile2);
        profile3 = (LinearLayout) findViewById(R.id.profile3);

        creatProfile = (LinearLayout) findViewById(R.id.createProfile);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        Util.scaleOnTouch(backward);
        Util.scaleOnTouch(forward);

        Util.scaleOnTouch(profile1);
        Util.scaleOnTouch(profile2);
        Util.scaleOnTouch(profile3);

        Util.scaleOnTouch(creatProfile);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

//    public void onProfile(View view) {
//        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(intent);
//    }

//    public void onCreateProfile(View view) {
//        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(intent);
//    }





}
