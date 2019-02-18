package com.example.jda8301.spellarhyme;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jda8301.spellarhyme.service.AudioPlayerHelper;
import com.example.jda8301.spellarhyme.utils.Config;

/**
 * This class is a library for commonly used functions throughout the app
 */
public class Util {

    /******************************************************************************************/

    // https://stackoverflow.com/questions/33916287/android-scale-image-view-with-animation

    public static void scaleOnTouch(View button) {

        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(button, "scaleX", 1.1f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(button, "scaleY", 1.1f);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(button, "scaleX", 1.0f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(button, "scaleY", 1.0f);

        scaleUpX.setDuration(0250);
        scaleUpY.setDuration(0250);

        scaleDownX.setDuration(0250);
        scaleDownY.setDuration(0250);

        final AnimatorSet scaleUp = new AnimatorSet();
        final AnimatorSet scaleDown = new AnimatorSet();

        scaleUp.play(scaleUpX).with(scaleUpY);
        scaleDown.play(scaleDownX).with(scaleDownY);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    scaleUp.start();
                }
                else if(event.getAction() == MotionEvent.ACTION_UP) {
                    scaleDown.start();
                }
                return false;
            }
        });
    }

    /******************************************************************************************/

    public static void playSoundOnClick(View button, final String sound) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlayerHelper.getInstance().playAudio(Config.SOUND_PATH + sound);
            }
        });
    }

    public static void setMultipleTouchListeners(final View view, final View.OnTouchListener[] listeners) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for (View.OnTouchListener listener : listeners) {
                    listener.onTouch(view,event);
                }
                return false;
            }
        });
    }
    
}
