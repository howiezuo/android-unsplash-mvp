package io.github.howiezuo.unsplash.feature.user.photos;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.model.photo.Liked;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.photo.Unliked;
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
    PhotosService mPhotosService;

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

    @Override
    public void openPhotoDetails(Photo photo) {
        mView.showPhotoDetails(photo);
    }

    @Override
    public void likePhoto(Photo photo, final int index) {
        mPhotosService.like(photo.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Liked>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        // TODO error handling
                    }

                    @Override
                    public void onNext(Liked liked) {
                        mView.likedPhoto(liked.getPhoto(), index);
                    }
                });
    }

    @Override
    public void unlikePhoto(Photo photo, final int index) {
        mPhotosService.unlike(photo.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unliked>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        // TODO error handling
                    }

                    @Override
                    public void onNext(Unliked unliked) {
                        mView.unlikedPhoto(unliked.getPhoto(), index);
                    }
                });
    }
}
