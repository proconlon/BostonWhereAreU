package com.example.bostonwhereareu;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.ortiz.touchview.TouchImageView;

public class MapViewerFragment extends Fragment {

    private TextView coordinatesTextView; // TextView to display coordinates

    public MapViewerFragment() {
        // Required empty public constructor
    }

    public static MapViewerFragment newInstance() {
        return new MapViewerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TouchImageView touchImageView = view.findViewById(R.id.img_map);
        touchImageView.setImageResource(R.drawable.campus_map_temporary);

        coordinatesTextView = view.findViewById(R.id.coordinates_text_view);

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
                }
                return true;
            }
        });
    }
}
