package io.github.howiezuo.unsplash;

public class Config {

    // APP
    public static final String APP_SCHEME = "app";
    public static final String APP_HOST = "io.github.howiezuo.unsplash";

    // API
    public static final String OAUTH_URL = "https://unsplash.com";
    public static final String API_URL = "https://api.unsplash.com";
    public static final String API_VERSION = "v1";
    public static final String REDIRECT_URI = APP_SCHEME + "://" + APP_HOST + "/oauth/authorize";
    public static final String LOGIN_URL = OAUTH_URL + "/oauth/authorize" +
            "?client_id=%s" +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=code" +
            "&scope=public+read_user+read_photos+read_collections+write_likes+write_followers";

    public static final int DEFAULT_PER_PAGE = 20;

    // DB
    public static final String REALM_DATABASE_NAME = "database.realm";
    public static final long REALM_SCHEMA_VERSION = 1;

    public static final float PHOTO_RATIO = 1.6f;
}
