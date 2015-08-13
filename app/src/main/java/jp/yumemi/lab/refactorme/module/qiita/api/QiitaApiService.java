package jp.yumemi.lab.refactorme.module.qiita.api;

import java.util.List;

import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;
import retrofit.http.GET;

/**
* Created by k_morishita on 15/08/14.
*/
public interface QiitaApiService {

    @GET("/api/v1/items")
    public List<QiitaItem> getItem();

    //@GET("/api/v1/tags")
    //public List<QiitaTagApiEntity> getTags();
}
