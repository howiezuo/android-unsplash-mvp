package io.github.howiezuo.unsplash.user;

import java.util.List;

import io.github.howiezuo.unsplash.base.BasePresenter;
import io.github.howiezuo.unsplash.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;

public interface UserPhotosContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

    }

    interface View extends BaseView<Presenter> {

        void showPhotos(List<Photo> photos);

    }
}
