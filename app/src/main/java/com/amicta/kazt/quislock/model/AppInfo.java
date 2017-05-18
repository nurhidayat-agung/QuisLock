package com.amicta.kazt.quislock.model;

import android.graphics.drawable.Drawable;

/**
 * Created by kazt on 5/17/17.
 */

public class AppInfo {
    private String packageApp;
    private String labelApp;
    private Drawable iconApp;

    public String getPackageApp() {
        return packageApp;
    }

    public void setPackageApp(String packageApp) {
        this.packageApp = packageApp;
    }

    public String getLabelApp() {
        return labelApp;
    }

    public void setLabelApp(String labelApp) {
        this.labelApp = labelApp;
    }

    public Drawable getIconApp() {
        return iconApp;
    }

    public void setIconApp(Drawable iconApp) {
        this.iconApp = iconApp;
    }
}
