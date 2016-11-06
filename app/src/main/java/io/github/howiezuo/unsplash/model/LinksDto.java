package io.github.howiezuo.unsplash.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LinksDto implements Parcelable {

    String self;
    String html;
    String photos;
    String likes;
    String portfolio;
    String download;
    String download_location;

    protected LinksDto(Parcel in) {
        self = in.readString();
        html = in.readString();
        photos = in.readString();
        likes = in.readString();
        portfolio = in.readString();
        download = in.readString();
        download_location = in.readString();
    }

    public static final Creator<LinksDto> CREATOR = new Creator<LinksDto>() {
        @Override
        public LinksDto createFromParcel(Parcel in) {
            return new LinksDto(in);
        }

        @Override
        public LinksDto[] newArray(int size) {
            return new LinksDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(self);
        dest.writeString(html);
        dest.writeString(photos);
        dest.writeString(likes);
        dest.writeString(portfolio);
        dest.writeString(download);
        dest.writeString(download_location);
    }
}
