package com.example.mdbclient.viewmodel;

import com.example.mdbclient.model.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@Singleton
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository movieRepository;

    @Inject
    public MainActivityViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(movieRepository);
    }
}
