package io.github.howiezuo.unsplash.feature.user.photos;

import java.util.List;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;

public interface UserPhotosContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

        void openPhotoDetails(Photo photo);

        void likePhoto(Photo photo, int index);

        void unlikePhoto(Photo photo, int index);

    }

    interface View extends BaseView<Presenter> {

        void showPhotos(List<Photo> photos);

        void showPhotoDetails(Photo photo);

        void likedPhoto(Photo photo, int index);

        void unlikedPhoto(Photo photo, int index);

    }
}
