package edu.wit.maximon.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.wit.maximon.MainActivity;
import edu.wit.maximon.R;

@SuppressLint("ValidFragment")
public class HomeFragment extends DailyStatsFragment {

    @SuppressLint("ValidFragment")
    public HomeFragment(Activity parentActivity) {
        super(parentActivity, Calendar.getInstance());
    }

    public HomeFragment(){
        super(null, Calendar.getInstance());
    }


}
