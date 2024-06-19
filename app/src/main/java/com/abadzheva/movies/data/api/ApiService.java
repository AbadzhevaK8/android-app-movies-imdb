package com.abadzheva.movies.data.api;

import com.abadzheva.movies.data.model.movie.MovieResponse;
import com.abadzheva.movies.data.model.review.ReviewResponse;
import com.abadzheva.movies.data.model.trailer.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

//    @GET("movie?token=GYPN980-J0W41K5-GRS6XZD-MTQSN9W&limit=40&sortField=votes.imdb&sortType=-1&rating.imdb=5-10&notNullFields=videos.trailers.url")
    @GET("movie?token=GYPN980-J0W41K5-GRS6XZD-MTQSN9W&limit=30&sortField=votes.imdb&sortType=-1&rating.imdb=4-10")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie/{id}?token=GYPN980-J0W41K5-GRS6XZD-MTQSN9W")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);

    @GET("review?token=GYPN980-J0W41K5-GRS6XZD-MTQSN9W&page=1&limit=10")
    Single<ReviewResponse> loadReviews(@Query("movieId") int movieId);
}


