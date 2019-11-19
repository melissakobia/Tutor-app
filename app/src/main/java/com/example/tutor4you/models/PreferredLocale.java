
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferredLocale {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("language")
    @Expose
    private String language;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PreferredLocale() {
    }

    /**
     * 
     * @param country
     * @param language
     */
    public PreferredLocale(String country, String language) {
        super();
        this.country = country;
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
