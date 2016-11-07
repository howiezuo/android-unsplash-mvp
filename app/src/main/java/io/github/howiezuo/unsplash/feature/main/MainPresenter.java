package io.github.howiezuo.unsplash.feature.main;

import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.dto.photo.LikedDto;
import io.github.howiezuo.unsplash.model.dto.photo.UnlikedDto;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;
    @Inject
    PhotosService mPhotosService;

    private int mCurrentPage = 1;

    @Inject
    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadPhotos() {
        mPhotosService.getCurated()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoDto>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<PhotoDto> photosDto) {
                        mView.refreshPhotos(photosDto);
                    }
                });
    }

    @Override
    public void loadMorePhotos() {
        mPhotosService.getCurated(++mCurrentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoDto>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<PhotoDto> photosDto) {
                        mView.addPhotos(photosDto);
                    }
                });
    }

    @Override
    public void openPhotoDetails(PhotoDto photoDto) {
        mView.showPhotoDetails(photoDto);
    }

    @Override
    public void openUserDetails(UserDto userDto) {
        mView.showUserDetails(userDto);
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
                        mView.showError();
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
                        mView.showError();
                    }

                    @Override
                    public void onNext(UnlikedDto unlikedDto) {
                        mView.unlikedPhoto(unlikedDto.getPhoto(), index);
                    }
                });
    }
}
