package io.github.howiezuo.unsplash.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import io.github.howiezuo.unsplash.model.dto.photo.LocationDto;
import io.github.howiezuo.unsplash.model.dto.photo.UrlsDto;

public class PhotoDto implements Parcelable {

    String id;
    int width;
    int height;
    String color;
    int likes;
    boolean liked_by_user;

    LocationDto location;
    UrlsDto urls;
    LinksDto links;
    UserDto user;

    protected PhotoDto(Parcel in) {
        id = in.readString();
        width = in.readInt();
        height = in.readInt();
        color = in.readString();
        likes = in.readInt();
        liked_by_user = in.readByte() != 0;
        location = in.readParcelable(LocationDto.class.getClassLoader());
        urls = in.readParcelable(UrlsDto.class.getClassLoader());
        links = in.readParcelable(LinksDto.class.getClassLoader());
        user = in.readParcelable(UserDto.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLikedByUser() {
        return liked_by_user;
    }

    public void setLikedByUser(boolean liked) {
        liked_by_user = liked;
    }

    public LocationDto getLocation() {
        return location;
    }

    public UrlsDto getUrls() {
        return urls;
    }

    public UserDto getUser() {
        return user;
    }

    public static final Creator<PhotoDto> CREATOR = new Creator<PhotoDto>() {
        @Override
        public PhotoDto createFromParcel(Parcel in) {
            return new PhotoDto(in);
        }

        @Override
        public PhotoDto[] newArray(int size) {
            return new PhotoDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(color);
        dest.writeInt(likes);
        dest.writeByte((byte) (liked_by_user ? 1 : 0));
        dest.writeParcelable(location, flags);
        dest.writeParcelable(urls, flags);
        dest.writeParcelable(links, flags);
        dest.writeParcelable(user, flags);
    }

}
