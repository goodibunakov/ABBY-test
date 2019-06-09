package ru.goodibunakov.abbyy_test.api;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            apiService = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder()
                                    .excludeFieldsWithoutExposeAnnotation()
                                    .create()
                    ))
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }
}
