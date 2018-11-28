package edu.wit.maximon.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;

@SuppressLint("ValidFragment")
public class CustomFragment extends Fragment {


    protected Activity parentActivity;

    @SuppressLint("ValidFragment")
    public CustomFragment(final Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    public Activity getParentActivity() {
        return parentActivity;
    }

    public void setParentActivity(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }
}
