package com.example.dagger;
import com.example.dagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return (AndroidInjector<? extends DaggerApplication>)
                DaggerAppComponent.builder().application(this).build();
    }
}
