package edu.wit.maximon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class DailyStatsActivity extends AppCompatActivity {

    private Calendar calendar;

    public DailyStatsActivity(final Calendar calendar) {
        this.calendar = calendar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stats);
    }


}
