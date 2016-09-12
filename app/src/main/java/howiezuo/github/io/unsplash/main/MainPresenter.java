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

    public MainPresenter(@NonNull MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadPhotos() {
        Photos photos = ApiClient.createService(Photos.class);
        Call<List<Photo>> task = photos.getPhotos();
        task.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response != null && response.body() != null) {
                    mView.refreshList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                if (t == null) {

                }
            }
        });
    }
}
