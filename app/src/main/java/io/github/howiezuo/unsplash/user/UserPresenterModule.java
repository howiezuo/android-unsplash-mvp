package io.github.howiezuo.unsplash.user;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.model.User;
import io.github.howiezuo.unsplash.user.photos.UserPhotosContract;

@Module
public class UserPresenterModule {

    private final UserContract.View mUserView;
    private final UserPhotosContract.View mPhotosView;
    private final User mUser;

    public UserPresenterModule(UserContract.View userView, UserPhotosContract.View photosView, User user) {
        mUserView = userView;
        mPhotosView = photosView;
        mUser = user;
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
    User provideUser() {
        return mUser;
    }
}
