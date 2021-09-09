package com.philipowino.myrestaurants.network;

import com.philipowino.myrestaurants.model.YelpBusinessesSearchResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

public class YelpClient implements YelpApi {
    private Retrofit retrofit = null;
    @Override
    public Call<YelpBusinessesSearchResponse> getRestaurants(String location, String term) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    .addHeader("Authorization", YELP_API_KEY);
                            return null;
                        }
                    })
        }
    }
}
