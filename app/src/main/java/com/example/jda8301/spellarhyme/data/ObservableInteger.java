package com.example.jda8301.spellarhyme.data;

import java.io.Serializable;
import java.util.Observable;

public class ObservableInteger extends Observable implements Serializable {

    private int value;

    public ObservableInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        this.setChanged();
        this.notifyObservers(value);
    }
}