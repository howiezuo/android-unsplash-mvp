package io.github.howiezuo.unsplash.api.service;

import io.github.howiezuo.unsplash.model.oauth.Token;
import io.github.howiezuo.unsplash.model.oauth.token.Post;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface OAuthService {

    @POST("oauth/token")
    Observable<Token> postOAuth(@Body Post body);

}
