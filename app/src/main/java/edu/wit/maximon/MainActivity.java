package edu.wit.maximon;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wit.maximon.fragments.CustomFragment;
import edu.wit.maximon.fragments.HomeFragment;
import edu.wit.maximon.fragments.OnFragmentInteractionListener;
import edu.wit.maximon.fragments.SettingsFragment;
import edu.wit.maximon.fragments.TimelineFragment;

public class MainActivity extends ActivityWrapper implements OnFragmentInteractionListener {


    private static int selectedTab = R.id.navigation_home;

    private Map<Integer,CustomFragment> fragmentMap = new HashMap<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectedTab = item.getItemId();
            applyFragment(fragmentMap.get(selectedTab), R.id.fragment);
            return true;
        }
    };

    private void applyFragment(final CustomFragment fragment, final int id) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment.setParentActivity(this);
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMap.put(R.id.navigation_timeline, new TimelineFragment(this));
        fragmentMap.put(R.id.navigation_home, new HomeFragment(this));
        fragmentMap.put(R.id.navigation_settings, new SettingsFragment(this));
        final CustomFragment fragment = (CustomFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(fragment.getParentActivity() == null) {
            fragment.setParentActivity(this);
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(selectedTab);
        applyFragment(fragmentMap.get(selectedTab), R.id.fragment);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public static List<UsageStats> queryDailyUsageStats(final Context context, final Calendar calendarDay) {
        return queryDailyUsageStats(context, calendarDay,false);
    }

    public static List<UsageStats> queryDailyUsageStats(final Context context, final Calendar calendarDay, final boolean completeDay) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        final Calendar calendar = floorToMidnight(calendarDay);
        if(completeDay) {
            Log.d("CAL_COMP", "REACHED");
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        }

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        Log.d("CAL_INFO", simpleDateFormat.format(calendar.getTime()));
        return usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, calendar.getTimeInMillis(), calendarDay.getTimeInMillis());
    }

    public static Calendar floorToMidnight(final Calendar calendar) {
        final Calendar clone = (Calendar)calendar.clone();
        clone.set(Calendar.HOUR, 0);
        clone.set(Calendar.MINUTE, 0);
        clone.set(Calendar.SECOND, 0);
        clone.set(Calendar.MILLISECOND, 0);
        return clone;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
