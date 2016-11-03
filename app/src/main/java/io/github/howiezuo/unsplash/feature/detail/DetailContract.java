package io.github.howiezuo.unsplash.feature.detail;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;

public interface DetailContract {

    interface Presenter extends BasePresenter {

        void showPhoto();

    }

    interface View extends BaseView<Presenter> {

        void showPhoto(Photo photo);

    }

}
