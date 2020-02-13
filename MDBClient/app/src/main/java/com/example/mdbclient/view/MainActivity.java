package com.example.mdbclient.view;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.mdbclient.R;
import com.example.mdbclient.adapter.MovieAdapter;
import com.example.mdbclient.model.Movie;
import com.example.mdbclient.model.MovieDBResponse;
import com.example.mdbclient.service.MovieDataService;
import com.example.mdbclient.service.RetrofitInstance;
import com.example.mdbclient.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        getPopularMovies();
        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    public void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesLiveData) {
                movies = (ArrayList<Movie>) moviesLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
