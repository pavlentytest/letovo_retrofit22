package ru.samsung.itschool.mdev.myapplication;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnekdotAPI {

    @GET("/api/get")
    Call<ArrayList<Answer>> getAnekdots(@Query("site") String site,
                                        @Query("num") int num,
                                        @Query("name") String name);
}
