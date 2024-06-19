package com.abadzheva.movies.ui.main;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.abadzheva.movies.R;
import com.abadzheva.movies.data.model.movie.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnReachEndListener onReachEndListener;
    private OnMovieClickListener onMovieClickListener;

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d("MoviesAdapter", "onBindViewHolder " + position);
        Movie movie = movies.get(position);
        if (movie.getPoster() != null) {
            Glide.with(holder.itemView)
                    .load(movie.getPoster().getUrl())
                    .into(holder.imageViewPoster);
        } else {
            Glide.with(holder.itemView)
                    .load("https://st.kp.yandex.net/images/no-poster.gif")
                    .into(holder.imageViewPoster);
        }
        double rating = movie.getRating().getImdb();
        int backgroundId;
        if (rating >= 7) {
            backgroundId = R.drawable.circle_high_raiting;
        } else if (rating >= 5) {
            backgroundId = R.drawable.circle_middle_raiting;
        } else {
            backgroundId = R.drawable.circle_low_raiting;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewRating.setBackground(background);
        holder.textViewRating.setText(String.valueOf(rating));

        if (position >= movies.size() - 10 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMovieClickListener != null) {
                    onMovieClickListener.onMovieClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface OnReachEndListener {

        void onReachEnd();
    }

    interface OnMovieClickListener {
        void  onMovieClick(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
