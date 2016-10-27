package io.github.howiezuo.unsplash.api;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.Config;
import io.github.howiezuo.unsplash.api.service.MeService;
import io.github.howiezuo.unsplash.api.service.OAuthService;
import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.helper.PreferencesHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Interceptor provideHttpInterceptor(final PreferencesHelper helper) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder builder = original.newBuilder()
                        .addHeader("Accept-Version", Config.API_VERSION);
                String token = helper.getToken();
                if (token != null) {
                    builder.addHeader("Authorization", "Bearer " + token);
                } else {
                    builder.addHeader("Authorization", "Client-ID " + Config.CLIENT_ID);
                }
                Request request = builder.build();

                return chain.proceed(request);
            }
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient) {
        return provideRetrofit(httpClient, Config.API_URL);
    }

    @Provides
    @Singleton
    @Named("oauth")
    Retrofit provideOAuthRetrofit(OkHttpClient httpClient) {
        return provideRetrofit(httpClient, Config.OAUTH_URL);
    }

    private static Retrofit provideRetrofit(OkHttpClient httpClient, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PhotosService providePhotosService(Retrofit retrofit) {
        return retrofit.create(PhotosService.class);
    }

    @Provides
    @Singleton
    OAuthService provideOAuthService(@Named("oauth") Retrofit retrofit) {
        return retrofit.create(OAuthService.class);
    }

    @Provides
    @Singleton
    MeService provideMeService(Retrofit retrofit) {
        return retrofit.create(MeService.class);
    }
}
