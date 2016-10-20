package io.github.howiezuo.unsplash.api;

import io.github.howiezuo.unsplash.model.Token;
import retrofit2.http.POST;
import rx.Observable;

public interface OAuthService {

    @POST("oauth/token")
    Observable<Token> postOAuth();

}
