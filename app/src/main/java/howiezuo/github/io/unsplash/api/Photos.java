package howiezuo.github.io.unsplash.api;

import java.util.List;

import howiezuo.github.io.unsplash.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Photos {

    @GET("photos")
    Call<List<Photo>> getPhotos();

}
