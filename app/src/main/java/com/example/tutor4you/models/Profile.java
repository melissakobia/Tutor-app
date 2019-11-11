package com.example.tutor4you.models;

import java.util.List;

public class Profile {
    private String  tutorName;
    private String age;
    private String gender;
    private String specialization;
    private String educationLevel;
    private int rate;
    private int Thumbnail;
    List <Profile> profiles;

    public Profile(String tutorName, String age, String gender, String specialization, String educationLevel, int rate) {
        this.tutorName = tutorName;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.educationLevel = educationLevel;
        this.rate = rate;
        //profileImages = this.Thumbnail;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
    public Profile getPosition (int position) {
        return profiles.get(position);

    }


}
