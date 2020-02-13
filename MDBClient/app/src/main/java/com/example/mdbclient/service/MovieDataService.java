package com.example.mdbclient.service;

import com.example.mdbclient.model.MovieDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey,
                                                     @Query("page") long page);
}
