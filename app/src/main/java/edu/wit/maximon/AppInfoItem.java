package edu.wit.maximon;

import android.graphics.drawable.Drawable;

public class AppInfoItem {

    private String name;
    private String packageID;
    private float usage;
    private Drawable icon;

    public AppInfoItem(final String name, final float usage, final Drawable icon, final String packageID) {
        this.name = name;
        this.usage = usage;
        this.icon = icon;
        this.packageID = packageID;
    }

    public String getName() {
        return name;
    }

    public float getUsage() {
        return usage;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setUsage(float usage) {
        this.usage = usage;
    }
}
