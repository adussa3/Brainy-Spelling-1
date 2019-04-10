package com.example.jda8301.spellarhyme;

import android.app.Application;
import android.content.Context;

import com.example.jda8301.spellarhyme.data.AppPreferencesHelper;
import com.example.jda8301.spellarhyme.utils.Bank;


import java.util.Map;


public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

//        AppPreferencesHelper appHelper = new AppPreferencesHelper();

//        Bank.setMastered("default", "vowels", "axe");
//        Bank.setMastered("default", "consonants", "cow", "animals");
//
//        Map<String, Integer> bank = Bank.getBank();
//        System.out.print("Bank Size: " + bank.size());
//
//        for (String key : bank.keySet()) {
//            System.out.println("Word: " + Bank.parseWord(key)
//                    + " \tSpell Count: " + Bank.getSpellCount(key)
//                    + " \tMastery: " + Bank.isMastered(key));
//        }

    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}