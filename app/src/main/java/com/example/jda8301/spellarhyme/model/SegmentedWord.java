package com.example.jda8301.spellarhyme.model;


public class SegmentedWord {
    private String stringName;
    private boolean landscape;
    private Segment[] segmentInfo;

    public SegmentedWord(String stringName, boolean landscape, Segment[] segmentInfo) {
        this.stringName = stringName;
        this.landscape = landscape;
        this.segmentInfo = segmentInfo;
    }

    public String getDisplayString() {
        return stringName;
    }

    public void setDisplayString(String stringName) {
        this.stringName = stringName;
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
