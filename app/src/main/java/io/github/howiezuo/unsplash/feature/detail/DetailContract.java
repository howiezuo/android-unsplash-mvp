package io.github.howiezuo.unsplash.feature.detail;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;

public interface DetailContract {

    interface Presenter extends BasePresenter {

        void showPhoto();

        void loadPhoto();

        void openUserDetails(UserDto userDto);

        void likePhoto(PhotoDto photoDto);

        void unlikePhoto(PhotoDto photoDto);

    }

    interface View extends BaseView<Presenter> {

        void showPhoto(PhotoDto photoDto);

        void showLocation(PhotoDto photoDto);

        void showUserDetails(UserDto userDto);

        void likedPhoto(PhotoDto photoDto);

        void unlikedPhoto(PhotoDto photoDto);

        void showError();

    }

}
