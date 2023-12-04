package com.example.bostonwhereareu.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bostonwhereareu.GameOverFragment;
import com.example.bostonwhereareu.GameState;
import com.example.bostonwhereareu.R;
import com.example.bostonwhereareu.databinding.FragmentDashboardBinding;

import java.util.Collections;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
       /* DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
*/
        //final TextView textView = binding.textDashboard;
       // dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //get username from game over fragment
        ListView usersList = view.findViewById(R.id.usersList);
        List<String> usernames = GameOverFragment.getUsernames();
        if (usernames == null || usernames.isEmpty()) {
            usernames = Collections.singletonList("No BU players yet");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                usernames
        );
        usersList.setAdapter(adapter);

        ListView scoresList = view.findViewById(R.id.scoresList);
        String totalScore = GameOverFragment.tot_score;
        if (totalScore == null || totalScore.isEmpty()) {
            totalScore = "No scores yet";
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                Collections.singletonList(totalScore)
        );
        scoresList.setAdapter(adapter2);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}