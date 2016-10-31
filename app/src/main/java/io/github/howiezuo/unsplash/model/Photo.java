package io.github.howiezuo.unsplash.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {

    String id;
    int width;
    int height;
    String color;
    int likes;
    boolean liked_by_user;

    User user;

    Links links;
    Urls urls;


    protected Photo(Parcel in) {
        id = in.readString();
        width = in.readInt();
        height = in.readInt();
        color = in.readString();
        likes = in.readInt();
        liked_by_user = in.readByte() != 0;
        user = in.readParcelable(User.class.getClassLoader());
        links = in.readParcelable(Links.class.getClassLoader());
        urls = in.readParcelable(Urls.class.getClassLoader());
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

    public User getUser() {
        return user;
    }

    public Urls getUrls() {
        return urls;
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
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
        dest.writeParcelable(user, flags);
        dest.writeParcelable(links, flags);
        dest.writeParcelable(urls, flags);
    }

}
