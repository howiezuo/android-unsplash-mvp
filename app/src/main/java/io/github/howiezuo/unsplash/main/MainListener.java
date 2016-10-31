package io.github.howiezuo.unsplash.main;

import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;

public interface MainListener {

    void onPhotoClick(Photo photo);

    void onUserClick(User user);

    void onLikeClick(Photo photo, int index);

}
