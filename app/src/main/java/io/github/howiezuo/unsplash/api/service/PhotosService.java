package io.github.howiezuo.unsplash.api.service;

import java.util.List;

import io.github.howiezuo.unsplash.app.Constants;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.photo.LikedDto;
import io.github.howiezuo.unsplash.model.dto.photo.UnlikedDto;
import io.github.howiezuo.unsplash.model.dto.photo.DownloadDto;
import io.github.howiezuo.unsplash.model.dto.photo.StatsDto;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface PhotosService {

    @GET("photos?per_page=" + Constants.DEFAULT_PER_PAGE)
    Observable<List<PhotoDto>> getPhotos();

    @GET("photos?per_page=" + Constants.DEFAULT_PER_PAGE)
    Observable<List<PhotoDto>> getPhotos(@Query("page") int page);

    @GET("photos/curated?per_page=" + Constants.DEFAULT_PER_PAGE)
    Observable<List<PhotoDto>> getCurated();

    @GET("photos/curated?per_page=" + Constants.DEFAULT_PER_PAGE)
    Observable<List<PhotoDto>> getCurated(@Query("page") int page);

    @GET("photos/{id}")
    Observable<PhotoDto> getPhoto(@Path("id") String id);

    @GET("photos/random")
    Call<PhotoDto> getRandom();

    @GET("photos/{id}/stats")
    Call<StatsDto> getStats(@Path("id") String id);

    @GET("photos/{id}/download")
    Call<DownloadDto> getDownload(@Path("id") String id);

    @PUT("photos/{id}")
    Call<PhotoDto> update(@Path("id") String id);

    @POST("photos/{id}/like")
    Observable<LikedDto> like(@Path("id") String id);

    @DELETE("photos/{id}/like")
    Observable<UnlikedDto> unlike(@Path("id") String id);

}
