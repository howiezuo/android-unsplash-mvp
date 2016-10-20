package howiezuo.github.io.unsplash.api;

import howiezuo.github.io.unsplash.model.Token;
import retrofit2.http.POST;
import rx.Observable;

public interface OAuthService {

    @POST("oauth/token")
    Observable<Token> postOAuth();

}
