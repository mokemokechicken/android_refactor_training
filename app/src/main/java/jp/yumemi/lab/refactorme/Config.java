package jp.yumemi.lab.refactorme;

/**
 * Created by k_morishita on 15/08/18.
 */

import jp.yumemi.lab.refactorme.android_mvc_sample.BuildConfig;

/**
 * アプリケーションの設定を提供する
 */
public class Config {

    private Config() {
    }

    // 例えばこうする
    public static int getDisplayItemCount() {
        return BuildConfig.DEBUG ? 10 : 20;
    }

    // これでも可能
    public static final int DISPLAY_ITEM_COUNT = BuildConfig.DEBUG ? 10 : 20;
}

/*
「問題： Debug/Release で表示件数を変更する」 の解答の説明
問題：
QiitaLatestItemModelで表示件数を以下のように直接指定していますが、
これを「Debugビルドの時は10件」「Releaseビルドの時は20件」となるようにリファクタリングしてください。

説明：
AndroidStudioの標準で使われているgradleのAndroidPluginを使うと、
BuildConfig というクラスが自動生成されます。
このクラスの DEBUG というstatic定数が、 debug/release ビルドで切り替わるのを利用すると簡単に実現できます。

他にも、 `Build Variants` という仕組みがあるので、
このConfigファイル自体を debug/releaseで別に提供することもできます。
詳しくは「android gradle flavor buildType」などでググってみてください。
 */
