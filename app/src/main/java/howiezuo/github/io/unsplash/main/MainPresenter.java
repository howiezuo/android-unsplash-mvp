package howiezuo.github.io.unsplash.main;

import android.support.annotation.NonNull;

import java.util.List;

import howiezuo.github.io.unsplash.api.ApiClient;
import howiezuo.github.io.unsplash.api.Photos;
import howiezuo.github.io.unsplash.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;

    Photos photos = ApiClient.createService(Photos.class);
    private int mCurrentPage = 1;

    public MainPresenter(@NonNull MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadPhotos() {
        Call<List<Photo>> task = photos.getPhotos();
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
        Call<List<Photo>> task = photos.getPhotos(++mCurrentPage);
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
