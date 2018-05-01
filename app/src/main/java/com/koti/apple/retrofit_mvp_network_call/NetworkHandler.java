package com.koti.apple.retrofit_mvp_network_call;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Handles network instances
 */
public class NetworkHandler {
    private static final NetworkHandler ourInstance = new NetworkHandler();
    private Interceptor mInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (response.isSuccessful())
                return response;
            switch (response.code()) {
                case 400:
                    break;
            }

            return response;
        }
    };

    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(90000, TimeUnit.MILLISECONDS)
            .connectTimeout(90000, TimeUnit.MILLISECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(mInterceptor)
            .build();

    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(IpApi.URL)
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private IpApi mIpApi = mRetrofit.create(IpApi.class);

    public NetworkHandler() {
    }

    public static NetworkHandler instance() {
        return ourInstance;
    }

    public IpApi ipInfoApi() {
        return mIpApi;
    }
}
