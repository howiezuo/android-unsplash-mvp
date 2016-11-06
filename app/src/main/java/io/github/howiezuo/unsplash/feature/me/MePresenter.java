package io.github.howiezuo.unsplash.feature.me;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.model.UserDto;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MePresenter implements MeContract.Presenter {

    private final MeContract.View mView;

    @Inject
    UsersService mUsersService;

    @Inject
    public MePresenter(@NonNull MeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadMe() {
        mUsersService.getMe()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(UserDto userDto) {
                        mView.showMe(userDto);
                    }
                });
    }

    @Override
    public void loadPhotos() {

    }

}