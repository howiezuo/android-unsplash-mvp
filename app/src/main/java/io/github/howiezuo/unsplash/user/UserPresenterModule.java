package io.github.howiezuo.unsplash.user;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.model.User;

@Module
public class UserPresenterModule {

    private final UserContract.View mView;
    private final User mUser;

    public UserPresenterModule(UserContract.View view, User user) {
        mView = view;
        mUser = user;
    }

    @Provides
    UserContract.View provideUserContractView() {
        return mView;
    }

    @Provides
    User provideUser() {
        return mUser;
    }
}
