package com.movies.Repository;

import com.movies.Models.GenreMoviesSubtotals;
import com.movies.Models.Movie;

import java.util.List;

public interface MovieRepositoryInterface {
    List<Movie> getLongestDurationMovies();

    void addNewMovie(Movie movie);

    List<Movie> getTopRatedMovies();

    List<GenreMoviesSubtotals> getGenreMoviesWithSubtotals();

    public String updateRunTimeMinutes();
}
