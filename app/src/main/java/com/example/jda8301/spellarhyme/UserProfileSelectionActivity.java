package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileSelectionActivity extends AppCompatActivity {

    private ImageView exit;

    private ImageView backward;
    private ImageView forward;

    private ImageView profile1;
    private ImageView profile2;
    private ImageView profile3;

    private ImageButton createProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_selection);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText(getString(R.string.who_playing));

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        backward = (ImageView) findViewById(R.id.backButton);
        forward = (ImageView) findViewById(R.id.forwardButton);

        profile1 = (ImageView) findViewById(R.id.profilePic1);
        profile2 = (ImageView) findViewById(R.id.profilePic2);
        profile3 = (ImageView) findViewById(R.id.profilePic3);

        createProfile = (ImageButton) findViewById(R.id.createProfileButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);

        Util.scaleOnTouch(backward);
        Util.scaleOnTouch(forward);

        Util.scaleOnTouch(profile1);
        Util.scaleOnTouch(profile2);
        Util.scaleOnTouch(profile3);

        Util.scaleOnTouch(createProfile);
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

//    public void onCreateProfile(View view) {
//        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(intent);
//    }

}
