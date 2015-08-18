package jp.yumemi.lab.refactorme.module.qiita.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jp.yumemi.lab.refactorme.Environment;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiManager {

    private static QiitaApiService sService;

    private static QiitaApiService createService() {
        // Gsonの設定(ただ今回はこれが無くても動きはした)
        Gson qiitaJson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        // Retrofitのアダプタ
        RestAdapter qiitaRestAdapter = new RestAdapter.Builder()
                .setEndpoint(Environment.QIITA_API_ENDPOINT)
                .setConverter(new GsonConverter(qiitaJson))
                .build();
        return qiitaRestAdapter.create(QiitaApiService.class);
    }

    public static QiitaApiService getService() {
        if (sService == null) {
            sService = createService();
        }
        return sService;
    }

    public static void setService(QiitaApiService service) {
        sService = service;
    }
}

