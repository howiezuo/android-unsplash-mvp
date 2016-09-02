package howiezuo.github.io.unsplash.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .client(HttpClient.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> cls) {
        return retrofit.create(cls);
    }
}
