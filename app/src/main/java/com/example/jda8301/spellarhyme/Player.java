package com.example.jda8301.spellarhyme;

public class Player extends User {

    // how do we add a profile picture?
    // I am assuming it's going to be the name of the image
    private String profile;

    // constructors
    public Player() {}

    public Player(String firstName, String lastName, String profile) {
        super(firstName, lastName);

        this.profile = profile;
    }

    // instance methods
    public String getProfile() { return profile; }

    public void setProfile(String profile) { this.profile = profile; }
}