package io.github.howiezuo.unsplash;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context mContext;

    AppModule(Context c) {
        mContext = c;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }
}
