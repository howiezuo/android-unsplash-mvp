package io.github.howiezuo.unsplash.detail;

import io.github.howiezuo.unsplash.base.BasePresenter;
import io.github.howiezuo.unsplash.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;

public interface DetailContract {

    interface Presenter extends BasePresenter {

        void showPhoto();

    }

    interface View extends BaseView<Presenter> {

        void showPhoto(Photo photo);

    }

}
