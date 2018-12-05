package edu.wit.maximon.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.util.Log;
import android.widget.Toast;

import edu.wit.maximon.R;


@SuppressLint("ValidFragment")
public class SettingsFragment extends CustomFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @SuppressLint("ValidFragment")
    public SettingsFragment(Activity parentActivity) {
        super(parentActivity);
    }

    //access individual setting preferences
    //EditText maxGoal = (EditText) findViewById(R.id.maxGoal);
    //Switch notification_Toggle = (Switch) findViewById(R.id.notification_Toggle);
    //Switch forceOff_Toggle = (Switch) findViewById(R.id.forceOff_Toggle);

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
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final SharedPreferences preferences = getParentActivity().getSharedPreferences("default_prefs", Context.MODE_PRIVATE);
        final Switch notificationToggle = view.findViewById(R.id.notification_Toggle);
        final EditText maxGoalInput = view.findViewById(R.id.maxGoal);
        maxGoalInput.setText(String.format("%f", preferences.getFloat("max_time", 3)));
        maxGoalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String input = s.toString();
                if(input != null || input.trim().length() > 0) {
                    try {
                        preferences.edit().putFloat("max_time", Float.parseFloat(input.trim())).apply();
                    } catch(final NumberFormatException e) {
                        Log.d("MAXIMON_HEALTH", "Was unable to parse number!");
                        final Toast toast = Toast.makeText(SettingsFragment.this.getContext(), "Was unable to parse number!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        notificationToggle.setChecked(preferences.getBoolean("notifications", false));
        notificationToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences.edit().putBoolean("notifications", isChecked).apply();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onSwitch(Uri uri) {
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
