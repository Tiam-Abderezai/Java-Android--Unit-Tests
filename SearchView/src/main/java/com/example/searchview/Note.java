package com.example.searchview;

public class Note {
//    private int imageResource;
    private String title;
    private String description;

//    int imageResource
    public Note(String title, String description) {
//        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
    }

//    public int getImageResource() {
//        return imageResource;
//    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}