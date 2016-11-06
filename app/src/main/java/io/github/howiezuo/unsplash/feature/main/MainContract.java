package io.github.howiezuo.unsplash.feature.main;

import java.util.List;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.PhotoDto;
import io.github.howiezuo.unsplash.model.UserDto;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

        void loadMorePhotos();

        void openPhotoDetails(PhotoDto photoDto);

        void openUserDetails(UserDto userDto);

        void likePhoto(PhotoDto photoDto, int index);

        void unlikePhoto(PhotoDto photoDto, int index);

    }

    interface View extends BaseView<Presenter> {

        void refreshPhotos(List<PhotoDto> list);

        void addPhotos(List<PhotoDto> list);

        void showPhotoDetails(PhotoDto photoDto);

        void showUserDetails(UserDto userDto);

        void likedPhoto(PhotoDto photoDto, int index);

        void unlikedPhoto(PhotoDto photoDto, int index);

        void showError();

    }

}
