package io.github.howiezuo.unsplash;

import android.app.Application;

import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.api.DaggerApiComponent;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.github.howiezuo.unsplash.helper.HelperModule;

public class AppApplication extends Application {

    private static AppApplication sInstance;
    private AppComponent mAppComponent;
    private ApiComponent mApiComponent;
    private HelperComponent mHelperComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
        mHelperComponent = mAppComponent.helperComponent()
                .requestModule(new HelperModule())
                .build();

        mApiComponent = DaggerApiComponent.builder()
                .build();
    }

    public static AppApplication getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public HelperComponent getHelperComponent() {
        return mHelperComponent;
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
