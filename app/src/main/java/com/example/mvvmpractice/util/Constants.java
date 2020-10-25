package com.example.mvvmpractice.util;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Constants {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original";
    public static final String API_KEY = "c9cc10ebf73275af052b57efc03dfdf4";

    @Retention(SOURCE)
    @StringDef
    public @interface Type {
        String MOVIES = "movie";
        String TV_SHOWS = "tv";
    }
}
