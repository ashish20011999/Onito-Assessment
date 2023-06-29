package com.movies.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private String tconst;
    private String primaryTitle;
    private int runtimeMinutes;
    private String genres;
    private double averageRating;
}
