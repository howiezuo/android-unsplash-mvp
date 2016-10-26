package io.github.howiezuo.unsplash.main;

import java.util.List;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.model.Photo;
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
        mPhotosService.getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Photo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        mView.refreshPhotos(photos);
                    }
                });
    }

    @Override
    public void loadMorePhotos() {
        mPhotosService.getPhotos(++mCurrentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Photo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

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
}
