package jp.yumemi.lab.refactorme.module.qiita.entity;

import com.google.gson.annotations.SerializedName;

public class QiitaUser {

    @SerializedName("id")
    public int id;

    @SerializedName("url_name")
    public String urlName;

    @SerializedName("profile_image_url")
    public String profileImageUrl;
}
