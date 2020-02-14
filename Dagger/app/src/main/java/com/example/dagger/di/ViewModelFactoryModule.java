package com.example.dagger.di;

import com.example.dagger.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelProviderFactory modelProviderFactory);

}
