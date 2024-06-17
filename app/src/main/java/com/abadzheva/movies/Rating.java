package com.abadzheva.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("imdb")
    private double imdb;

    public Rating(double imdb) {
        this.imdb = imdb;
    }

    public double getImdb() {
        return imdb;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "imdb='" + imdb + '\'' +
                '}';
    }
}
