package io.github.howiezuo.unsplash.api.service;

import java.util.List;

import io.github.howiezuo.unsplash.Config;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface UsersService {

    @GET("me")
    Observable<User> getMe();

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);

    @GET("users/{username}/photos?per_page=" + Config.DEFAULT_PER_PAGE)
    Observable<List<Photo>> getUserPhotos(@Path("username") String username);

    @GET("users/{username}/photos?per_page=" + Config.DEFAULT_PER_PAGE)
    Observable<List<Photo>> getUserPhotos(@Path("username") String username, @Query("page") int page);

}
