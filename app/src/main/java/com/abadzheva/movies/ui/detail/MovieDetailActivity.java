package com.abadzheva.movies.ui.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.abadzheva.movies.R;
import com.abadzheva.movies.data.model.movie.Movie;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";

    private MovieDetailViewModel viewModel;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;
    private ImageView imageViewFavs;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //------------------------ onCreate ------------------------
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        initViews();

        trailersAdapter = new TrailersAdapter();
        reviewsAdapter = new ReviewsAdapter();
        recyclerViewTrailers.setAdapter(trailersAdapter);
        recyclerViewReviews.setAdapter(reviewsAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        if (movie != null && movie.getPoster() != null) {
            Glide.with(this)
                    .load(movie.getPoster().getUrl())
                    .into(imageViewPoster);
        } else {
            Glide.with(this)
                    .load("https://st.kp.yandex.net/images/no-poster.gif")
                    .into(imageViewPoster);
        }
        textViewTitle.setText(movie != null ? movie.getName() : "Name unavailable");
        textViewYear.setText(movie != null ? String.valueOf(movie.getYear()) : "Year unavailable");
        textViewDescription.setText(movie != null ? movie.getDescription() : "Text unavailable");

        if (movie != null) {
            viewModel.loadTrailers(movie.getId());
        }
        viewModel.getTrailers().observe(this, trailers -> trailersAdapter.setTrailers(trailers));

        trailersAdapter.setOnTrailerClickListener(trailer -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getUrl()));
            startActivity(intent);
        });
        viewModel.getReviews().observe(this, reviewList -> reviewsAdapter.setReviews(reviewList));
        if (movie != null) {
            viewModel.loadReviews(movie.getId());
        }

        Drawable favsOff = ContextCompat.getDrawable(MovieDetailActivity.this,
                R.drawable.ic_favorite_off);
        Drawable favsOn = ContextCompat.getDrawable(MovieDetailActivity.this,
                R.drawable.ic_favorite_on);
        if (movie != null) {
            viewModel.getFavouriteMovie(movie.getId()).observe(this, movieFromDb -> {
                if (movieFromDb == null) {
                    imageViewFavs.setImageDrawable(favsOff);
                    imageViewFavs.setOnClickListener(v -> viewModel.insertMovie(movie));
                } else {
                    imageViewFavs.setImageDrawable(favsOn);
                    imageViewFavs.setOnClickListener(v -> viewModel.removeMovie(movie.getId()));
                }
            });
        }
    }

    public void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewFavs = findViewById(R.id.imageViewFavs);
    }

    @NonNull
    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}