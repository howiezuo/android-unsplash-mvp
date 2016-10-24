package io.github.howiezuo.unsplash.user;

import dagger.Module;
import dagger.Provides;

@Module
public class UserPresenterModule {

    private final UserContract.View mView;

    public UserPresenterModule(UserContract.View view) {
        mView = view;
    }

    @Provides
    UserContract.View provideUserContractView() {
        return mView;
    }
}
