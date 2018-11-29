package edu.wit.maximon;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import edu.wit.maximon.fragments.CustomFragment;
import edu.wit.maximon.fragments.HomeFragment;
import edu.wit.maximon.fragments.OnFragmentInteractionListener;
import edu.wit.maximon.fragments.SettingsFragment;
import edu.wit.maximon.fragments.TimelineFragment;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_timeline:
                    applyFragment(new TimelineFragment(MainActivity.this), R.id.fragment);
                    break;
                case R.id.navigation_home:
                    applyFragment(new HomeFragment(MainActivity.this), R.id.fragment);
                    break;
                case R.id.navigation_settings:
                    applyFragment(new SettingsFragment(MainActivity.this), R.id.fragment);
                    break;
            }
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
        final CustomFragment fragment = (CustomFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(fragment.getParentActivity() == null) {
            fragment.setParentActivity(this);
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
