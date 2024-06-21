package com.abadzheva.movies.ui.favourite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abadzheva.movies.R;
import com.abadzheva.movies.ui.detail.MovieDetailActivity;
import com.abadzheva.movies.ui.main.MoviesAdapter;

public class FavouriteMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favourite_movies);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerViewMovies), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // -------------------- onCreate --------------------
        RecyclerView recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        MoviesAdapter moviesAdapter = new MoviesAdapter();
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMovies.setAdapter(moviesAdapter);
        moviesAdapter.setOnMovieClickListener(movie -> {
            Intent intent = MovieDetailActivity.newIntent(
                    FavouriteMoviesActivity.this,
                    movie
            );
            startActivity(intent);
        });

        FavouriteMoviesViewModel viewModel = new ViewModelProvider(this)
                .get(FavouriteMoviesViewModel.class);
        viewModel.getMovies().observe(this, moviesAdapter::setMovies);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMoviesActivity.class);
    }
}