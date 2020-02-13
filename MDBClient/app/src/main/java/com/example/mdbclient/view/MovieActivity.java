package com.example.mdbclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mdbclient.R;
import com.example.mdbclient.model.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;

    private ImageView movieImage;

    private String image;

    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movieImage = (ImageView) findViewById(R.id.ivMovieLarge);
        movieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        movieSynopsis = (TextView) findViewById(R.id.tvPlotsynopsis);
        movieRating = (TextView) findViewById(R.id.tvMovieRating);
        movieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);


        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {

            movie = getIntent().getParcelableExtra("movie");

            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_LONG).show();

            image = movie.getPosterPath();

            String path = "https://image.tmdb.org/t/p/w500" + image;

            Glide.with(this)
                    .load(path)
                    .placeholder(R.drawable.loading)
                    .into(movieImage);

            getSupportActionBar().setTitle(movie.getTitle());

            movieTitle.setText(movie.getTitle());
            movieSynopsis.setText(movie.getOverview());
            movieRating.setText(Double.toString(movie.getVoteAverage()));
            movieReleaseDate.setText(movie.getReleaseDate());
        }

    }


}
