package io.github.howiezuo.unsplash.model;


import android.os.Parcel;
import android.os.Parcelable;

import io.github.howiezuo.unsplash.model.user.ProfileImageDto;

public class UserDto implements Parcelable{

    String id;
    String username;
    String first_name;
    String last_name;
    String name;
    String portfolio_url;
    String bio;
    String location;
    int total_likes;
    int total_photos;
    int total_collections;
    boolean followed_by_user;
    int downloads;
    int uploads_remaining;
    String instagram_username;
    String email;
    ProfileImageDto profile_image;
    LinksDto links;

    protected UserDto(Parcel in) {
        id = in.readString();
        username = in.readString();
        name = in.readString();
        portfolio_url = in.readString();
        profile_image = in.readParcelable(ProfileImageDto.class.getClassLoader());
        links = in.readParcelable(LinksDto.class.getClassLoader());
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getTotalLikes() {
        return total_likes;
    }

    public int getTotalPhotos() {
        return total_photos;
    }

    public int getTotalCollections() {
        return total_collections;
    }

    public ProfileImageDto getProfileImage() {
        return profile_image;
    }

    public static final Creator<UserDto> CREATOR = new Creator<UserDto>() {
        @Override
        public UserDto createFromParcel(Parcel in) {
            return new UserDto(in);
        }

        @Override
        public UserDto[] newArray(int size) {
            return new UserDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(portfolio_url);
        dest.writeParcelable(profile_image, flags);
        dest.writeParcelable(links, flags);
    }

}
