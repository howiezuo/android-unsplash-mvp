package io.github.howiezuo.unsplash.feature.me;

import java.util.List;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;

public interface MeContract {

    interface Presenter extends BasePresenter {

        void loadMe();

        void loadPhotos();

    }

    interface View extends BaseView<Presenter> {

        void showMe(User user);

        void showPhotos(List<Photo> photos);

        void showError();

    }

}
