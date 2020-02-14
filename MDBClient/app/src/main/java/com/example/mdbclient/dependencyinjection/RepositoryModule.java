package com.example.mdbclient.dependencyinjection;

import android.app.Application;

import com.example.mdbclient.model.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(Application application) {
        return new MovieRepository(application);
    }

}
