package io.github.howiezuo.unsplash.model.dto;


import android.os.Parcel;
import android.os.Parcelable;

import io.github.howiezuo.unsplash.model.dto.user.ProfileImageDto;
import io.github.howiezuo.unsplash.model.entity.Me;

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

    public UserDto(Me entity) {
        id = entity.getId();
        username = entity.getUsername();
        name = entity.getName();
        bio = entity.getBio();
        location = entity.getLocation();
        total_likes = entity.getTotalLikes();
        total_photos = entity.getTotalPhotos();
        total_collections = entity.getTotalCollections();
        profile_image = new ProfileImageDto(entity.getProfileImage());
    }

    public String getId() {
        return id;
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

    public String getLocation() {
        return location;
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

    protected UserDto(Parcel in) {
        id = in.readString();
        username = in.readString();
        name = in.readString();
        portfolio_url = in.readString();
        profile_image = in.readParcelable(ProfileImageDto.class.getClassLoader());
        links = in.readParcelable(LinksDto.class.getClassLoader());
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
