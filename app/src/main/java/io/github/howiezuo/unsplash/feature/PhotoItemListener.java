package io.github.howiezuo.unsplash.feature;

import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;

public interface PhotoItemListener {

    void onPhotoClick(Photo photo);

    void onUserClick(User user);

    void onLikeClick(Photo photo, int index);

}
