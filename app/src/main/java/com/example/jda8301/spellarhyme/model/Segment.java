package com.example.jda8301.spellarhyme.model;


public class Segment {

    private int soundFile;
    private String imageFile;

    public Segment(int soundFile, String imageFile) {
        this.soundFile = soundFile;
        this.imageFile = imageFile;
    }

    public int getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(int soundFile) {
        this.soundFile = soundFile;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
