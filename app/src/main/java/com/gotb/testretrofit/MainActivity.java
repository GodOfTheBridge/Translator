package com.gotb.testretrofit;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public final String URL = "https://translate.yandex.net";
    public final String KEY = "trnsl.1.1.20160324T093729Z.b14b7c54accb1f8e.7d811d65e5f9b19b5541568a35af1e6c8019b10e";

    private TextView tvTranslate;
    private EditText etInputText;
    private YandexTranslateService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTranslate = (TextView) findViewById(R.id.tvTranslate);
        etInputText = (EditText) findViewById(R.id.etInputText);

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL)
                .build();

        service = retrofit.create(YandexTranslateService.class);
    }

    public void onClickTranslate(View view) {
        final Map<String, String> mapJson = new HashMap<>();
        mapJson.put("key", KEY);
        mapJson.put("text", etInputText.getText().toString());
        mapJson.put("lang", "en-ru");

        service.translate(mapJson).enqueue(new Callback<TranslateData>() {
            @Override
            public void onResponse(Response<TranslateData> response, Retrofit retrofit) {
                tvTranslate.setText(response.body().getText().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void onClickClear(View view) {
        tvTranslate.setText("");
        etInputText.setText("");
    }

}
