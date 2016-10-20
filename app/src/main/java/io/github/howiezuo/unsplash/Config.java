package io.github.howiezuo.unsplash;

public class Config {

    public static final String APP_SCHEME = "app";
    public static final String APP_HOST = "io.github.howiezuo.unsplash";

    public static final String API_URL = "https://api.unsplash.com";
    public static final String API_VERSION = "v1";
    public static final String CLIENT_ID = "bb0b8493dba89c5b8765bdca724c00c3766b20971017f3315536ba19856e6287";
    public static final String LOGIN_URL = "https://unsplash.com/oauth/authorize" +
            "?client_id=" + Config.CLIENT_ID +
            "&redirect_uri=" + Config.REDIRECT_URI +
            "&response_type=code" +
            "&scope=public+read_user+read_photos+read_collections+write_likes+write_followers";
    public static final String REDIRECT_URI = Config.APP_SCHEME + "://" + Config.APP_HOST + "/oauth/authorize";
}
