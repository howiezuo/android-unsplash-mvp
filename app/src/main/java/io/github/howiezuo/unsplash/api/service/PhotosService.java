package io.github.howiezuo.unsplash.api.service;

import java.util.List;

import io.github.howiezuo.unsplash.Config;
import io.github.howiezuo.unsplash.model.photo.Liked;
import io.github.howiezuo.unsplash.model.photo.Unliked;
import io.github.howiezuo.unsplash.model.photo.Download;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.photo.Stats;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface PhotosService {

    @GET("photos?per_page=" + Config.DEFAULT_PER_PAGE)
    Observable<List<Photo>> getPhotos();

    @GET("photos?per_page=" + Config.DEFAULT_PER_PAGE)
    Observable<List<Photo>> getPhotos(@Query("page") int page);

    @GET("photos/curated?per_page=" + Config.DEFAULT_PER_PAGE)
    Observable<List<Photo>> getCurated();

    @GET("photos/curated?per_page=" + Config.DEFAULT_PER_PAGE)
    Observable<List<Photo>> getCurated(@Query("page") int page);

    @GET("photos/{id}")
    Observable<Photo> getPhoto(@Path("id") String id);

    @GET("photos/random")
    Call<Photo> getRandom();

    @GET("photos/{id}/stats")
    Call<Stats> getStats(@Path("id") String id);

    @GET("photos/{id}/download")
    Call<Download> getDownload(@Path("id") String id);

    @PUT("photos/{id}")
    Call<Photo> update(@Path("id") String id);

    @POST("photos/{id}/like")
    Observable<Liked> like(@Path("id") String id);

    @DELETE("photos/{id}/like")
    Observable<Unliked> unlike(@Path("id") String id);

}
