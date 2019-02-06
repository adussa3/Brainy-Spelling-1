package com.example.jda8301.spellarhyme.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jda8301.spellarhyme.MyApplication;

import java.util.HashMap;
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
     * Wipes the bank of all stored words on this current android device for all users and levels.
     * Note that there is way to recover the wiped data.
     */
    public static void resetBank() {
        editor.clear().apply();
    }

    /**
     * Returns a map of all words from every level and every user along with their spell counts as a
     * single map.
     * @return a map of stored words along with their spell counts
     */
    public static Map<String, Integer> getBank() {
        return (Map<String, Integer>) sharedPref.getAll();
    }

    /**
     * Returns the list of all spelled/stored words pertaining to the specified user.
     * @param user the user to filter the bank with
     * @return a map of all words spelled by the user along with their spell counts
     */
    public static Map<String, Integer> getUserBank(String user) {
        Map<String, Integer> bank = (Map<String, Integer>) sharedPref.getAll();
        Map<String, Integer> userBank = new HashMap<>();
        for (String key : bank.keySet()) {
            if (parseUser(key).equals(user)) {
                userBank.put(key, bank.get(key));
            }
        }

        return userBank;
    }

    /**
     * Returns all words spelled by a user pertaining to a specified level
     * @param user the user to filter the bank with
     * @param level words pertaining to which game mode/level of interest
     * @return a map of all words spelled by a specific user pertaining to a certain level along
     * with their spell counts.
     */
    public static Map<String, Integer> getUserLevelBank(String user, String level) {
        Map<String, Integer> bank = (Map<String, Integer>) sharedPref.getAll();
        Map<String, Integer> userLevelBank = new HashMap<>();
        for (String key : bank.keySet()) {
            String[] parsedKey = parseKey(key);
            if (parsedKey[0].equals(user) && parsedKey[1].equals(level)) {
                userLevelBank.put(key, bank.get(key));
            }
        }

        return userLevelBank;
    }

    /**
     * Removes all stored words from the bank pertaining to a specified user
     * @param user the user who's data is to be removed
     */
    public static void removeUser(String user) {
        Map<String, Integer> bank = getBank();
        for (String key : bank.keySet()) {
            if (parseUser(key).equals(user)) {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Removes all stored words in the bank pertaining to a specified user and level
     * @param user the user who's data is to be removed
     * @param level the level of interest
     */
    public static void removeUserLevelWords(String user, String level) {
        Map<String, Integer> bank = getBank();
        for (String key : bank.keySet()) {
            String[] keySegments = parseKey(key);
            if (keySegments[0].equals(user) && keySegments[1].equals(level)) {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Removes a specified word under a specific user and level from the bank
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     */
    public static void removeWord(String user, String level, String word) {
        String key = user + " " + level + "word";
        removeWord(key);
    }

    /**
     * Removes the specified word from the bank
     * @param key the user + level + word to be removed
     */
    public static void removeWord(String key) {
        editor.remove(key).apply();
    }

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
     * Increments the spell count of a word using its key of (user + level + word) in
     * Shared Preferences.
     * @param key the user + level + word to be incremented
     */
    public static void incrementSpellCount(String key) {
        int oldSpellCount = sharedPref.getInt(key, 0);
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
        setSpellCount(key, count);
    }

    /**
     * Sets the spell count of a word using its key of (user + level + word) in Shared Preference
     * s to the desired count.
     * @param key the user + level + word to be incremented
     * @param count the desired count
     */
    public static void setSpellCount(String key, int count) {
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
        resetSpellCount(key);
    }

    /**
     * Sets the spell count of a word using its key of (user + level + word) in Shared Preferences
     * to 0.
     * @param key the user + level + word to be incremented
     */
    public static void resetSpellCount(String key) {
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
        return isMastered(key);
    }

    /**
     * Checks if a word using its key of (user + level + word) in Shared Preferences has been
     * mastered.
     * @param key the user + level + word to be incremented
     */
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
     * Breaks up a shared preferences key into the user, level, and word name parts.
     * @param key the shared preferences key to be parsed
     * @return [user, level, word]
     */
    public static String[] parseKey(String key) {
        String[] keySegments = key.split("\\s+");
        return keySegments;
    }

    /**
     * Returns the user part of a shared preferences key
     * @param key the shared preferences key to be parsed
     * @return the username portion of the key
     */
    public static String parseUser(String key) {
        String[] keySegments = key.split("\\s+");
        return keySegments[0];
    }

    /**
     * Returns the level portion of a shared preferences key
     * @param key the shared preferences key to be parsed
     * @return the level portion of the key
     */
    public static String parseLevel(String key) {
        String[] keySegments = key.split("\\s+");
        return keySegments[1];
    }

    /**
     * Returns the word of a shared preferences key
     * @param key the shared preferences key to be parsed
     * @return the word portion of the key
     */
    public static String parseWord(String key) {
        String[] keySegments = key.split("\\s+");
        return keySegments[2];
    }

    public static void updateLearnedWords(String user, String level) {
        //parse through the JSON
        Map<String, Integer> bankDetails = getUserLevelBank(user, level);

        //if increment >= 3, find the image based on the string name + .png or something
        for (String key : bankDetails.keySet()) {
            if (bankDetails.get(key) >= 3) {
                //update the corresponding section to include that image
                String fileName = parseWord(key)+".jpg";
                //how to get current imageView with the bw version of image? pass in context as param?
            }
        }

    }
}

