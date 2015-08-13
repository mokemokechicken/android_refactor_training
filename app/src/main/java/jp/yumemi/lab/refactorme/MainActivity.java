package jp.yumemi.lab.refactorme;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import jp.yumemi.lab.refactorme.android_mvc_sample.R;
import jp.yumemi.lab.refactorme.base.Animation;
import jp.yumemi.lab.refactorme.base.FragmentRouter;
import jp.yumemi.lab.refactorme.module.qiita.presentation.FragmentTag;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            // TODO: Moduleに決めさせるべきかもしれない
            FragmentRouter.instance.replace(getSupportFragmentManager(), R.id.container, FragmentTag.LIST, null, Animation.NON, false);
        }
    }

    // 端末のバックキーが押下された際、WebView もブラウザバックさせる
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO: ここは EventBusから各Fragmentが処理する
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FragmentRouter.Tag.DETAIL.toString());
//        if (fragment != null) {
//            WebView webView = ((DetailFragment)fragment).getWebView();
//            if (webView != null) {
//                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
//                    webView.goBack();
//                    return true;
//                }
//            }
//        }
        return super.onKeyDown(keyCode, event);
    }
}
