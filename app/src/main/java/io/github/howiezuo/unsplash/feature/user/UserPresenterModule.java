package io.github.howiezuo.unsplash.feature.user;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.feature.user.photos.UserPhotosContract;

@Module
public class UserPresenterModule {

    private final UserContract.View mUserView;
    private final UserPhotosContract.View mPhotosView;
    private final UserDto mUserDto;

    public UserPresenterModule(UserContract.View userView, UserPhotosContract.View photosView, UserDto userDto) {
        mUserView = userView;
        mPhotosView = photosView;
        mUserDto = userDto;
    }

    @Provides
    UserContract.View provideUserView() {
        return mUserView;
    }

    @Provides
    UserPhotosContract.View providePhotosView() {
        return mPhotosView;
    }

    @Provides
    UserDto provideUserDto() {
        return mUserDto;
    }
}
