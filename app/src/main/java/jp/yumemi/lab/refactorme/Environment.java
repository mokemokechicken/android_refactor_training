package jp.yumemi.lab.refactorme;

import jp.yumemi.lab.refactorme.android_mvc_sample.BuildConfig;

/**
 * 環境情報を提供する
 */
public class Environment {
    public static final String QIITA_API_ENDPOINT = "http://qiita.com";

    public static String getQiitaApiKey() {
        return BuildConfig.QIITA_API_KEY;
    }
}

/*
「問題： (B) 環境別のAPI Keyを指定する」 の解答の説明
問題：
現時点の実装では、Qiita Viewer の ApiKeyが `Environment.getQiitaApiKey()` で取得できますが、
これを「Dev環境」「Staging環境」「Release環境」の３つの環境で異なる値を指定できるようにしてください。

説明：
これもBuildConfigから値を取得するようにします。
Customな値をBuildConfigに指定する方法は、
http://iti.hatenablog.jp/entry/2015/06/22/084303
などを見てみてください。

実際の値は app/build.gradle の productFlavors { ... } にかかれています。

この例では「環境 → productFlavors」に対応させていますが、buildTypeに対応させることもあります。
 */
