package jp.yumemi.lab.refactorme.module.qiita.model;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import jp.yumemi.lab.refactorme.module.qiita.api.ApiManager;
import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;

public class QiitaItemModel {

    private final List<QiitaItem> mQiitaItemList = new ArrayList<>();
    private int mItemCount = 0;
    private boolean mIsBusy = false;

    public QiitaItemModel() {
    }

    public List<QiitaItem> getItemList() {
        return mQiitaItemList;
    }

    public int getItemCount() {
        return mItemCount;
    }

    public void load() {
        // ビジー状態なら何もしない
        if (mIsBusy) {
            return;
        }
        mIsBusy = true;

        // 非同期で API を発行
        new AsyncTask<Void, Void, List<QiitaItem>>() {
            @Override
            protected List<QiitaItem> doInBackground(Void... voids) {
                return ApiManager.getService().getItems();
            }

            @Override
            protected void onPostExecute(List<QiitaItem> result) {
                super.onPostExecute(result);
                // 取得結果でリストを更新(参照は維持)
                mQiitaItemList.clear();
                mQiitaItemList.addAll(result);
                // 件数を更新
                mItemCount = result.size();
                // ビジー状態を解除
                mIsBusy = false;
                // EventBus で完了通知を送る
                EventBus.getDefault().post(new LoadedEvent(true));
            }
        }.execute();
    }

    // イベントに値を詰め込んで投げることが出来る(今回は成功/失敗フラグのみ)
    public static class LoadedEvent {
        private boolean isSuccess;

        private LoadedEvent(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }
}
