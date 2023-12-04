# Overview
*Boston Where Are U* is a dynamic Android game designed for Boston University (BU) enthusiasts and Terriers. It challenges players to identify BU landmarks on a map using images. The player guesses the location on a BU map and the app should calculate a score based on the distance between the guessed location and the actual location of the image. This game will also include a leaderboard of top BU guessers.

This game is ideal for students, alumni, and visitors, offering an entertaining way to learn about the campus. Helps players familiarize themselves with BU's campus through engaging gameplay. Unlike general map-based games, it specifically targets the BU campus, offering a personalized experience.

# Functionality
* Full pan and zoom of a map using TouchImageView class as well as a custom coordinate system for adding locations.
* Leaderboard using external [API](https://github.com/daifengxin/bonstonwhereareu-backend)
* Game management of visited locations with a link to more information

# Building App
This app was created using Android Studio.

To build app, follow these steps:

1. **Clone the Repository**: in Android Studio or in a new directory
   ```
   git clone https://github.com/proconlon/BostonWhereAreU
   ```
2. **Open Project**: Open project in Android Studio 
   
3. **Gradle Configuration**: External class TouchImageView has already been included in the build.gradle file so you do not need to add it. Everything you need to build is in this repo.

4. **Build the Project**: Build using Android Studio `Build --> Make Project`
   
5. **Run App**: Can be done using Android Studio's emulator or your own Android device.

## Using TouchImageView

We use `TouchImageView` in the `MapViewerFragment` class. Here is the original repo: https://github.com/MikeOrtiz/TouchImageView 

## External Resources

We use our own API for maintaining a leaderboard. https://github.com/daifengxin/bonstonwhereareu-backend

We also use open source map assets from [Stamen Design](https://watercolormaps.collection.cooperhewitt.org/#12/42.3555/-71.0486).

# App Images
## Home Screen
![Screenshot_20231204-142057](https://github.com/proconlon/BostonWhereAreU/assets/66924033/844a3038-9bc0-4261-9bb7-4d8233a1dde2)

## Guess Page
![Screenshot_20231204-142106](https://github.com/proconlon/BostonWhereAreU/assets/66924033/30b4e7b8-7752-42ef-a70f-20818c60676f)

## Map Marker and Score
![Screenshot_20231204-142121](https://github.com/proconlon/BostonWhereAreU/assets/66924033/6d2add46-9d51-464a-8826-91b766f67b7d)

## More Info Page
![Screenshot_20231204-142124](https://github.com/proconlon/BostonWhereAreU/assets/66924033/6673d159-abf4-44f0-9daf-53f778ec5892)

## Game Over Screen
![Screenshot_20231204-142226](https://github.com/proconlon/BostonWhereAreU/assets/66924033/5318940a-6ac5-421c-a48b-1ddc727e1065)

## Leaderboard
![Screenshot_20231204-142233](https://github.com/proconlon/BostonWhereAreU/assets/66924033/e1d9ca3d-1ce9-40ad-b677-a2fc4e7539e1)
