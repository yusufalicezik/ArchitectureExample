package com.example.mdbclient.viewmodel;

import android.app.Application;

import com.example.mdbclient.model.Movie;
import com.example.mdbclient.model.MovieDataSource;
import com.example.mdbclient.model.MovieDataSourceFactory;
import com.example.mdbclient.model.MovieRepository;
import com.example.mdbclient.service.MovieDataService;
import com.example.mdbclient.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor; //for thread
    private LiveData<PagedList<Movie>> moviesPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
