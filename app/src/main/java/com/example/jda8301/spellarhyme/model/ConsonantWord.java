package com.example.jda8301.spellarhyme.model;

/**
 * Created by YizraGhebre on 1/23/19.
 */

public class ConsonantWord {
    private String stringName;
    private String category;
    private int[] sound;
    private int[] silentLetters;
    private int[] consonants;
    private int targetConsonant;
    private String category;

    public ConsonantWord(String stringName, int[] sound, int[] silentLetters, int[] consonants, int targetConsonant, String category) {
        this.stringName = stringName;
        this.category = category;
        this.sound = sound;
        this.silentLetters = silentLetters;
        this.consonants = consonants;
        this.targetConsonant = targetConsonant;
        this.category = category;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int[] getSound() {
        return sound;
    }

    public void setSound(int[] sound) {
        this.sound = sound;
    }

    public int[] getSilentLetters() {
        return silentLetters;
    }

    public void setSilentLetters(int[] silentLetters) {
        this.silentLetters = silentLetters;
    }

    public int[] getConsonants() {
        return consonants;
    }

    public void setConsonants(int[] consonants) {
        this.consonants = consonants;
    }

    public int getTargetConsonant() {
        return targetConsonant;
    }

    public void setTargetConsonant(int targetConsonant) {
        this.targetConsonant = targetConsonant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
