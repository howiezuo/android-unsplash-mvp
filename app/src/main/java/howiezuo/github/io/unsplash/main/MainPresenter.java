package howiezuo.github.io.unsplash.main;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import howiezuo.github.io.unsplash.api.Photos;
import howiezuo.github.io.unsplash.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;
    @Inject
    Photos mPhotos;

    private int mCurrentPage = 1;

    @Inject
    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadPhotos() {
        Call<List<Photo>> task = mPhotos.getPhotos();
        task.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response != null && response.body() != null) {
                    mView.refreshPhotos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                if (t == null) {

                }
            }
        });
    }

    @Override
    public void loadMorePhotos() {
        Call<List<Photo>> task = mPhotos.getPhotos(++mCurrentPage);
        task.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response != null && response.body() != null) {
                    mView.addPhotos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                if (t == null) {

                }
            }
        });
    }

    @Override
    public void openPhotoDetails(Photo photo) {
        mView.showPhotoDetails(photo);
    }
}
