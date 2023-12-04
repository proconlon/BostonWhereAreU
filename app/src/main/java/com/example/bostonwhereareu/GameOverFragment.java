package com.example.bostonwhereareu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class GameOverFragment extends Fragment {

    public GameOverFragment() {
        // Required empty public constructor
    }
    public static List<String> getUsernames() {
        return usernames;
    }
    private EditText usernameInput;
    private Button submitUsername;
    private static List<String> usernames = new ArrayList<>();
    public static String username;
    public static String tot_score;

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

        //Ask for username to put on leaderboard
        usernameInput = view.findViewById(R.id.usernameInput);
        submitUsername = view.findViewById(R.id.submitUsername);

        submitUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tot_score = String.valueOf(gameState.getTotalScore());
                username = usernameInput.getText().toString();
                usernames.add(username);
                NavHostFragment.findNavController(GameOverFragment.this)
                        .navigate(R.id.action_game_over_to_navigation_home);
            }
        });


        return view;
    }
}
