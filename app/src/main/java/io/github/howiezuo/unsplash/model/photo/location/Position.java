package io.github.howiezuo.unsplash.model.photo.location;

import android.os.Parcel;
import android.os.Parcelable;

public class Position implements Parcelable {

    double latitude;
    double longitude;

    protected Position(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public double getLat() {
        return latitude;
    }

    public double getLng() {
        return longitude;
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
