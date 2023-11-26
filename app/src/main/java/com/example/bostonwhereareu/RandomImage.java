package com.example.bostonwhereareu;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class RandomImage extends Fragment {
    ImageView imageView3;
    Button rand_button;
    Random rand;
    int[] images = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
    };

    @Override

   // public void onCreate(Bundle savedInstanceState){
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.random_img, container, false);

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.random_img);

        //imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3 = view.findViewById(R.id.imageView3);
        rand = new Random();
        rand_button = view.findViewById(R.id.rand_button);
        //rand_button = (Button) findViewById(R.id.rand_button);
        rand_button.setVisibility(View.VISIBLE);

        rand_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView3.setImageResource(images[rand.nextInt(images.length)]);
            }


        });

        return view;
    }

}
