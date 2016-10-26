package io.github.howiezuo.unsplash.api.service;

import io.github.howiezuo.unsplash.model.User;
import retrofit2.http.GET;
import rx.Observable;

public interface MeService {

    @GET("me")
    Observable<User> getMe();

}
