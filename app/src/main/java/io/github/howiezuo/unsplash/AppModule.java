package io.github.howiezuo.unsplash;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.helper.HelperComponent;

@Module(
        subcomponents = {
                HelperComponent.class
        }
)
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
