package io.github.howiezuo.unsplash.feature.me;

import dagger.Module;
import dagger.Provides;

@Module
public class MePresenterModule {

    private final MeContract.View mMeView;

    public MePresenterModule(MeContract.View meView) {
        mMeView = meView;
    }

    @Provides
    MeContract.View provideMeView() {
        return mMeView;
    }

}
