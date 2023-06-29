package com.movies.Service;

import com.movies.Models.GenreMoviesSubtotals;
import com.movies.Models.Movie;
import com.movies.Repository.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements MovieServiceInterface {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getLongestDurationMovies() {
        return movieRepository.getLongestDurationMovies();
    }

    public void addNewMovie(Movie movie) {
        movieRepository.addNewMovie(movie);
    }

    public List<Movie> getTopRatedMovies() {
        return movieRepository.getTopRatedMovies();
    }

    public List<GenreMoviesSubtotals> getGenreMoviesWithSubtotals() {
        return movieRepository.getGenreMoviesWithSubtotals();
    }

    public String updateRunTimeMinutes() {
        return movieRepository.updateRunTimeMinutes();
    }
}