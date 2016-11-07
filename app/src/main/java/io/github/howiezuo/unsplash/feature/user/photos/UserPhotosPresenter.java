package io.github.howiezuo.unsplash.feature.user.photos;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.dto.photo.LikedDto;
import io.github.howiezuo.unsplash.model.dto.photo.UnlikedDto;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPhotosPresenter implements UserPhotosContract.Presenter {

    private final UserPhotosContract.View mView;
    private final UserDto mUserDto;

    @Inject
    UsersService mUsersService;
    @Inject
    PhotosService mPhotosService;

    @Inject
    public UserPhotosPresenter(@NonNull UserPhotosContract.View view, UserDto userDto) {
        mView = view;
        mView.setPresenter(this);
        mUserDto = userDto;
    }

    @Override
    public void loadPhotos() {
        mUsersService.getUserPhotos(mUserDto.getUsername())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoDto>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(List<PhotoDto> photosDto) {
                        mView.showPhotos(photosDto);
                    }
                });
    }

    @Override
    public void openPhotoDetails(PhotoDto photoDto) {
        mView.showPhotoDetails(photoDto);
    }

    @Override
    public void likePhoto(PhotoDto photoDto, final int index) {
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
                        // TODO error handling
                    }

                    @Override
                    public void onNext(LikedDto likedDto) {
                        mView.likedPhoto(likedDto.getPhoto(), index);
                    }
                });
    }

    @Override
    public void unlikePhoto(PhotoDto photoDto, final int index) {
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
                        // TODO error handling
                    }

                    @Override
                    public void onNext(UnlikedDto unlikedDto) {
                        mView.unlikedPhoto(unlikedDto.getPhoto(), index);
                    }
                });
    }
}
