package io.github.howiezuo.unsplash.model.dto.user;

import android.os.Parcel;
import android.os.Parcelable;

import io.github.howiezuo.unsplash.model.entity.ProfileImage;

public class ProfileImageDto implements Parcelable {

    String small;
    String medium;
    String large;

    public ProfileImageDto(ProfileImage entity) {
        small = entity.getSmall();
        medium = entity.getMedium();
        large = entity.getLarge();
    }

    public String getSmall() {
        return small;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }

    protected ProfileImageDto(Parcel in) {
        small = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public static final Creator<ProfileImageDto> CREATOR = new Creator<ProfileImageDto>() {
        @Override
        public ProfileImageDto createFromParcel(Parcel in) {
            return new ProfileImageDto(in);
        }

        @Override
        public ProfileImageDto[] newArray(int size) {
            return new ProfileImageDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(medium);
        dest.writeString(large);
    }
}
