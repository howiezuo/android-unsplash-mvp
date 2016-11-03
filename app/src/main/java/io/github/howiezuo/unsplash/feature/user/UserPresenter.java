package io.github.howiezuo.unsplash.feature.user;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.model.User;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenter implements UserContract.Presenter {

    private final UserContract.View mView;
    private final User mUser;

    @Inject
    UsersService mUsersService;

    @Inject
    public UserPresenter(@NonNull UserContract.View view, User user) {
        mView = view;
        mView.setPresenter(this);
        mUser = user;
    }

    @Override
    public void loadMe() {
        mUsersService.getMe()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        mView.showMe(user);
                    }
                });
    }

    @Override
    public void showUser() {
        mView.showUser(mUser);
    }

}
