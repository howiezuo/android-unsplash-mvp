package io.github.howiezuo.unsplash.model.dto.photo;

import android.os.Parcel;
import android.os.Parcelable;

import io.github.howiezuo.unsplash.model.dto.photo.location.PositionDto;

public class LocationDto implements Parcelable {

    String city;
    String country;
    PositionDto position;

    protected LocationDto(Parcel in) {
        city = in.readString();
        country = in.readString();
        position = in.readParcelable(PositionDto.class.getClassLoader());
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public PositionDto getPosition() {
        return position;
    }

    public static final Creator<LocationDto> CREATOR = new Creator<LocationDto>() {
        @Override
        public LocationDto createFromParcel(Parcel in) {
            return new LocationDto(in);
        }

        @Override
        public LocationDto[] newArray(int size) {
            return new LocationDto[size];
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
