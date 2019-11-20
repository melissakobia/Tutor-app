package com.example.tutor4you.models;

public class EducationLevel {

    private String educationLevelName;
    private int thumbnail;

    public EducationLevel() {
    }

    public EducationLevel(String educationLevelName, int thumbnail) {
        this.educationLevelName = educationLevelName;
        this.thumbnail = thumbnail;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
