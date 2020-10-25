package com.example.mvvmpractice.network;

import com.example.mvvmpractice.model.CastResponse;
import com.example.mvvmpractice.model.GenreResponse;
import com.example.mvvmpractice.model.MovieResponse;
import com.example.mvvmpractice.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoints {
    @GET("discover/movie") //get Movies collection
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")  //https://developers.themoviedb.org/3/discover/tv-discover
    Call<TvShowResponse> getTvShow(@Query("api_key")String apiKey);

    @GET("movie/{movie_id}") //https://developers.themoviedb.org/3/movies/get-movie-details
    Call<GenreResponse> getGenre(@Path("id") int id, @Query("api_key")String apiKey);

    @GET("{type}/{id}") // get details and genres of specific movie / tv
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/credits") // get casts of specific movie / tv shows
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);
}
