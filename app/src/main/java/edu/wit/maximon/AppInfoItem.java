package edu.wit.maximon;

import android.graphics.drawable.Drawable;

public class AppInfoItem {

    private String name;
    private float usage;
    private Drawable icon;

    public AppInfoItem(final String name, final float usage, final Drawable icon) {
        this.name = name;
        this.usage = usage;
        this.icon = icon;
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
}
