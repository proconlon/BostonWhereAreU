package com.example.bostonwhereareu;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Map;
import java.util.Random;

public class RandomImage extends Fragment {
     TextView countTime;
     private static final long Start_Time = 10000;
     private long time_left = Start_Time;
     CountDownTimer countDown;
    private boolean is_running;
    ImageView imageView3;
    Button to_map_viewer;
    Random rand;

    private int ImageIndex;
    int[] images = {
            R.drawable.cas,
            R.drawable.cds,
            R.drawable.fenwaypark,
            R.drawable.gsu,
            R.drawable.kenmore,
            R.drawable.marciano,
            R.drawable.marshchapel,
            R.drawable.myles,
            R.drawable.questrom,
            R.drawable.warren,
            R.drawable.westcampus
    };
    int[] xcoords = {
          370,//cas
          445,//cds
          557,//fenway park
          337,//gsu
          569,//kenmore
          539,//marciano
          376,//marsh chapel
          625,//myles
          509,//questrom
          423,//warren
          161//west campus
    };
    int[] ycoords = {
            138,//cas
            205,//cds
            296,//fenway park
            185,//gsu
            247,//kenmore
            208,//marciano
            187,//marsh chapel
            221,//myles
            215,//questrom
            226,//warren
            171//west campus
    };
    public RandomImage() {
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.random_img, container, false);



        imageView3 = view.findViewById(R.id.imageView3);
        rand = new Random();
        to_map_viewer = view.findViewById(R.id.action_rand_img_to_map_viewer);

        //Change targetX and targetY to location values
        ImageIndex = rand.nextInt(images.length);
        imageView3.setImageResource(images[ImageIndex]);

        MapViewerFragment.targetX = xcoords[ImageIndex];
        MapViewerFragment.targetY = ycoords[ImageIndex];

        //Create Timer
        countTime = view.findViewById(R.id.count_time);
        countDown = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the countdown TextView every second
                countTime.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                FragmentManager fragMan = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragMan.beginTransaction();
                ft.setReorderingAllowed(true);
                ft.replace(R.id.nav_host_fragment_activity_main, new MapViewerFragment());
                ft.commit();
            }//Go to guess screen when time is up
        }.start();


        to_map_viewer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //imageView3.setImageResource(images[rand.nextInt(images.length)]);
                FragmentManager fragMan = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragMan.beginTransaction();
                ft.setReorderingAllowed(true);
                ft.replace(R.id.nav_host_fragment_activity_main, new MapViewerFragment());
                ft.commit();
                }//goes to guess screen

        });

        return view;
    }

}
