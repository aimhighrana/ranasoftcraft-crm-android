package org.ranasoftcraft.com.services;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        logger.info(request.url().toString());
        Response response = chain.proceed(request);
//        if(response.isSuccessful()) {
//            return response;
//        }
        return response;
    }
}
