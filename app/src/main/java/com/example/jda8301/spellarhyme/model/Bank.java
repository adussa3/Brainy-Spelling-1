package com.example.jda8301.spellarhyme.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jda8301.spellarhyme.MyApplication;

import java.util.Map;

/**
 * @Author Harrison Banh
 * @Date January 28, 2019
 *
 * Helper class used to manipulate and modify words in a user's Bank.
 */
public class Bank {
    private static final int MASTERY = 3;
    private static SharedPreferences sharedPref = MyApplication.getAppContext().getSharedPreferences("bank", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = sharedPref.edit();

    /**
     * Increments the spell count of a specified word.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     */
    public static void incrementSpellCount(String user, String level, String word) {
        String key = user + " " + level + " " + word;
        int oldSpellCount = sharedPref.getInt(key,0);
        editor.putInt(key, oldSpellCount + 1);
        editor.apply();
    }

    /**
     * Sets the spell count of a specified word to a desired value.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     * @param count : what value to set the spell count to
     */
    public static void setSpellCount(String user, String level, String word, int count) {
        String key = user + " " + level + " " + word;
        editor.putInt(key, count);
        editor.apply();
    }

    /**
     * Sets the spell count of a specified word to 0.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     */
    public static void resetSpellCount(String user, String level, String word) {
        String key = user + " " + level + " " + word;
        editor.putInt(key, 0);
        editor.apply();
    }

    /**
     * Checks if the specified word has been mastered.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     */
    public static boolean isMastered(String user, String level, String word) {
        String key = user + " " + level + " " + word;
        return MASTERY == sharedPref.getInt(key, 0);
    }

    public static boolean isMastered(String key) {
        return MASTERY == sharedPref.getInt(key, 0);
    }

    /**
     * Returns the spell count of the specified word. If the specified word has never been stored
     * before, 0 is returned by default
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     * @return how many times the specified word has been spelled
     */
    public static int getSpellCount(String user, String level, String word) {
        String key = user + " " + level + " " + word;
        return sharedPref.getInt(key, 0);
    }


    public static int getSpellCount(String key) {
        return sharedPref.getInt(key, 0);
    }

    /**
     * Returns a map of all words from every level and every user along with their spell counts as a
     * single map.
     * @return a map of stored words along with their spell counts
     */
    public static Map<String, Integer> getBank() {
        return (Map<String, Integer>) sharedPref.getAll();
    }

    public static String parseWord(String key) {
        String[] keySegements = key.split("\\s+");
        return keySegements[2];
    }
}

