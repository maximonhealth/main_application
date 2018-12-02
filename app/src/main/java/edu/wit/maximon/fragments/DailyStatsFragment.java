package edu.wit.maximon.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        Log.d("DAY_STAT", String.format("Stats Length: %d", stats.size()));
        for(final UsageStats stat : stats) {
            Log.d("DAY_STAT", String.format("Package: %s Time: %s", stat.getPackageName(), stat.getTotalTimeInForeground()));
        }
        final PieChart usageChart = view.findViewById(R.id.usageChart);
        final PieData data = new PieData();
        final List<PieEntry> entries = new ArrayList<>();
        final PieEntry entry = new PieEntry(66, "Test 1");
        final PieEntry entry2 = new PieEntry(33, "Test 2");
        entries.add(entry);
        entries.add(entry2);
        final PieDataSet dataSet = new PieDataSet(entries,"Usage");
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
