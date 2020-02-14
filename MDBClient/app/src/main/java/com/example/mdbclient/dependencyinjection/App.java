package com.example.mdbclient.dependencyinjection;

import android.app.Application;

public class App extends Application {
    private static App app;
    private MovieComponent movieComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        movieComponent = DaggerMovieComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getApp() {
        return app;
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }
}
