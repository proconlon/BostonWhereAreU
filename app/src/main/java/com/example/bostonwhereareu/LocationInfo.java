package com.example.bostonwhereareu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import androidx.fragment.app.Fragment;

    public class LocationInfo extends DialogFragment {

        // the fragment goes to one of the xml files for a location.
        // the xml files are in app/src/main/res/layout called location_XXXX.xml

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Get the current location from GameState class
            GameState gameState = GameState.getInstance();

            //get the name of the current location
            String currentLocation = gameState.getCurrentLocation().getPlaceName();

            // based on the name of the current location, go to the corresponding xml file with a case statement
            int layoutResId = getLayoutResId(currentLocation);

            View view = inflater.inflate(layoutResId, container, false);

            Button leaveButton = view.findViewById(R.id.btn_leave);
            leaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss(); // Dismiss the dialog
                }
            });

            return view;
        }

        private int getLayoutResId(String locationName) {
            switch (locationName) {
                case "CAS":
                    return R.layout.location_cas;
                case "CDS":
                    return R.layout.location_cds;
                case "Fenway Park":
                    return R.layout.location_fenwaypark;
                case "GSU":
                    return R.layout.location_gsu;
                case "Kenmore":
                    return R.layout.location_kenmore;
                case "Marciano":
                    return R.layout.location_marciano;
                case "Marsh Chapel":
                    return R.layout.location_marshchapel;
                case "Myles":
                    return R.layout.location_myles;
                case "Questrom":
                    return R.layout.location_questrom;
                case "Warren":
                    return R.layout.location_warren;
                case "West Campus":
                    return R.layout.location_westcampus;
                default:
                    return R.layout.location_cas; // default
            }
        }
    }