package io.github.howiezuo.unsplash;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.io.IOException;
import java.util.Properties;

import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.api.DaggerApiComponent;
import io.github.howiezuo.unsplash.database.DatabaseComponent;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.realm.Realm;

public class AppApplication extends Application {

    private static AppApplication sInstance;

    private String sClientId;
    private String sClientSecret;

    private AppComponent mAppComponent;
    private DatabaseComponent mDatabaseComponent;
    private HelperComponent mHelperComponent;
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        // Private config
        Properties properties = new Properties();
        try {
            properties.load(getAssets().open("private.properties"));
            sClientId = properties.getProperty("client.id");
            sClientSecret = properties.getProperty("client.secret");
        } catch (IOException e) {
            Logger.e(e.getMessage());
            e.printStackTrace();
        }

        // Realm
        Realm.init(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        // Dagger components
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
        mDatabaseComponent = mAppComponent.databaseComponent()
                .build();
        mHelperComponent = mAppComponent.helperComponent()
                .build();

        mApiComponent = DaggerApiComponent.builder()
                .helperComponent(mHelperComponent)
                .build();
    }

    public static AppApplication getInstance() {
        return sInstance;
    }

    public String getClientId() {
        return sClientId;
    }

    public String getClientSecret() {
        return sClientSecret;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public DatabaseComponent getDatabaseComponent() {
        return mDatabaseComponent;
    }

    public HelperComponent getHelperComponent() {
        return mHelperComponent;
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }

}
