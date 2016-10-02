package howiezuo.github.io.unsplash.detail;


import android.support.annotation.NonNull;

import howiezuo.github.io.unsplash.model.Photo;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;
    private Photo mPhoto;

    public DetailPresenter(@NonNull DetailContract.View view, Photo photo) {
        mView = view;
        mView.setPresenter(this);
        mPhoto = photo;
    }

    @Override
    public void showPhoto() {
        mView.showPhoto(mPhoto);
    }
}
