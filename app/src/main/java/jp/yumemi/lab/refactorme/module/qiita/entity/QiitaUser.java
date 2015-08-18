package jp.yumemi.lab.refactorme.module.qiita.entity;

import com.google.gson.annotations.SerializedName;

public class QiitaUser {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("profile_image_url")
    public String profileImageUrl;
}
