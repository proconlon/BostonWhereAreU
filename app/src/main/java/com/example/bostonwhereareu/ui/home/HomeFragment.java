package com.example.bostonwhereareu.ui.home;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.bostonwhereareu.R;
import com.example.bostonwhereareu.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Button to navigate to MapsFragment
        Button button = binding.MAPSButton; // Ensure this ID matches your button ID in fragment_home.xml
        button.setOnClickListener(v -> navigateToMapsFragment());

        Button button1 = binding.randImg;
        button1.setOnClickListener(v -> navigateToRandImg());

        Path path = new Path();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(3);
        path.addCircle(100, 100, 200, Path.Direction.CW);
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
// Draw text on the Canvas along the path
        canvas.drawTextOnPath("womp", path, 0, 0, textPaint);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); // Hide the action bar
        }
    }

    private void navigateToRandImg() {
        // Ensure this action ID matches the one defined in your nav_graph.xml
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_navigation_home_to_rand_img);
    }
    private void navigateToMapsFragment() {
        // Ensure this action ID matches the one defined in your nav_graph.xml
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_navigation_home_to_map_viewer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

