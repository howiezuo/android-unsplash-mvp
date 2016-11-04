package io.github.howiezuo.unsplash.feature.detail;


import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;
import io.github.howiezuo.unsplash.model.photo.Liked;
import io.github.howiezuo.unsplash.model.photo.Unliked;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailPresenter implements DetailContract.Presenter {

    private final DetailContract.View mView;
    private final Photo mPhoto;

    @Inject
    PhotosService mPhotosService;

    @Inject
    public DetailPresenter(@NonNull DetailContract.View view, Photo photo) {
        mView = view;
        mView.setPresenter(this);
        mPhoto = photo;
    }

    @Override
    public void showPhoto() {
        mView.showPhoto(mPhoto);
    }

    @Override
    public void loadPhoto() {
        mPhotosService.getPhoto(mPhoto.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Photo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(Photo photo) {
                        mView.showLocation(photo);
                    }
                });

    }

    @Override
    public void openUserDetails(User user) {
        mView.showUserDetails(user);
    }

    @Override
    public void likePhoto(Photo photo) {
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
                        mView.showError();
                    }

                    @Override
                    public void onNext(Liked liked) {
                        mPhoto.setLikedByUser(liked.getPhoto().isLikedByUser());
                        mPhoto.setLikes(liked.getPhoto().getLikes());
                        mView.likedPhoto(mPhoto);
                    }
                });

    }

    @Override
    public void unlikePhoto(Photo photo) {
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
                        mView.showError();
                    }

                    @Override
                    public void onNext(Unliked unliked) {
                        mPhoto.setLikedByUser(unliked.getPhoto().isLikedByUser());
                        mPhoto.setLikes(unliked.getPhoto().getLikes());
                        mView.unlikedPhoto(mPhoto);
                    }
                });
    }
}
