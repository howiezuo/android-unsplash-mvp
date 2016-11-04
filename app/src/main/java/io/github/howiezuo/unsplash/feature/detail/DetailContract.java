package io.github.howiezuo.unsplash.feature.detail;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;

public interface DetailContract {

    interface Presenter extends BasePresenter {

        void showPhoto();

        void loadPhoto();

        void openUserDetails(User user);

        void likePhoto(Photo photo);

        void unlikePhoto(Photo photo);

    }

    interface View extends BaseView<Presenter> {

        void showPhoto(Photo photo);

        void showLocation(Photo photo);

        void showUserDetails(User user);

        void likedPhoto(Photo photo);

        void unlikedPhoto(Photo photo);

        void showError();

    }

}
