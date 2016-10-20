package io.github.howiezuo.unsplash.main;

import java.util.List;

import io.github.howiezuo.unsplash.base.BasePresenter;
import io.github.howiezuo.unsplash.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

        void loadMorePhotos();

        void openPhotoDetails(Photo photo);

    }

    interface View extends BaseView<Presenter> {

        void refreshPhotos(List<Photo> list);

        void addPhotos(List<Photo> list);

        void showPhotoDetails(Photo photo);

    }

}
