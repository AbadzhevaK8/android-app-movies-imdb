package com.abadzheva.movies.data.model.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

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
