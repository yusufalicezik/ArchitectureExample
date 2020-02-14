package com.example.mdbclient.model;


import android.app.Application;

import com.example.mdbclient.dependencyinjection.App;
import com.example.mdbclient.service.MovieDataService;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends MovieDataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieDataService movieDataService;
    private MutableLiveData<MovieDataSource> mutableLiveData;
    @Inject
    public Application application;

    public MovieDataSourceFactory(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        App.getApp().getMovieComponent().inject(this);
        movieDataSource = new MovieDataSource(movieDataService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
