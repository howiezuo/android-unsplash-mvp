package io.github.howiezuo.unsplash.feature.detail;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.model.Photo;

@Module
public class DetailPresenterModule {

    private final DetailContract.View mView;
    private final Photo mPhoto;

    public DetailPresenterModule(DetailContract.View view, Photo photo) {
        mView = view;
        mPhoto = photo;
    }

    @Provides
    DetailContract.View provideDetailContractView() {
        return mView;
    }

    @Provides
    Photo providePhoto() {
        return mPhoto;
    }
}
