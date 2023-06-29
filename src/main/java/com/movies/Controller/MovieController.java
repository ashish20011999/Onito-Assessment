package com.movies.Controller;

import com.movies.Models.GenreMoviesSubtotals;
import com.movies.Models.Movie;
import com.movies.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/longest-duration-movies")
    public List<Movie> getLongestDurationMovies() {
        return movieService.getLongestDurationMovies();
    }

    @PostMapping("/new-movie")
    public String addNewMovie(@RequestBody Movie movie) {
        movieService.addNewMovie(movie);
        return "success";
    }

    @GetMapping("/top-rated-movies")
    public List<Movie> getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }

    @GetMapping("/genre-movies-with-subtotals")
    public List<GenreMoviesSubtotals> getGenreMoviesWithSubtotals() {
        return movieService.getGenreMoviesWithSubtotals();
    }

    @PostMapping("/update-runtime-minutes")
    public String updateRuntimeMinutes() {
        return movieService.updateRunTimeMinutes();
    }
}
