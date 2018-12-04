package edu.wit.maximon.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.wit.maximon.AppInfoAdapter;
import edu.wit.maximon.AppInfoItem;
import edu.wit.maximon.MainActivity;
import edu.wit.maximon.R;

@SuppressLint("ValidFragment")
public class DailyStatsFragment extends CustomFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Calendar currentDate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @SuppressLint("ValidFragment")
    public DailyStatsFragment(Activity parentActivity, final Calendar currentDate) {
        super(parentActivity);
        this.currentDate = currentDate;
    }

    public DailyStatsFragment(){
        super(null);
    }

    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_daily_stats, container, false);
        final List<UsageStats> stats = MainActivity.queryDailyUsageStats(this.getContext(), this.currentDate);
        final SharedPreferences preferences = this.getContext().getSharedPreferences("default_prefs", 0);
        final float maxTime = preferences.getFloat("max_time", 0);
        Log.d("DAY_STAT", String.format("Stats Length: %d", stats.size()));
        long totalTime = 0;
        final Comparator<UsageStats> usageStatsComparator = new Comparator<UsageStats>() {
            @Override
            public int compare(UsageStats o1, UsageStats o2) {
                if (o1.getTotalTimeInForeground() > o2.getTotalTimeInForeground()) {
                    return -1;
                }
                if(o1.getTotalTimeInForeground() == o2.getTotalTimeInForeground()) {
                    return 0;
                }
                return 1;
            }
        };
        Collections.sort(stats, usageStatsComparator);
        final List<AppInfoItem> appInfoItems = new ArrayList<>();
        for(final UsageStats stat : stats) {
            Log.d("DAY_STAT", String.format("Package: %s Time: %s", stat.getPackageName(), stat.getTotalTimeInForeground()));
            totalTime += stat.getTotalTimeInForeground();
            if(stat.getTotalTimeInForeground() <= 5000) {
                continue;
            }
            ApplicationInfo app = null;
            try {
                app = this.getContext().getPackageManager().getApplicationInfo(stat.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            final Drawable icon = this.getContext().getPackageManager().getApplicationIcon(app);
            final String name = this.getContext().getPackageManager().getApplicationLabel(app).toString();
            appInfoItems.add(new AppInfoItem(name, (float)stat.getTotalTimeInForeground()/1000f/60f/60f, icon));
        }
        final float totalTimeInHours = totalTime/1000f/60f/60f;
        final PieChart usageChart = view.findViewById(R.id.usageChart);
        final PieData data = new PieData();
        usageChart.setContentDescription("");
        final List<PieEntry> entries = new ArrayList<>();
        final PieEntry entry = new PieEntry(totalTimeInHours, "Usage");
        final PieEntry entry2 = new PieEntry(maxTime - totalTimeInHours, "Time Left");
        entries.add(entry);
        entries.add(entry2);
        final ListView listView = view.findViewById(R.id.topAppsList);
        listView.setAdapter(new AppInfoAdapter(this.getContext(), R.id.topAppsList, appInfoItems));
        final PieDataSet dataSet = new PieDataSet(entries,"Usage");
        final int color1 = Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(this.getContext(), R.color.colorAccent)));
        final int color2 = Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(this.getContext(), R.color.colorPrimaryLight)));
        dataSet.setColors(color1, color2);
        data.setDataSet(dataSet);
        usageChart.setData(data);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
