package com.philipowino.myrestaurants;

import static com.philipowino.myrestaurants.Constants.YELP_API_KEY;
import static com.philipowino.myrestaurants.Constants.YELP_BASE_URL;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class YelpClient {

    private static Retrofit retrofit = null;

    public static YelpApi getClient() {

        if (retrofit == null) {
          Interceptor interceptor =  new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization","Bearer " +  YELP_API_KEY)
                            .build();
                    return chain.proceed(newRequest);
                }
            };

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
          loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor).addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(YELP_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(YelpApi.class);
    }
}
