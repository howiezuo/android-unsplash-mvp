package howiezuo.github.io.unsplash;

import android.app.Application;

import howiezuo.github.io.unsplash.api.ApiComponent;
import howiezuo.github.io.unsplash.api.DaggerApiComponent;

public class AppApplication extends Application {

    private static AppApplication sInstance;
    private AppComponent mAppComponent;
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);

        mApiComponent = DaggerApiComponent.builder()
                .build();
    }

    public static AppApplication getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
