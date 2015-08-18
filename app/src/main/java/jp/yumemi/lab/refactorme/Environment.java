package jp.yumemi.lab.refactorme;

/**
 * 環境情報を提供する
 */
public class Environment {
    public static final String QIITA_API_ENDPOINT = "http://qiita.com";

    public static String getQiitaApiKey() {
        return "DebugApiKey";
    }
}
