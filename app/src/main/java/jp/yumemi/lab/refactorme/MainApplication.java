package jp.yumemi.lab.refactorme;

import android.app.Application;
import android.util.Log;

import jp.yumemi.lab.refactorme.base.FragmentRouter;
import jp.yumemi.lab.refactorme.module.qiita.QiitaModule;
import jp.yumemi.lab.refactorme.module.qiita.presentation.FragmentTag;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FragmentRouter.instance = new FragmentRouter();  // なんか変な感じなので要検討。

        // 必要なら ModuleManager.add(new QiitaModule()) みたいにした方が良いだろう。
        new QiitaModule().onLoad();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
