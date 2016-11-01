package io.github.howiezuo.unsplash.user;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPhotosPresenter implements UserPhotosContract.Presenter {

    private final UserPhotosContract.View mView;
    private final User mUser;

    @Inject
    UsersService mUsersService;

    @Inject
    public UserPhotosPresenter(@NonNull UserPhotosContract.View view, User user) {
        mView = view;
        mView.setPresenter(this);
        mUser = user;
    }

    @Override
    public void loadPhotos() {
        mUsersService.getUserPhotos(mUser.getUsername())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Photo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        mView.showPhotos(photos);
                    }
                });
    }
}
