package howiezuo.github.io.unsplash.api;

import java.util.List;

import howiezuo.github.io.unsplash.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Photos {

    @Headers({
            "Accept-Version: v1",
            "Authorization: Client-ID bb0b8493dba89c5b8765bdca724c00c3766b20971017f3315536ba19856e6287"
    })
    @GET("photos")
    Call<List<Photo>> getPhotos();

}
