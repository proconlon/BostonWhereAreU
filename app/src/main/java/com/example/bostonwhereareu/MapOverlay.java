package com.example.bostonwhereareu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

public class MapOverlay extends View {
    private Paint paint; // paint object for drawing lines and circles
    public boolean isMarkerConfirmed = false;

    // viewer locations of marker and target (for line drawing)
    private float viewMarkerX, viewMarkerY;
    private float viewTargetX, viewTargetY;
    private float score;

    // code constructor
    public MapOverlay(Context context) {
        super(context);
        init();
    }

    // xml constructor
    public MapOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2f);
    }

    // Calculate score based on distance between marker and target
    protected int calculateScore() {
        float distance = distanceBetweenPoints(MapViewerFragment.rawX, MapViewerFragment.rawY, MapViewerFragment.targetX, MapViewerFragment.targetY);

        // defined r<15 as max score (1000)
        // defined r>500 as min score (0)
        if (distance <= 40) {
            return 1000;
        } else if (distance > 6000) {
            return 0;
        } else {
            // Decrease score as distance increases
            return Math.max(0, 1000 - (int)(distance * 2)); // Score decreases from 1000 to 0 for distances from 15 to 500
        }
    }

    private float distanceBetweenPoints(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void confirmMarkerAndTarget(float viewMarkerX, float viewMarkerY, float viewTargetX, float viewTargetY) {
        isMarkerConfirmed = true; // Set the marker as confirmed
        this.viewMarkerX = viewMarkerX;
        this.viewMarkerY = viewMarkerY;
        this.viewTargetX = viewTargetX;
        this.viewTargetY = viewTargetY;
        invalidate(); // Redraw the view

        // Calculate score
        score = calculateScore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isMarkerConfirmed) {
            // Draw a circle around the marker
            canvas.drawCircle(viewTargetX, viewTargetY, 15, paint);

            // Draw a line to the target point
            canvas.drawLine(viewMarkerX, viewMarkerY, viewTargetX, viewTargetY, paint);

            // display score
            paint.setTextSize(65f);
            int color = ContextCompat.getColor(getContext(), R.color.black);
            paint.setColor(color);
            paint.setStrokeWidth(10);
            canvas.drawText("Score: " + score, 400f, 1690f, paint);

            // display distance in feet or other things

        }
    }

}