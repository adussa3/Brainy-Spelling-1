package com.example.jda8301.spellarhyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BankActivity extends AppCompatActivity {

    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_level_selection);

        // Change Action Bar Title
        View actionBar = findViewById(R.id.actionBar);
        TextView actionBarTitle = (TextView) actionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setText("Word Bank Level");


        // Initialize variables
        exit = (ImageView) findViewById(R.id.exitButton);

        // Add touch animation to buttons
        Util.scaleOnTouch(exit);
    }

    public void onClickAnimalsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), AnimalBankActivity.class);
        startActivity(intent);
    }

    public void onClickPeopleBank(View view) {
        Intent intent = new Intent(getApplicationContext(), PeopleBankActivity.class);
        startActivity(intent);
    }

    public void onClickPretendBank(View view) {
        Intent intent = new Intent(getApplicationContext(), PretendBankActivity.class);
        startActivity(intent);
    }

    public void onClickBodyPartsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), BodyPartsActivity.class);
        startActivity(intent);
    }

    public void onClickWaterAnimalsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), WaterAnimalsBankActivity.class);
        startActivity(intent);
    }

    public void onClickBirdsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), BirdsBankActivity.class);
        startActivity(intent);
    }

    public void onClickThingsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), ThingsBankActivity.class);
        startActivity(intent);
    }

    public void onClickHouseStuffBank(View view) {
        Intent intent = new Intent(getApplicationContext(), HouseStuffBankActivity.class);
        startActivity(intent);
    }

    public void onClickToysBank(View view) {
        Intent intent = new Intent(getApplicationContext(), ToysBankActivity.class);
        startActivity(intent);
    }

    public void onClickToolsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), ToolsBankActivity.class);
        startActivity(intent);
    }

    public void onClickColorsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), ColorsBankActivity.class);
        startActivity(intent);
    }

    public void onClickClothesBank(View view) {
        Intent intent = new Intent(getApplicationContext(), ClothesBankActivity.class);
        startActivity(intent);
    }

    public void onClickVehiclesBank(View view) {
        Intent intent = new Intent(getApplicationContext(), VehiclesBankActivity.class);
        startActivity(intent);
    }

    public void onClickFoodBank(View view) {
        Intent intent = new Intent(getApplicationContext(), FoodBankActivity.class);
        startActivity(intent);
    }

    public void onClickPlacesBank(View view) {
        Intent intent = new Intent(getApplicationContext(), PlacesBankActivity.class);
        startActivity(intent);
    }

    public void onClickOutdoorsBank(View view) {
        Intent intent = new Intent(getApplicationContext(), OutdoorsBankActivity.class);
        startActivity(intent);
    }

    public void onClickDoingBank(View view) {
        Intent intent = new Intent(getApplicationContext(), DoingBankActivity.class);
        startActivity(intent);
    }

    public void onClickDescribeBank(View view) {
        Intent intent = new Intent(getApplicationContext(), DescribeBankActivity.class);
        startActivity(intent);
    }
    // Intents - goes to a different activity when the button is clicked
    public void onClickExit(View view) {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
    }

}
