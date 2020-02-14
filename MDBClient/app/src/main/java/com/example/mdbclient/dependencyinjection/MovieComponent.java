package com.example.mdbclient.dependencyinjection;

import com.example.mdbclient.model.MovieDataSourceFactory;
import com.example.mdbclient.view.MainActivity;
import com.example.mdbclient.viewmodel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface MovieComponent {
    void inject(MainActivity activity);
    void inject(MovieDataSourceFactory factory);
}
