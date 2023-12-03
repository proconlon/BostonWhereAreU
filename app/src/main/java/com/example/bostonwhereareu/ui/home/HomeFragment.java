package com.example.bostonwhereareu.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Button to navigate to MapsFragment
        Button button = binding.MAPSButton; // Ensure this ID matches your button ID in fragment_home.xml
        button.setOnClickListener(v -> navigateToMapsFragment());

        Button button1 = binding.randImg;
        button1.setOnClickListener(v->navigateToRandImg());
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
