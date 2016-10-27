package io.github.howiezuo.unsplash.helper;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class HelperModule {

    @Provides
    PreferencesHelper providePreferencesHelper(Context context) {
        return PreferencesHelper.getInstance(context);
    }

}
