package ru.samsung.itschool.mdev.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private YandexAPI yandexapi;
    public static final String BASE_URL = "https://predictor.yandex.net";
    public static final String KEY = "pdct.1.1.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.searchText);
        TextView textView = findViewById(R.id.searchResult);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                Gson gson = new GsonBuilder().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                yandexapi = retrofit.create(YandexAPI.class);
                yandexapi.getResult(editText.getText().toString(),KEY, "en", 5).enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(Call<Answer> call, Response<Answer> response) {
                        // 200
                        if (response.code() == 200) {
                            String result = TextUtils.join(", ", response.body().getText());
                            textView.setText(result);
                        } else {
                            Log.d("RRR", "Code:" + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<Answer> call, Throwable t) {
                        Log.d("RRR",t.getMessage());
                    }
                });
            }
        });



    }
}