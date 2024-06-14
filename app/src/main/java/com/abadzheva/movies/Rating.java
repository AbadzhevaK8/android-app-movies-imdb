package com.abadzheva.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("imdb")
    private String imdb;

    public Rating(String imdb) {
        this.imdb = imdb;
    }

    public String getImdb() {
        return imdb;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "imdb='" + imdb + '\'' +
                '}';
    }
}
