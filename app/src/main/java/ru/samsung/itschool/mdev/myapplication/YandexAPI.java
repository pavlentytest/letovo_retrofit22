package ru.samsung.itschool.mdev.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexAPI {

    @GET("/api/v1/predict.json/complete")
    Call<Answer> getResult(@Query("q") String q,
                           @Query("key") String key,
                           @Query("lang") String lang,
                           @Query("limit") Integer limit);
}
