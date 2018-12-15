package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class JoinClassActivity extends AppCompatActivity {

    private int inputsEntered = 0;

    private ImageView exit;

    private ImageView delete;

    private ImageView input1;
    private ImageView input2;
    private ImageView input3;
    private ImageView input4;

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
        setContentView(R.layout.activity_join_class);

        // Initialize Variables
        exit = (ImageView) findViewById(R.id.exitButton);
        delete = (ImageView) findViewById(R.id.deleteButton);

        input1 = (ImageView) findViewById(R.id.input1);
        input2 = (ImageView) findViewById(R.id.input2);
        input3 = (ImageView) findViewById(R.id.input3);
        input4 = (ImageView) findViewById(R.id.input4);

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

        if (inputsEntered < 4) {

            float scale = getResources().getDisplayMetrics().density;
            int diameter = (int) (50 * scale);

            switch (inputsEntered) {
                case 0:
                    input1.requestLayout();
                    input1.getLayoutParams().height = diameter;
                    input1.setAlpha(1f);
                    break;
                case 1:
                    input2.requestLayout();
                    input2.getLayoutParams().height = diameter;
                    input2.setAlpha(1f);
                    break;
                case 2:
                    input3.requestLayout();
                    input3.getLayoutParams().height = diameter;
                    input3.setAlpha(1f);
                    break;
                case 3:
                    input4.requestLayout();
                    input4.getLayoutParams().height = diameter;
                    input4.setAlpha(1f);
                    break;
            }
            inputsEntered++;
        }
    }

    public void onClickDelete(View view) {

        if (inputsEntered > 0) {

            float scale = getResources().getDisplayMetrics().density;
            int diameter = (int) (25 * scale);

            switch (inputsEntered) {
                case 1:
                    input1.requestLayout();
                    input1.getLayoutParams().height = diameter;
                    input1.setAlpha(0.6f);
                    break;
                case 2:
                    input2.requestLayout();
                    input2.getLayoutParams().height = diameter;
                    input2.setAlpha(0.6f);
                    break;
                case 3:
                    input3.requestLayout();
                    input3.getLayoutParams().height = diameter;
                    input3.setAlpha(0.6f);
                    break;
                case 4:
                    input4.requestLayout();
                    input4.getLayoutParams().height = diameter;
                    input4.setAlpha(0.6f);
                    break;
            }
            inputsEntered--;
        }
    }

    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}
