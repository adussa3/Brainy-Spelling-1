package com.example.jda8301.spellarhyme;

import android.app.Application;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Config;

import java.io.IOException;

public class OneWordAtATimeGameActivity extends AppCompatActivity {

    private ImageView exit;
    private ImageView help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word_at_a_time_game);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText(getString(R.string.one_word_at_a_time));

        // Initialize Variables
        exit = (ImageView) findViewById(R.id.exitButton);
        help = (ImageView) findViewById(R.id.helpButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
        Util.scaleOnTouch(help);
    }

    // Play sounds
    public void onClickSound1(View view) {
        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + "sound-F");
    }

    public void onClickSound2(View view) {
        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + "sound-O-short");

    }

    public void onClickSound3(View view) {
        AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + "sound-X");
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), OneWordAtATimeListActivity.class);
        startActivity(intent);
    }
}
