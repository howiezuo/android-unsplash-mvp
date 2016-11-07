package io.github.howiezuo.unsplash.feature;

import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;

public interface PhotoItemListener {

    void onPhotoClick(PhotoDto photoDto);

    void onUserClick(UserDto userDto);

    void onLikeClick(PhotoDto photoDto, int index);

}
