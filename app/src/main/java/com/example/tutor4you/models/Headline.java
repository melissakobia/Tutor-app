
package com.example.tutor4you.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("localized")
    @Expose
    private Localized_ localized;
    @SerializedName("preferredLocale")
    @Expose
    private PreferredLocale_ preferredLocale;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Headline() {
    }

    /**
     * 
     * @param localized
     * @param preferredLocale
     */
    public Headline(Localized_ localized, PreferredLocale_ preferredLocale) {
        super();
        this.localized = localized;
        this.preferredLocale = preferredLocale;
    }

    public Localized_ getLocalized() {
        return localized;
    }

    public void setLocalized(Localized_ localized) {
        this.localized = localized;
    }

    public PreferredLocale_ getPreferredLocale() {
        return preferredLocale;
    }

    public void setPreferredLocale(PreferredLocale_ preferredLocale) {
        this.preferredLocale = preferredLocale;
    }

}
