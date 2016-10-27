package io.github.howiezuo.unsplash.model;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

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
    ProfileImage profile_image;
    Links links;

    protected User(Parcel in) {
        id = in.readString();
        username = in.readString();
        name = in.readString();
        portfolio_url = in.readString();
        profile_image = in.readParcelable(ProfileImage.class.getClassLoader());
        links = in.readParcelable(Links.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public ProfileImage getProfileImage() {
        return profile_image;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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
