
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstName {

    @SerializedName("localized")
    @Expose
    private Localized localized;
    @SerializedName("preferredLocale")
    @Expose
    private PreferredLocale preferredLocale;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FirstName() {
    }

    /**
     * 
     * @param localized
     * @param preferredLocale
     */
    public FirstName(Localized localized, PreferredLocale preferredLocale) {
        super();
        this.localized = localized;
        this.preferredLocale = preferredLocale;
    }

    public Localized getLocalized() {
        return localized;
    }

    public void setLocalized(Localized localized) {
        this.localized = localized;
    }

    public PreferredLocale getPreferredLocale() {
        return preferredLocale;
    }

    public void setPreferredLocale(PreferredLocale preferredLocale) {
        this.preferredLocale = preferredLocale;
    }

}
