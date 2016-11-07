package io.github.howiezuo.unsplash.model.dto.photo;


import android.os.Parcel;
import android.os.Parcelable;

public class UrlsDto implements Parcelable {

    String raw;
    String full;
    String regular;
    String small;
    String thumb;

    protected UrlsDto(Parcel in) {
        raw = in.readString();
        full = in.readString();
        regular = in.readString();
        small = in.readString();
        thumb = in.readString();
    }

    public static final Creator<UrlsDto> CREATOR = new Creator<UrlsDto>() {
        @Override
        public UrlsDto createFromParcel(Parcel in) {
            return new UrlsDto(in);
        }

        @Override
        public UrlsDto[] newArray(int size) {
            return new UrlsDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(raw);
        dest.writeString(full);
        dest.writeString(regular);
        dest.writeString(small);
        dest.writeString(thumb);
    }

    public String getRegular() {
        return regular;
    }

    public String getSmall() {
        return small;
    }

    public String getThumb() {
        return thumb;
    }

}
