package io.github.howiezuo.unsplash.model.oauth;

public class Token {

    String access_token;
    String token_type;
    String scope;
    long created_at;

    public String getAccessToken() {
        return access_token;
    }
}
