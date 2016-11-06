package io.github.howiezuo.unsplash.model.photo;

import io.github.howiezuo.unsplash.model.PhotoDto;
import io.github.howiezuo.unsplash.model.UserDto;

public class LikedDto {

    PhotoDto photo;
    UserDto user;

    public PhotoDto getPhoto() {
        return photo;
    }

    public UserDto getUser() {
        return user;
    }

}
