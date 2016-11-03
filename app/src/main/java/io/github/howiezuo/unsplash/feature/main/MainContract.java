package io.github.howiezuo.unsplash.feature.main;

import java.util.List;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

        void loadMorePhotos();

        void openPhotoDetails(Photo photo);

        void openUserDetails(User user);

        void likePhoto(Photo photo, int index);

        void unlikePhoto(Photo photo, int index);

    }

    interface View extends BaseView<Presenter> {

        void refreshPhotos(List<Photo> list);

        void addPhotos(List<Photo> list);

        void showPhotoDetails(Photo photo);

        void showUserDetails(User user);

        void likedPhoto(Photo photo, int index);

        void unlikedPhoto(Photo photo, int index);

    }

}
