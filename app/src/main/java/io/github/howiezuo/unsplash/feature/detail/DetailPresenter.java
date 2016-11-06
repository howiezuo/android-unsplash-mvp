package io.github.howiezuo.unsplash.feature.detail;


import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.model.PhotoDto;
import io.github.howiezuo.unsplash.model.UserDto;
import io.github.howiezuo.unsplash.model.photo.LikedDto;
import io.github.howiezuo.unsplash.model.photo.UnlikedDto;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailPresenter implements DetailContract.Presenter {

    private final DetailContract.View mView;
    private final PhotoDto mPhotoDto;

    @Inject
    PhotosService mPhotosService;

    @Inject
    public DetailPresenter(@NonNull DetailContract.View view, PhotoDto photoDto) {
        mView = view;
        mView.setPresenter(this);
        mPhotoDto = photoDto;
    }

    @Override
    public void showPhoto() {
        mView.showPhoto(mPhotoDto);
    }

    @Override
    public void loadPhoto() {
        mPhotosService.getPhoto(mPhotoDto.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(PhotoDto photoDto) {
                        mView.showLocation(photoDto);
                    }
                });

    }

    @Override
    public void openUserDetails(UserDto userDto) {
        mView.showUserDetails(userDto);
    }

    @Override
    public void likePhoto(PhotoDto photoDto) {
        mPhotosService.like(photoDto.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LikedDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(LikedDto likedDto) {
                        mPhotoDto.setLikedByUser(likedDto.getPhoto().isLikedByUser());
                        mPhotoDto.setLikes(likedDto.getPhoto().getLikes());
                        mView.likedPhoto(mPhotoDto);
                    }
                });

    }

    @Override
    public void unlikePhoto(PhotoDto photoDto) {
        mPhotosService.unlike(photoDto.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UnlikedDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(UnlikedDto unlikedDto) {
                        mPhotoDto.setLikedByUser(unlikedDto.getPhoto().isLikedByUser());
                        mPhotoDto.setLikes(unlikedDto.getPhoto().getLikes());
                        mView.unlikedPhoto(mPhotoDto);
                    }
                });
    }
}
