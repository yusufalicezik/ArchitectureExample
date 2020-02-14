package com.example.dagger.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {ActivityBuildersModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
