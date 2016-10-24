package io.github.howiezuo.unsplash.user;

import android.support.annotation.NonNull;

import javax.inject.Inject;

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View mView;

    @Inject
    public UserPresenter(@NonNull UserContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

}
