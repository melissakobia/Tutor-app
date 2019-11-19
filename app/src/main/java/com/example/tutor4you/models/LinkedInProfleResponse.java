
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkedInProfleResponse {

    @SerializedName("firstName")
    @Expose
    private FirstName firstName;
    @SerializedName("localizedFirstName")
    @Expose
    private String localizedFirstName;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("localizedHeadline")
    @Expose
    private String localizedHeadline;
    @SerializedName("vanityName")
    @Expose
    private String vanityName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lastName")
    @Expose
    private LastName lastName;
    @SerializedName("localizedLastName")
    @Expose
    private String localizedLastName;
    @SerializedName("profilePicture")
    @Expose
    private ProfilePicture profilePicture;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LinkedInProfleResponse() {
    }

    /**
     * 
     * @param localizedLastName
     * @param firstName
     * @param vanityName
     * @param lastName
     * @param profilePicture
     * @param localizedHeadline
     * @param id
     * @param headline
     * @param localizedFirstName
     */
    public LinkedInProfleResponse(FirstName firstName, String localizedFirstName, Headline headline, String localizedHeadline, String vanityName, String id, LastName lastName, String localizedLastName, ProfilePicture profilePicture) {
        super();
        this.firstName = firstName;
        this.localizedFirstName = localizedFirstName;
        this.headline = headline;
        this.localizedHeadline = localizedHeadline;
        this.vanityName = vanityName;
        this.id = id;
        this.lastName = lastName;
        this.localizedLastName = localizedLastName;
        this.profilePicture = profilePicture;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public String getLocalizedFirstName() {
        return localizedFirstName;
    }

    public void setLocalizedFirstName(String localizedFirstName) {
        this.localizedFirstName = localizedFirstName;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public String getLocalizedHeadline() {
        return localizedHeadline;
    }

    public void setLocalizedHeadline(String localizedHeadline) {
        this.localizedHeadline = localizedHeadline;
    }

    public String getVanityName() {
        return vanityName;
    }

    public void setVanityName(String vanityName) {
        this.vanityName = vanityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LastName getLastName() {
        return lastName;
    }

    public void setLastName(LastName lastName) {
        this.lastName = lastName;
    }

    public String getLocalizedLastName() {
        return localizedLastName;
    }

    public void setLocalizedLastName(String localizedLastName) {
        this.localizedLastName = localizedLastName;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

}
