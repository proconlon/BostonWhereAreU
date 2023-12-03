package com.example.bostonwhereareu;

// keeps track of game state, locations visited, and score

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameState {
    private static final GameState instance = new GameState();
    private static final int ROUNDS = 5+2; // number of rounds in a game
    private int currentRound = 0;
    private int totalScore = 0;

    // array list of string data type to store location for each round
    private List<String> visitedLocations = new ArrayList<>();
    private List<LocationData> locationList = new ArrayList<>();
    private LocationData currentLocation;

    // constructor
    private GameState() {
        locationList.add(new LocationData(1684, 981, "CAS"));
        locationList.add(new LocationData(1812, 1015, "CDS"));
        locationList.add(new LocationData(2235, 1352, "Fenway Park"));
        locationList.add(new LocationData(1387, 929, "GSU"));
        locationList.add(new LocationData(2341, 1111, "Kenmore"));
        locationList.add(new LocationData(2190, 1017, "Marciano"));
        locationList.add(new LocationData(1570, 954, "Marsh Chapel"));
        locationList.add(new LocationData(2432, 1035, "Myles"));
        locationList.add(new LocationData(2051, 1044, "Questrom"));
        locationList.add(new LocationData(1748, 1077, "Warren"));
        locationList.add(new LocationData(757, 868, "West Campus"));

        Collections.shuffle(locationList, new Random());
    }
    public void startRound() {
        if (currentRound < ROUNDS) {
            // Ensure the round number is within the bounds of the location list
            currentLocation = locationList.get(currentRound);
            visitedLocations.add(currentLocation.getPlaceName());
            currentRound++; // Increment the round number for the next call
        } else {
            // Handle the case where all rounds are complete
            currentLocation = null; // or set it to a default LocationData object
        }
    }

    public static GameState getInstance() {
        return instance;
    }

    public LocationData getCurrentLocation() {
        return currentLocation;
    }

    public void incrementRound() {
        currentRound++;
    }

    public void addScore(int roundScore) {
        totalScore += roundScore;
    }

    public void addVisitedLocation(String locationName) {
        visitedLocations.add(locationName);
    }

    public List<String> getVisitedLocations() {
        return visitedLocations;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getTotalScore() {
        return totalScore;
    }

    // returns true if game is over
    public boolean isGameOver() {
        return currentRound > ROUNDS;
    }

    // Reset game state for a new game
    public void resetGame() {
        currentRound = 1;
        totalScore = 0;
        visitedLocations.clear(); // Clear the arraylist
    }
}
