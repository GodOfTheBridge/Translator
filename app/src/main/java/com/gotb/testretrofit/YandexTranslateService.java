package com.gotb.testretrofit;


import java.util.Map;


import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface YandexTranslateService {
    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<TranslateData> translate(@FieldMap Map map);
}
