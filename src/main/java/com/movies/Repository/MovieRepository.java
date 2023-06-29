package com.movies.Repository;

import com.movies.Models.GenreMoviesSubtotals;
import com.movies.Models.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRepository implements MovieRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getLongestDurationMovies() {
        String sql = "SELECT tconst, primaryTitle, runtimeMinutes, genres FROM public.movies ORDER BY runtimeMinutes DESC LIMIT 10";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Movie movie = new Movie();
            movie.setTconst(rs.getString("tconst"));
            movie.setPrimaryTitle(rs.getString("primaryTitle"));
            movie.setRuntimeMinutes(rs.getInt("runtimeMinutes"));
            movie.setGenres(rs.getString("genres"));
            return movie;
        });
    }

    public void addNewMovie(Movie movie) {
        String sql = "INSERT INTO public.movies (tconst, primaryTitle, runtimeMinutes, genres) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, movie.getTconst(), movie.getPrimaryTitle(), movie.getRuntimeMinutes(), movie.getGenres());
        String sql1 = "INSERT INTO public.ratings (tconst,averageRating,numVotes) VALUES (?,?,?)";
        jdbcTemplate.update(sql1, movie.getTconst(), movie.getAverageRating(), 1);
    }

    public List<Movie> getTopRatedMovies() {
        String sql = "SELECT m.tconst, m.primaryTitle, m.genres, r.averageRating " +
                "FROM public.movies m " +
                "JOIN ratings r ON m.tconst = r.tconst " +
                "WHERE r.averageRating > 6.0 " +
                "ORDER BY r.averageRating DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Movie movie = new Movie();
            movie.setTconst(rs.getString("tconst"));
            movie.setPrimaryTitle(rs.getString("primaryTitle"));
            movie.setGenres(rs.getString("genres"));
            movie.setAverageRating(rs.getDouble("averageRating"));
            return movie;
        });
    }

    public List<GenreMoviesSubtotals> getGenreMoviesWithSubtotals() {
        String sql = "SELECT m.genres, SUM(r.numVotes) AS numVotes " +
                "FROM public.movies m " +
                "JOIN ratings r ON m.tconst = r.tconst " +
                "GROUP BY m.genres";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            GenreMoviesSubtotals subtotals = new GenreMoviesSubtotals();
            subtotals.setGenre(rs.getString("genres"));
            subtotals.setNumVotes(rs.getInt("numVotes"));
            return subtotals;
        });
    }

    public String updateRunTimeMinutes() {
        String sql = "UPDATE movies " +
                "SET runtimeMinutes = CASE " +
                "    WHEN genres = 'Documentary' THEN runtimeMinutes + 15 " +
                "    WHEN genres = 'Animation' THEN runtimeMinutes + 30 " +
                "    ELSE runtimeMinutes + 45 " +
                "END";

        jdbcTemplate.update(sql);
        return "Runtime minutes updated successfully";
    }
}