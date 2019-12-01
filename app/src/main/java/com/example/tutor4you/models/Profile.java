package com.example.tutor4you.models;

import org.parceler.Parcel;

import java.util.List;



public class Profile {
    private String  tutorName;
    private int age;
    private String gender;
    private String specialization;
    private String educationLevel;
    private int rate;
    private String tutorProfileUrl;
    List <Profile> profiles;

    public Profile() {
    }



    public Profile(String tutorName, int age, String gender, String specialization, String educationLevel, int rate, String tutorProfileUrl) {
        this.tutorName = tutorName;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.educationLevel = educationLevel;
        this.rate = rate;
        this.tutorProfileUrl = tutorProfileUrl;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public String getTutorProfileUrl() {
        return tutorProfileUrl;
    }

    public void setTutorProfileUrl(String tutorProfileUrl) {
        this.tutorProfileUrl = tutorProfileUrl;
    }

    public Profile getPosition (int position) {
        return profiles.get(position);

    }


}
