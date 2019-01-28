package com.example.jda8301.spellarhyme.model;

/**
 * @Author Harrison Banh
 * @Date January 28, 2019
 *
 * Represents a word stored within the Bank.
 */
public class BankWord {
    private String stringName;
    private int spellCount;

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

    public void incrementSpellCount() {
        this.spellCount++;
    }

    public boolean isMastered() {
        return this.spellCount >= 3;
    }
}
