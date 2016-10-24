package io.github.howiezuo.unsplash.model.oauth.token;

import io.github.howiezuo.unsplash.Config;

public class Post {

    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;
    private String grant_type;

    public Post(String code) {
        this.client_id = Config.CLIENT_ID;
        this.client_secret = Config.CLIENT_SECRET;
        this.redirect_uri = Config.REDIRECT_URI;
        this.code = code;
        this.grant_type = "authorization_code";
    }
}
