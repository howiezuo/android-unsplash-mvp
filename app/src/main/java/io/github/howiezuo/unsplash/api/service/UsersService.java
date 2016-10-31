package io.github.howiezuo.unsplash.api.service;

import io.github.howiezuo.unsplash.model.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UsersService {

    @GET("me")
    Observable<User> getMe();

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);

}
