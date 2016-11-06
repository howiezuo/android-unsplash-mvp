package io.github.howiezuo.unsplash.feature.me;

import java.util.List;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.PhotoDto;
import io.github.howiezuo.unsplash.model.UserDto;

public interface MeContract {

    interface Presenter extends BasePresenter {

        void loadMe();

        void loadPhotos();

    }

    interface View extends BaseView<Presenter> {

        void showMe(UserDto userDto);

        void showPhotos(List<PhotoDto> photosDto);

        void showError();

    }

}
