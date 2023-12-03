package com.example.bostonwhereareu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameOverFragment extends Fragment {

    public GameOverFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_over, container, false);

        // Get game state
        GameState gameState = GameState.getInstance();

        // Set score
        TextView scoreTextView = view.findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameState.getTotalScore());

        // Set list of visited locations
        ListView locationsListView = view.findViewById(R.id.locationsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                gameState.getVisitedLocations()
        );
        locationsListView.setAdapter(adapter);

        return view;
    }
}
