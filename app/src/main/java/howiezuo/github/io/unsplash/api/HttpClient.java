package howiezuo.github.io.unsplash.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClient {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpInterceptor())
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public static OkHttpClient create() {
        return client;
    }

}
