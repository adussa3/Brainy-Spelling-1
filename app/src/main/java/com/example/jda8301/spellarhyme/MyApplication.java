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
            if (word.isMastered()) {
                System.out.println(word.getStringName() + "\tMastery: True");
            } else {
                System.out.println(word.getStringName() + "\tMastery: False");
            }

            word.incrementSpellCount(context, "vowels");
            word.incrementSpellCount(context, "vowels");
            word.incrementSpellCount(context, "vowels");
        }

        for (BankWord word : bank.get("vowels")) {
            if (word.isMastered()) {
                System.out.println(word.getStringName() + "\tMastery: True");
            } else {
                System.out.println(word.getStringName() + "\tMastery: False");
            }
        }
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}