package io.github.howiezuo.unsplash.user;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.MeService;
import io.github.howiezuo.unsplash.model.User;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenter implements UserContract.Presenter {

    private final  UserContract.View mView;

    @Inject
    MeService mMeService;

    @Inject
    public UserPresenter(@NonNull UserContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadMe() {
        mMeService.getMe()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e);
                    }

                    @Override
                    public void onNext(User user) {
                        Logger.d(user);
                    }
                });
    }
}
