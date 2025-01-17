package com.example.dagger.di;

import android.app.Application;
import com.example.dagger.BaseApplication;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ActivityBuildersModule.class, AppModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
