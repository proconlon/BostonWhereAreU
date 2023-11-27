package com.example.bostonwhereareu;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.ortiz.touchview.TouchImageView;

public class MapViewerFragment extends Fragment {

    private TextView coordinatesTextView; // for debugging, shows coordinates tapped
    private ImageView mapMarker; // map marker image view
    private TouchImageView touchImageView; // TouchImageView type for the map

    private Button confirmButton; // Button to confirm the marker placement

    public MapViewerFragment() {
        // Required empty public constructor
    }

    public static MapViewerFragment newInstance() {
        return new MapViewerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        touchImageView = view.findViewById(R.id.img_map); // Initialize TouchImageView
        touchImageView.setImageResource(R.drawable.campus_map_temporary);

        coordinatesTextView = view.findViewById(R.id.coordinates_text_view); // Initialize TextView
        mapMarker = view.findViewById(R.id.map_marker); // Initialize ImageView as marker

        mapMarker.setVisibility(View.INVISIBLE);

        confirmButton = view.findViewById(R.id.btn_confirm); // Initialize Confirm Button

        touchImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Drawable drawable = touchImageView.getDrawable();
                    if (drawable == null) return true;

                    int intrinsicWidth = drawable.getIntrinsicWidth();
                    int intrinsicHeight = drawable.getIntrinsicHeight();

                    Matrix imageMatrix = new Matrix(touchImageView.getImageMatrix());
                    float[] values = new float[9];
                    imageMatrix.getValues(values);

                    float relativeX = (event.getX() - values[Matrix.MTRANS_X]) / values[Matrix.MSCALE_X];
                    float relativeY = (event.getY() - values[Matrix.MTRANS_Y]) / values[Matrix.MSCALE_Y];

                    float imageX = relativeX * intrinsicWidth / touchImageView.getWidth();
                    float imageY = relativeY * intrinsicHeight / touchImageView.getHeight();

                    coordinatesTextView.setText("X: " + imageX + ", Y: " + imageY); // Update TextView with coordinates

                    // Position the marker
                    positionMarker(imageX, imageY);
                }
                return true;
            }
        });
    }
    private void positionMarker(float imageX, float imageY) {
        // just makes sure there is a drawable for the marker to be positioned on.
        Drawable drawable = touchImageView.getDrawable();
        if (drawable == null) return;

        // We have to do some math to properly display the marker on screen
        // the raw coordinates of the image are different from where the marker must be moved to (ie actual display coordinates)
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();

        // Convert image coordinates back to view coordinates
        Matrix imageMatrix = new Matrix(touchImageView.getImageMatrix());
        float[] values = new float[9];
        imageMatrix.getValues(values);

        // Adjusting the coordinates relative to the zoom and translation
        float adjustedX = values[Matrix.MTRANS_X] + (imageX / intrinsicWidth) * values[Matrix.MSCALE_X] * touchImageView.getWidth();
        float adjustedY = values[Matrix.MTRANS_Y] + (imageY / intrinsicHeight) * values[Matrix.MSCALE_Y] * touchImageView.getHeight();

        // Adjust the marker to center it on the touch point
        float markerX = adjustedX - mapMarker.getWidth() / 2f;
        float markerY = adjustedY - mapMarker.getHeight() / 2f;
        markerY -= 70; // adjustment so the marker tip is where the screen was tapped

        // Update the position of the marker
        mapMarker.setX(markerX);
        mapMarker.setY(markerY);
        mapMarker.setVisibility(View.VISIBLE);

        // makes confirm button appear when a marker is placed
        confirmButton.setVisibility(View.VISIBLE);
    }


}
