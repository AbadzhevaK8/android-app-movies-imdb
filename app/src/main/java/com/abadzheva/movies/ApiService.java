package com.abadzheva.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

//    @GET("movie?token=GYPN980-J0W41K5-GRS6XZD-MTQSN9W&limit=40&sortField=votes.imdb&sortType=-1&rating.imdb=5-10")
    @GET("movie?token=GYPN980-J0W41K5-GRS6XZD-MTQSN9W&limit=30&sortField=votes.imdb&sortType=-1&rating.imdb=4-10")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
