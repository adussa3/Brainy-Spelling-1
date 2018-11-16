package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UserProfileSelection extends AppCompatActivity {

    private ImageView exit;
//    private ImageView backward;
//    private ImageView forward;
    private ImageView profile1;
    private ImageView profile2;
    private ImageView profile3;
    private LinearLayout creatProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_selection);

        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton2);
//        backward = (ImageView) findViewById(R.id.backButton);
//        forward = (ImageView) findViewById(R.id.forwardButton);
        creatProfile = (LinearLayout) findViewById(R.id.createProfile);
        profile1 = (ImageView) findViewById(R.id.profilePic1);
//        profile2 = (ImageView) findViewById(R.id.profilePic2);
//        profile3 = (ImageView) findViewById(R.id.profilePic3);


        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
//        Util.scaleOnTouch(backward);
//        Util.scaleOnTouch(forward);
        Util.scaleOnTouch(profile1);
//        Util.scaleOnTouch(profile2);
//        Util.scaleOnTouch(profile3);
        Util.scaleOnTouch(creatProfile);

    }

    // Intents - goes to a different activity when the button is clicked
    public void onExit(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

//    public void onProfile(View view) {
//        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(intent);
//    }
//
    public void onCreateProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }





}
