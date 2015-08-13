package jp.yumemi.lab.refactorme.module.qiita.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import jp.yumemi.lab.refactorme.Const;
import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

public class ApiManager {
    public interface QiitaApiService {

        @GET("/api/v1/items")
        public List<QiitaItem> getItem();

        //@GET("/api/v1/tags")
        //public List<QiitaTagApiEntity> getTags();
    }

    // Gsonの設定(ただ今回はこれが無くても動きはした)
    private static Gson qiitaJson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    // Retrofitのアダプタ
    private static RestAdapter qiitaRestAdapter = new RestAdapter.Builder()
            .setEndpoint(Const.QIITA_API_ENDPOINT)
            .setConverter(new GsonConverter(qiitaJson))
            .build();

    private static QiitaApiService sService;

    public static QiitaApiService getService() {
        if (sService == null) {
            sService = qiitaRestAdapter.create(QiitaApiService.class);
        }
        return sService;
    }

    public static void setService(QiitaApiService service) {
        sService = service;
    }
}

