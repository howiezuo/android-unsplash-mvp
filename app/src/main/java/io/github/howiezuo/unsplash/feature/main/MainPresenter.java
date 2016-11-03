package io.github.howiezuo.unsplash.feature.main;

import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.model.Liked;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.Unliked;
import io.github.howiezuo.unsplash.model.User;
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
                .subscribe(new Observer<List<Photo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        mView.refreshPhotos(photos);
                    }
                });
    }

    @Override
    public void loadMorePhotos() {
        mPhotosService.getCurated(++mCurrentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Photo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        mView.addPhotos(photos);
                    }
                });
    }

    @Override
    public void openPhotoDetails(Photo photo) {
        mView.showPhotoDetails(photo);
    }

    @Override
    public void openUserDetails(User user) {
        mView.showUserDetails(user);
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
                        mView.showError();
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
                        mView.showError();
                    }

                    @Override
                    public void onNext(Unliked unliked) {
                        mView.unlikedPhoto(unliked.getPhoto(), index);
                    }
                });
    }
}
