package howiezuo.github.io.unsplash.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("Accept-Version", "v1")
                .addHeader("Authorization", "Client-ID bb0b8493dba89c5b8765bdca724c00c3766b20971017f3315536ba19856e6287")
                .build();
        return chain.proceed(request);
    }

}
