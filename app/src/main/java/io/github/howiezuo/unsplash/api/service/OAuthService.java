package io.github.howiezuo.unsplash.api.service;

import io.github.howiezuo.unsplash.model.oauth.TokenDto;
import io.github.howiezuo.unsplash.model.oauth.token.PostDto;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface OAuthService {

    @POST("oauth/token")
    Observable<TokenDto> postOAuth(@Body PostDto body);

}
