package io.github.howiezuo.unsplash.feature.detail;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.model.PhotoDto;

@Module
public class DetailPresenterModule {

    private final DetailContract.View mView;
    private final PhotoDto mPhotoDto;

    public DetailPresenterModule(DetailContract.View view, PhotoDto photoDto) {
        mView = view;
        mPhotoDto = photoDto;
    }

    @Provides
    DetailContract.View provideDetailContractView() {
        return mView;
    }

    @Provides
    PhotoDto providePhotoDto() {
        return mPhotoDto;
    }
}
