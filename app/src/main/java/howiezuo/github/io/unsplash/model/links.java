package howiezuo.github.io.unsplash.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {

    String self;
    String html;
    String photos;
    String likes;
    String portfolio;
    String download;
    String download_location;

    protected Links(Parcel in) {
        self = in.readString();
        html = in.readString();
        photos = in.readString();
        likes = in.readString();
        portfolio = in.readString();
        download = in.readString();
        download_location = in.readString();
    }

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
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
