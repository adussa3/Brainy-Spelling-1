package com.example.jda8301.spellarhyme.model;

import android.os.Parcelable;

/**
 * Created by YizraGhebre on 1/23/19.
 */

public class VowelWord {
    private String stringName;
    private int[] sound;
    private int[] silentLetters;
    private int[] vowels;
    private int targetVowel;

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

    public int[] getVowels() {
        return vowels;
    }

    public void setVowels(int[] vowels) {
        this.vowels = vowels;
    }

    public int getTargetVowel() {
        return targetVowel;
    }

    public void setTargetVowel(int targetVowel) {
        this.targetVowel = targetVowel;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public VowelWord(String stringName, int[] sound, int[] silentLetters, int[] vowels, int targetVowel) {
        this.stringName = stringName;
        this.sound = sound;
        this.silentLetters = silentLetters;
        this.vowels = vowels;
        this.targetVowel = targetVowel;

    }



}
