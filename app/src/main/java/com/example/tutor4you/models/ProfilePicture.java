
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilePicture {

    @SerializedName("displayImage")
    @Expose
    private String displayImage;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProfilePicture() {
    }

    /**
     * 
     * @param displayImage
     */
    public ProfilePicture(String displayImage) {
        super();
        this.displayImage = displayImage;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

}
