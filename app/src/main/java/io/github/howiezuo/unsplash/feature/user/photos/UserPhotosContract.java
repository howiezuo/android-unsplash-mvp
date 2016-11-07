package io.github.howiezuo.unsplash.feature.user.photos;

import java.util.List;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;

public interface UserPhotosContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

        void openPhotoDetails(PhotoDto photoDto);

        void likePhoto(PhotoDto photoDto, int index);

        void unlikePhoto(PhotoDto photoDto, int index);

    }

    interface View extends BaseView<Presenter> {

        void showPhotos(List<PhotoDto> photosDto);

        void showPhotoDetails(PhotoDto photoDto);

        void likedPhoto(PhotoDto photoDto, int index);

        void unlikedPhoto(PhotoDto photoDto, int index);

    }
}
