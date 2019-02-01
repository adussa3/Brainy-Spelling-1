package com.example.jda8301.spellarhyme.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;

/**
 * @Author Harrison Banh
 * @Date January 28, 2019
 *
 * Represents a word stored within the Bank.
 */
public class BankWord {
    private String stringName;
    private int spellCount;

    public BankWord(String name, int count) {
        stringName = name;
        spellCount = count;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public int getSpellCount() {
        return spellCount;
    }

    public void setSpellCount(int spellCount) {
        this.spellCount = spellCount;
    }

    public void incrementSpellCount(String level) {
        this.spellCount++;
        try {
            // Grabs the bank.json as an object then uses the corresponding level to filter words
            JSONObject bank = new JSONObject("{bank.json}");
            // Update the word within the corresponding level
            bank.getJSONObject(level).put(this.stringName, this.spellCount);
        } catch (JSONException e) {
            System.out.println("Problem modifying a word's data in the bank");
            e.printStackTrace();
        }
    }

    public void resetSpellCount(String level) {
        this.spellCount = 0;
        try {
            // Grabs the bank.json as an object then uses the corresponding level to filter words
            JSONObject bank = new JSONObject("{bank.json}");
            // Update the word within the corresponding level k
            bank.getJSONObject(level).put(this.stringName, this.spellCount);
        } catch (JSONException e) {
            System.out.println("Problem modifying a word's data in the bank");
            e.printStackTrace();
        }
    }

    public boolean isMastered() {
        return this.spellCount >= 3;
    }
}
