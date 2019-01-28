package com.example.jda8301.spellarhyme;

import android.app.Application;
import android.content.Context;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.model.BankWord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        AppPreferencesHelper appHelper = new AppPreferencesHelper();
        Map<String, List<BankWord>> bank =  appHelper.getBank();

        for (BankWord word : bank.get("vowels")) {
            System.out.print("Word: ");
            if (word.isMastered()) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}