package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EnterClassroomCodeActivity extends AppCompatActivity {

    private int digitsEntered = 1;

    private ImageView exit;

    private ImageView delete;

    private ImageView digit1;
    private ImageView digit2;
    private ImageView digit3;
    private ImageView digit4;

    private ImageView num0;
    private ImageView num1;
    private ImageView num2;
    private ImageView num3;
    private ImageView num4;
    private ImageView num5;
    private ImageView num6;
    private ImageView num7;
    private ImageView num8;
    private ImageView num9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_classroom_code);

        // Initialize Variables
        exit = (ImageView) findViewById(R.id.exitButton);
        delete = (ImageView) findViewById(R.id.deleteButton);

        digit1 = (ImageView) findViewById(R.id.digit1);
        digit2 = (ImageView) findViewById(R.id.digit2);
        digit3 = (ImageView) findViewById(R.id.digit3);
        digit4 = (ImageView) findViewById(R.id.digit4);

        num0 = (ImageView) findViewById(R.id.num0);
        num1 = (ImageView) findViewById(R.id.num1);
        num2 = (ImageView) findViewById(R.id.num2);
        num3 = (ImageView) findViewById(R.id.num3);
        num4 = (ImageView) findViewById(R.id.num4);
        num5 = (ImageView) findViewById(R.id.num5);
        num6 = (ImageView) findViewById(R.id.num6);
        num7 = (ImageView) findViewById(R.id.num7);
        num8 = (ImageView) findViewById(R.id.num8);
        num9 = (ImageView) findViewById(R.id.num9);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
        Util.scaleOnTouch(delete);

        Util.scaleOnTouch(num0);
        Util.scaleOnTouch(num1);
        Util.scaleOnTouch(num2);
        Util.scaleOnTouch(num3);
        Util.scaleOnTouch(num4);
        Util.scaleOnTouch(num5);
        Util.scaleOnTouch(num6);
        Util.scaleOnTouch(num7);
        Util.scaleOnTouch(num8);
        Util.scaleOnTouch(num9);
    }

    // Change code size
    public void onClickDigitEntered(View view) {

        if (digitsEntered <= 4) {

            float scale = getResources().getDisplayMetrics().density;
            int diameter = (int) (50 * scale);

            switch (digitsEntered) {
                case 1:
                    digit1.requestLayout();
                    digit1.getLayoutParams().height = diameter;
                    digit1.setAlpha(1f);
                    break;
                case 2:
                    digit2.requestLayout();
                    digit2.getLayoutParams().height = diameter;
                    digit2.setAlpha(1f);
                    break;
                case 3:
                    digit3.requestLayout();
                    digit3.getLayoutParams().height = diameter;
                    digit3.setAlpha(1f);
                    break;
                case 4:
                    digit4.requestLayout();
                    digit4.getLayoutParams().height = diameter;
                    digit4.setAlpha(1f);
                    break;
            }
            digitsEntered++;
        }
    }

    public void onClickDelete(View view) {

        if (digitsEntered > 1) {

            digitsEntered--;

            float scale = getResources().getDisplayMetrics().density;
            int diameter = (int) (25 * scale);

            switch (digitsEntered) {
                case 1:
                    digit1.requestLayout();
                    digit1.getLayoutParams().height = diameter;
                    digit1.setAlpha(0.6f);
                    break;
                case 2:
                    digit2.requestLayout();
                    digit2.getLayoutParams().height = diameter;
                    digit2.setAlpha(0.6f);
                    break;
                case 3:
                    digit3.requestLayout();
                    digit3.getLayoutParams().height = diameter;
                    digit3.setAlpha(0.6f);
                    break;
                case 4:
                    digit4.requestLayout();
                    digit4.getLayoutParams().height = diameter;
                    digit4.setAlpha(0.6f);
                    break;
            }
        }
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}
