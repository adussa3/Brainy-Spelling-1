package com.example.jda8301.spellarhyme.model;


public class SegmentedWord {
    private String displayString;
    private boolean landscape;
    private Segment[] segmentInfo;

    public SegmentedWord(String displayString, boolean landscape, Segment[] segmentInfo) {
        this.displayString = displayString;
        this.landscape = landscape;
        this.segmentInfo = segmentInfo;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public boolean isLandscape() {
        return landscape;
    }

    public void setLandscape(boolean landscape) {
        this.landscape = landscape;
    }

    public Segment[] getSegmentInfo() {
        return segmentInfo;
    }

    public void setSegmentInfo(Segment[] segmentInfo) {
        this.segmentInfo = segmentInfo;
    }
}
