package io.github.howiezuo.unsplash.api.service;

import io.github.howiezuo.unsplash.model.dto.oauth.TokenDto;
import io.github.howiezuo.unsplash.model.dto.oauth.token.PostDto;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface OAuthService {

    @POST("oauth/token")
    Observable<TokenDto> postOAuth(@Body PostDto body);

}
