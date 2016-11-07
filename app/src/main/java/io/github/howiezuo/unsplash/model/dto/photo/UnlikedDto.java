package io.github.howiezuo.unsplash.model.dto.photo;

import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;

public class UnlikedDto {

    PhotoDto photo;
    UserDto user;

    public PhotoDto getPhoto() {
        return photo;
    }

    public UserDto getUser() {
        return user;
    }

}
