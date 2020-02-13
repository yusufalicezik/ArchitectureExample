package com.example.mdbclient.viewmodel;

import android.app.Application;

import com.example.mdbclient.model.Movie;
import com.example.mdbclient.model.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
