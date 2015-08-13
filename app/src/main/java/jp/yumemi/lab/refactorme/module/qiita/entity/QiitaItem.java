package jp.yumemi.lab.refactorme.module.qiita.entity;

import com.google.gson.annotations.SerializedName;

public class QiitaItem {

    @SerializedName("id")
    public int id;

    @SerializedName("uuid")
    public String uuid;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("user")
    public QiitaUser user;
}
