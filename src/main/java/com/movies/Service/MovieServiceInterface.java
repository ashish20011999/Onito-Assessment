package com.movies.Service;

import com.movies.Models.GenreMoviesSubtotals;
import com.movies.Models.Movie;

import java.util.List;

public interface MovieServiceInterface {
    List<Movie> getLongestDurationMovies();

    void addNewMovie(Movie movie);

    List<Movie> getTopRatedMovies();

    List<GenreMoviesSubtotals> getGenreMoviesWithSubtotals();

    public String updateRunTimeMinutes();
}
