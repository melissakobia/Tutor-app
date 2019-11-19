
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localized_ {

    @SerializedName("en_US")
    @Expose
    private String enUS;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Localized_() {
    }

    /**
     * 
     * @param enUS
     */
    public Localized_(String enUS) {
        super();
        this.enUS = enUS;
    }

    public String getEnUS() {
        return enUS;
    }

    public void setEnUS(String enUS) {
        this.enUS = enUS;
    }

}
