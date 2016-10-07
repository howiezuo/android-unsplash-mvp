package howiezuo.github.io.unsplash.main;

import java.util.List;

import javax.inject.Inject;

import howiezuo.github.io.unsplash.api.Photos;
import howiezuo.github.io.unsplash.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;
    private Photos mPhotos;

    private int mCurrentPage = 1;

    @Inject
    public MainPresenter(MainContract.View view, Photos photos) {
        mView = view;
        mView.setPresenter(this);

        mPhotos = photos;
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
