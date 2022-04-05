package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AnekdotAPI anekdotapi;
    private ArrayList<Answer> arrayList;
    private RecyclerView recyclerView;
    public static final String BASE_URL = "https://umorili.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        anekdotapi = retrofit.create(AnekdotAPI.class);

        anekdotapi.getAnekdots("anekdot.ru",5,"new anekdot").enqueue(new Callback<ArrayList<Answer>>() {
            @Override
            public void onResponse(Call<ArrayList<Answer>> call, Response<ArrayList<Answer>> response) {
                // 200
                if(response.code() == 200) {
                    arrayList.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Log.d("RRR","Code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Answer>> call, Throwable t) {
                Log.d("RRR",t.getMessage());
            }
        });

    }
}