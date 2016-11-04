package io.github.howiezuo.unsplash.model.photo;

import android.os.Parcel;
import android.os.Parcelable;

import io.github.howiezuo.unsplash.model.photo.location.Position;

public class Location implements Parcelable {

    String city;
    String country;
    Position position;

    protected Location(Parcel in) {
        city = in.readString();
        country = in.readString();
        position = in.readParcelable(Position.class.getClassLoader());
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Position getPosition() {
        return position;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(country);
        dest.writeParcelable(position, flags);
    }
}
