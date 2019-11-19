
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastName {

    @SerializedName("localized")
    @Expose
    private Localized__ localized;
    @SerializedName("preferredLocale")
    @Expose
    private PreferredLocale__ preferredLocale;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastName() {
    }

    /**
     * 
     * @param localized
     * @param preferredLocale
     */
    public LastName(Localized__ localized, PreferredLocale__ preferredLocale) {
        super();
        this.localized = localized;
        this.preferredLocale = preferredLocale;
    }

    public Localized__ getLocalized() {
        return localized;
    }

    public void setLocalized(Localized__ localized) {
        this.localized = localized;
    }

    public PreferredLocale__ getPreferredLocale() {
        return preferredLocale;
    }

    public void setPreferredLocale(PreferredLocale__ preferredLocale) {
        this.preferredLocale = preferredLocale;
    }

}
