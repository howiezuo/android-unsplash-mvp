package io.github.howiezuo.unsplash.model.entity;

import io.github.howiezuo.unsplash.model.dto.user.ProfileImageDto;
import io.realm.RealmObject;


public class ProfileImage extends RealmObject {

    private String small;
    private String medium;
    private String large;

    public ProfileImage() {

    }

    public ProfileImage(ProfileImageDto dto) {
        small = dto.getSmall();
        medium = dto.getMedium();
        large = dto.getLarge();
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
