package io.github.howiezuo.unsplash.model.photo.location;

import android.os.Parcel;
import android.os.Parcelable;

public class PositionDto implements Parcelable {

    double latitude;
    double longitude;

    protected PositionDto(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public double getLat() {
        return latitude;
    }

    public double getLng() {
        return longitude;
    }

    public static final Creator<PositionDto> CREATOR = new Creator<PositionDto>() {
        @Override
        public PositionDto createFromParcel(Parcel in) {
            return new PositionDto(in);
        }

        @Override
        public PositionDto[] newArray(int size) {
            return new PositionDto[size];
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
