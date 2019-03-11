package com.example.jda8301.spellarhyme.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.example.jda8301.spellarhyme.MyApplication;
import com.example.jda8301.spellarhyme.R;

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

    // Username
    public static final String user = "default";

    // Levels
    public static final String vowels = "vowels";
    public static final String consonants = "consonants";
    public static final String segmented = "segmented";

    // Categories
    public static final String people = "people";
    public static final String friends = "friends";
    public static final String pretend = "pretend";
    public static final String body_parts = "body_parts";
    public static final String animals = "animals";
    public static final String water_animals = "water_animals";
    public static final String birds = "birds";
    public static final String things = "things";
    public static final String house_stuff = "house_stuff";
    public static final String toys = "toys";
    public static final String tools = "tools";
    public static final String clothes = "clothes";
    public static final String vehicles = "vehicles";
    public static final String food = "food";
    public static final String places = "places";
    public static final String outdoors = "outdoors";
    public static final String doing = "doing";
    public static final String describe = "describe";
    public static final String colors = "colors";


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
     * Returns all words spelled by a user pertaining to a specified category
     * @param user the user to filter the bank with
     * @param category words pertaining to which game category of interest
     * @return a map of all words spelled by a specific user pertaining to a certain category along
     * with their spell counts.
     */
    public static Map<String, Integer> getUserCategoryBank(String user, String category) {
        Map<String, Integer> bank = (Map<String, Integer>) sharedPref.getAll();
        Map<String, Integer> userCategoryBank = new HashMap<>();
        for (String key : bank.keySet()) {
            String[] parsedKey = parseKey(key);
            if (parsedKey[0].equals(user) && parsedKey[3].equals(category)) {
                userCategoryBank.put(key, bank.get(key));
            }
        }

        return userCategoryBank;
    }

    /**
     * Returns all words spelled by a user pertaining to a specified level and category
     * @param user the user to filter the bank with
     * @param level words pertaining to which game mode/level of interest
     * @param category words pertaining to which game category of interest
     * @return a map of all words spelled by a specific user pertaining to a certain level and
     * category along with their spell counts.
     */
    public static Map<String, Integer> getUserLevelCategory(String user, String level, String category) {
        Map<String, Integer> bank = (Map<String, Integer>) sharedPref.getAll();
        Map<String, Integer> userLevelCategoryBank = new HashMap<>();
        for (String key : bank.keySet()) {
            String[] parsedKey = parseKey(key);
            if (parsedKey[0].equals(user) && parsedKey[1].equals(user) && parsedKey.equals(category)) {
                userLevelCategoryBank.put(key, bank.get(key));
            }
        }

        return userLevelCategoryBank;
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
     * @param category : the category of the spelled
     */
    public static void removeWord(String user, String level, String word, String category) {
        String key = user + " " + level + " " + word + " " + category;
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
     * @param category : the category of the spelled
     */
    public static void incrementSpellCount(String user, String level, String word, String category) {
        String key = user + " " + level + " " + word + " " + category;
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
     * @param category : the category of the spelled
     * @param count : what value to set the spell count to
     */
    public static void setSpellCount(String user, String level, String word, String category, int count) {
        String key = user + " " + level + " " + word + " " + category;
        setSpellCount(key, count);
    }

    /**
     * Sets the spell count of a word using its key of (user + level + word) in Shared Preferences
     * to the desired count.
     * @param key the user + level + word to be incremented
     * @param count the desired count
     */
    public static void setSpellCount(String key, int count) {
        editor.putInt(key, count);
        editor.apply();
    }

    /**
     * Sets a word in the bank corresponding to a specific user and level to be mastered.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     * @param category : the category of the spelled
     */
    public static void setMastered(String user, String level, String word, String category) {
        String key = user + " " + level + " " + word + " " + category;
        setMastered(key);
    }

    /**
     * Sets a specific word to be mastered using a shared preferences key
     * @param key the user + level + word to be incremented
     */
    public static void setMastered(String key) {
        setSpellCount(key, 3);
    }

    /**
     * Sets the spell count of a specified word to 0.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     * @param category : the category of the spelled
     */
    public static void resetSpellCount(String user, String level, String word, String category) {
        String key = user + " " + level + " " + word + " " + category;
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
     * @param category : the category of the spelled
     */
    public static boolean isMastered(String user, String level, String word, String category) {
        String key = user + " " + level + " " + word + " " + category;
        return isMastered(key);
    }

    /**
     * Checks if a word using its key of (user + level + word) in Shared Preferences has been
     * mastered.
     * @param key the user + level + word to be incremented
     */
    public static boolean isMastered(String key) {
        return sharedPref.getInt(key, 0) >= MASTERY;
    }

    /**
     * Returns the spell count of the specified word. If the specified word has never been stored
     * before, 0 is returned by default
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param word : the name of the spelled word.
     * @param category : the category of the spelled
     * @return how many times the specified word has been spelled
     */
    public static int getSpellCount(String user, String level, String word, String category) {
        String key = user + " " + level + " " + word + " " + category;
        return sharedPref.getInt(key, 0);
    }


    public static int getSpellCount(String key) {
        return sharedPref.getInt(key, 0);
    }

    /**
     * Breaks up a shared preferences key into the user, level, and word name parts.
     * @param key the shared preferences key to be parsed
     * @return [user, level, word, category]
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

    /**
     * Returns the category of a shared preferences key
     * @param key the shared preferences key to be parsed
     * @return the category portion of the key
     */
    public static String parseCategory(String key) {
        String[] keySegments = key.split("\\s+");
        return keySegments[3];
    }

    /**
     * Adds words from a specific level that are not yet stored in the bank for a specific user.
     * @param user : the bank of which user
     * @param level : the level where the word comes from
     * @param activity : the activity of the current game mode
     */
    public static void updateLearnedWords(String user, String level, Activity activity) {
        //parse through the JSON
        Map<String, Integer> bankDetails = getUserLevelBank(user, level);

        //if increment >= 3, find the image based on the string name + .png or something
        for (String key : bankDetails.keySet()) {
            if (bankDetails.get(key) >= 3) {
                //update the corresponding section to include that image
                String name = parseWord(key);
                int buttonID = MyApplication.getAppContext().getResources().getIdentifier(name + "Button", "id", MyApplication.getAppContext().getPackageName());
                ImageView iv = (ImageView) activity.findViewById(buttonID);

                //change imageview
                int imageID = MyApplication.getAppContext().getResources().getIdentifier(name, "id", MyApplication.getAppContext().getPackageName());
                iv.setImageResource(imageID);
            }
        }

    }
}

