# BioScope

BioScope is an Android application that allows users to discover popular and top-rated movies. It uses the [The Movie Database (TMDb) API](https://www.themoviedb.org/documentation/api) to fetch movie data.

## Features

* Discover popular and top-rated movies.
* View movie details, including synopsis, release date, and ratings.
* Watch movie trailers.
* Read movie reviews.
* Add movies to a favorites list for offline access.

## Setup

To build and run this project, you will need to provide your own API key for The Movie Database.

1.  **Get an API key:**
    *   Go to [themoviedb.org](https://www.themoviedb.org).
    *   Create an account and request an API key.

2.  **Add the API key to the project:**
    *   Open the `gradle.properties` file in the root of the project.
    *   Add the following line, replacing `YOUR_API_KEY` with your actual API key:

        ```
        MovieDBApiKey="YOUR_API_KEY"
        ```

3.  **Build and run the app:**
    *   Open the project in Android Studio.
    *   Build and run the app on an Android device or emulator.

## Usage

*   The main screen displays a grid of movies.
*   Use the menu to sort the movies by "Most Popular" or "Top Rated".
*   You can also view your "Favorites" from the menu.
*   Tap on a movie poster to view its details.
*   In the details screen, you can view the movie's synopsis, rating, trailers, and reviews.
*   Tap the star icon to add a movie to your favorites.

## Project Structure

The project is organized into the following packages under `app/src/main/java/za/co/gundula/app/bioscope/`:

*   **`adapter`**: Contains the RecyclerView adapters for displaying movies, reviews, and trailers.
*   **`data`**: Contains the database helper, content provider, and contract class for managing the local data store.
*   **`model`**: Contains the data model classes that represent movies, reviews, and trailers.
*   **`rest`**: Contains the Retrofit API client and interface for communicating with The Movie Database API.
*   **`utils`**: Contains utility classes and constants used throughout the application.
